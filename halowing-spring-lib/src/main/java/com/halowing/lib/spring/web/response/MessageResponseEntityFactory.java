package com.halowing.lib.spring.web.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.halowing.lib.spring.context.support.MessageSourceFactory;

@Component
public class MessageResponseEntityFactory {
	
	private final MessageSourceAccessor messageSourceAccessor;
	
	public MessageResponseEntityFactory() {
		
		MessageSource messageSource = MessageSourceFactory.getMessageSource();
		
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource) ;
	}
	
	public ResponseEntity<MessageResponse> created(Locale locale){
		
		String message = messageSourceAccessor.getMessage(HttpMessageCode.CREATED.getMessageCode(),  locale);
		
		return new ResponseEntity<MessageResponse>(new SimpleMessageResponse(message),HttpStatus.CREATED) ;
	}
	
	public ResponseEntity<MessageResponse> deleted(Locale locale){
		
		String message = messageSourceAccessor.getMessage(HttpMessageCode.DELETED.getMessageCode(),  locale);
		
		return ResponseEntity.ok(new SimpleMessageResponse(message)) ;
	}
	
	public ResponseEntity<MessageResponse> updated(Locale locale){
		
		String message = messageSourceAccessor.getMessage(HttpMessageCode.UPDATED.getMessageCode(),  locale);
		
		return ResponseEntity.ok(new SimpleMessageResponse(message)) ;
	}
	
	public MessageResponseBuilder getBuilder(Locale locale) {
		
		return new MessageResponseBuilder(locale);
	}
	
	public class MessageResponseBuilder{
		
		private final Locale locale;
		
		private String messageId;
		
		private List<String> list = new ArrayList<String>();
		
		

		public MessageResponseBuilder(Locale locale) {
			this.locale = locale;
		}
		
		public MessageResponseBuilder messageId (String messgeId) {
			
			this.messageId = messgeId;
			
			return this;
		}
		
		public MessageResponseBuilder args (String[] args) {
			this.list.addAll(Arrays.asList(args));
			return this;
		}
		
		public MessageResponseBuilder arg(String arg) {
			
			this.list.add(arg);
			return this;
		}
		
		public ResponseEntity<MessageResponse>  build() {
			
			String message = messageSourceAccessor.getMessage(messageId, list.toArray(), locale);
			
			return ResponseEntity.ok().body(new SimpleMessageResponse(message)) ;
		}
	}
}
