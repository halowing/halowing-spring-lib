package com.halowing.lib.spring.event;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import com.halowing.lib.event.EventPublisher;

public class LocalMessageEventPublisher implements EventPublisher<String> {

	private final Logger log = LoggerFactory.getLogger(LocalMessageEventPublisher.class);
	
	private final ApplicationEventPublisher applicationEventPublisher;
	private final String topic;

	public LocalMessageEventPublisher(ApplicationEventPublisher applicationEventPublisher, String topic) {
		this.applicationEventPublisher = applicationEventPublisher;
		this.topic = topic;
	}

	@Override
	public void publish(String message) {
		applicationEventPublisher.publishEvent(new EventDto(topic,message));
		log.debug("complete pubish message. topic ={},  message = {}", topic, message);
	}

	@Override
	public String getTopic() {
		return this.topic;
	}
	
	public static class EventDto implements Serializable {

		private static final long serialVersionUID = 1786039560771393848L;
		
		private final String topic;
		private final String message;
		
		public EventDto(String topic, String message) {
			this.topic = topic;
			this.message = message;
		}
		
		@Override
		public String toString() {
			return "EventDto [topic=" + topic + ", message=" + message + "]";
		}

		public String getTopic() {
			return topic;
		}

		public String getMessage() {
			return message;
		}
	}
}
