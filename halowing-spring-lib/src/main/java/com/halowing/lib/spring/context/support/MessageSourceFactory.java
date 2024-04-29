package com.halowing.lib.spring.context.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.halowing.lib.exception.DefaultApplicationException;
import com.halowing.lib.string.StringUtility;

import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
public class MessageSourceFactory {

	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final int DEFAULT_CACHE_SECOND = 60;
	public static final String[] BASE_NAMES = new String[] 
			{"classpath:/messages/messages", "classpath:/messages/http","classpath:/messages/http-status-message"} ;
	
	private static final ReloadableResourceBundleMessageSource DEFAULT_MESSAGE_SOURCE =  defaultMessageSource();;
	
	private static final Map<String, MessageSource> CUSTOM_MESSAGE_SOURCE_MAP = new ConcurrentHashMap<>();
	
	
	/**
	 * 
	 * @param customMessageSource
	 */
	public void messageSource(@NotBlank String name, MessageSource customMessageSource) {
		
		if (customMessageSource == null || customMessageSource instanceof DelegatingMessageSource)
			return;
		else if(CUSTOM_MESSAGE_SOURCE_MAP.containsKey(name))
			throw new DefaultApplicationException("name is duplicated. name is " + name);
		else
			CUSTOM_MESSAGE_SOURCE_MAP.put(name, customMessageSource);
	}
	
	public static  MessageSource getMessageSource() {
		return getMessageSource(null);
	}
	
	public static  MessageSource getMessageSource(String name) {
		
		if(StringUtility.isBlank(name))
			return DEFAULT_MESSAGE_SOURCE;
		else if(CUSTOM_MESSAGE_SOURCE_MAP.containsKey(name))
			return CUSTOM_MESSAGE_SOURCE_MAP.get(name);
		else
			throw new DefaultApplicationException("Requested MessageSource does not contain. name = " + name);
		
	}
	
	private static ReloadableResourceBundleMessageSource defaultMessageSource() {
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(BASE_NAMES);
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
		messageSource.setCacheSeconds(DEFAULT_CACHE_SECOND);
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

}
