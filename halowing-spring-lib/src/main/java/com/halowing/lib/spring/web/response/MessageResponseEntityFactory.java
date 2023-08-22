package com.halowing.lib.spring.web.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageResponseEntityFactory {
	
	private final MessageSourceAccessor messageSourceAccessor;
	
	private static final String CREATED = "response.success.created";

	private static final String DELETED = "response.success.deleted";
	
	private static final String UPDATED = "response.success.updated";
	
	private static final ReloadableResourceBundleMessageSource defaultMessageSource = new ReloadableResourceBundleMessageSource();
	
	static {
		defaultMessageSource.setBasename("classpath:messages/response");
		defaultMessageSource.setDefaultEncoding("UTF-8");
		defaultMessageSource.setCacheSeconds(60);
	}
	
	public MessageResponseEntityFactory(@Autowired MessageSource messageSource) {
		
		if(messageSource == null || messageSource.getMessage(CREATED, null, "" , Locale.getDefault()).equals("") )
			messageSource = defaultMessageSource;
		
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource) ;
	}
	
	public ResponseEntity<MessageResponse> created(Locale locale){
		
		String message = messageSourceAccessor.getMessage(CREATED,  locale);
		
		return new ResponseEntity<MessageResponse>(new SimpleMessageResponse(message),HttpStatus.CREATED) ;
	}
	
	public ResponseEntity<MessageResponse> deleted(Locale locale){
		
		String message = messageSourceAccessor.getMessage(DELETED,  locale);
		
		return ResponseEntity.ok(new SimpleMessageResponse(message)) ;
	}
	
	public ResponseEntity<MessageResponse> updated(Locale locale){
		
		String message = messageSourceAccessor.getMessage(UPDATED,  locale);
		
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
