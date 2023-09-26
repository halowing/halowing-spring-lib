package com.halowing.lib.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.halowing.lib.event.EventSubscriber;
import com.halowing.lib.spring.event.LocalMessageEventPublisher.EventDto;
import com.halowing.lib.string.StringUtility;


public abstract class AbstractLocalMessageEventSubscriber implements EventSubscriber<String> {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractLocalMessageEventSubscriber.class);
	private final String topic;

	public AbstractLocalMessageEventSubscriber(String topic) {
		this.topic = topic.trim();
	}
	
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
			
			if(!this.topic.equals(topic))
				return;
			
			process(message);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return;
		}
	}

	@Override
	public abstract void process(String message);

	@Override
	public String getTopic() {
		return topic;
	}

}
