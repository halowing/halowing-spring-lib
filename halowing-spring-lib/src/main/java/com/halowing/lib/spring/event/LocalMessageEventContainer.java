package com.halowing.lib.spring.event;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

import com.halowing.lib.event.EventContainer;
import com.halowing.lib.event.EventPublisher;
import com.halowing.lib.event.EventSubscriber;
import com.halowing.lib.spring.event.LocalMessageEventPublisher.EventDto;
import com.halowing.lib.string.StringUtility;

public class LocalMessageEventContainer implements EventContainer<String> {
	
	private static final Logger log = LoggerFactory.getLogger(LocalMessageEventContainer.class);
	
	private static final Map<String, Set<EventSubscriber<String>>> SUBSCRIBERS = new ConcurrentHashMap<>();
	private static final Map<String, EventPublisher<String>> PUBLISHERS = new ConcurrentHashMap<>();
	
	private final ApplicationEventPublisher applicationEventPublisher;
	
	public LocalMessageEventContainer(
			ApplicationEventPublisher applicationEventPublisher
			) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
	@Override
	public void subscribe(EventSubscriber<String> subscriber)  {
		
		log.trace("subscriber = {}",subscriber);
		
		Objects.requireNonNull(subscriber,"subscriber is null");
		
		String topic = subscriber.getTopic().trim();
		
		if(!StringUtils.hasText(topic))
			throw new NullPointerException("subscriber doesn't have topic");
		
		if(!SUBSCRIBERS.containsKey(topic)) {
			
			synchronized (this) {
				if(!SUBSCRIBERS.containsKey(topic)) {
					SUBSCRIBERS.put(topic, new ConcurrentSkipListSet<EventSubscriber<String>>());
				}
			}
		}
		
		SUBSCRIBERS.get(topic).add(subscriber);
		
		if(!PUBLISHERS.keySet().contains(topic))
		{
			synchronized (this) {
				if(!PUBLISHERS.keySet().contains(topic))
				{
					PUBLISHERS.put(topic, new LocalMessageEventPublisher(applicationEventPublisher, topic)) ;
				}
			}
			
		}
	}

	@Override
	public EventPublisher<String> getPublisher(String topic) {
		
		if(!PUBLISHERS.keySet().contains(topic))
		{
			PUBLISHERS.put(topic, new LocalMessageEventPublisher(applicationEventPublisher, topic)) ;
		}
			
		EventPublisher<String> publisher =  PUBLISHERS.get(topic);
		return publisher;
	}

	@SuppressWarnings("unchecked")
	private Set<EventSubscriber<String>> getSubscribers(String topic) {
		
		if(topic == null || topic.trim().isEmpty())
		{
			log.warn("topic is null or blink.");
			return Collections.EMPTY_SET;
		}
		topic = topic.trim();
		
		if(!SUBSCRIBERS.containsKey(topic))
		{
			log.warn("request topic doesn't have subscriber. topic is {}",topic);
			return Collections.EMPTY_SET;
		}
		
		return SUBSCRIBERS.get(topic);
	}
	
	
	
	
	public class LocalMessageEventManager {

		private final Map<String, Queue<String>> MESSAGE_QUEUE = new ConcurrentHashMap<String, Queue<String>>();
		
		@EventListener
		@Async
		public void listen(EventDto event) {
			
			if(event == null)
			{
				log.warn("event is null");
				return;
			}
			
			try {
				String topic =  StringUtility.requireNonBlink(event.getTopic()) ;
				String message = StringUtility.requireNonBlink(event.getMessage());
				log.debug("[listen]topic = {}, message={}",topic, message);
				
				queueMessage(topic, message);
				
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				return;
			}
		}
		
		private void queueMessage(String topic, String message) {
			
			if(!MESSAGE_QUEUE.containsKey(topic)) {
				MESSAGE_QUEUE.put(topic, new ConcurrentLinkedQueue<String>());
			}
			
			MESSAGE_QUEUE.get(topic).add(message);
			
			MESSAGE_QUEUE.forEach((a,b) ->{
				log.debug("TOPIC={}, MESSAGE={}",a,b);
			});
		}

		public void doTask() {
			
			log.trace("start task");
			
			MESSAGE_QUEUE.keySet()
			.parallelStream()
			.forEach(topic -> {
				
				log.trace("TOPIC ={}",topic);
				
				Set<EventSubscriber<String>> subscribers = getSubscribers(topic);
				if(subscribers.isEmpty())
				{
					MESSAGE_QUEUE.remove(topic);
					return;
				}
				
				doTask(subscribers, topic);
			});
		}

		private void doTask(Set<EventSubscriber<String>> subscribers, String topic) {
			
			Queue<String> queue = MESSAGE_QUEUE.get(topic);
			if(queue == null || queue.isEmpty())
			{
				log.trace("Queue is null or empty. topic={}",topic);
				return;
			}
			
			log.debug("Queue: topic={} , size={} ",topic, queue.size());
			
			while(!queue.isEmpty()) {
				String message = queue.poll();
				subscribers
				.parallelStream()
				.forEach(subscriber ->{
					subscriber.process(message);
				});
			}
			
		}

	}
}
