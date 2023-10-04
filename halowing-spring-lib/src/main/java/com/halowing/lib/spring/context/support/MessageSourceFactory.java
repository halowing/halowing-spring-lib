package com.halowing.lib.spring.context.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceFactory {

	private static final Logger log = LoggerFactory.getLogger(MessageSourceFactory.class);
	
	private static final String[] basenames = new String[] 
			{"classpath:/messages/messages", "classpath:/messages/http"} ;
	
	private static MessageSource messageSource = null;
	
	private static MessageSource defaMessageSource = defaultMessageSource();
	
	public MessageSourceFactory(@Autowired MessageSource messageSource) {
		log.debug("MessageSource is {}:",messageSource.getClass() );
		
		if(messageSource instanceof DelegatingMessageSource)
			return;
		
		MessageSourceFactory.messageSource = messageSource;
	}
	
	private static MessageSource defaultMessageSource() {
		
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames(basenames);
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(60);
		return ms;
	}

	public static MessageSource getMessageSource() {
		
		if(messageSource != null)
			return messageSource;
		
		synchronized (basenames) {
			if(messageSource != null)
				return messageSource;
			
			log.debug("Call defaultMessageSource.");
			return defaMessageSource;
		}
		
	}
	

}
