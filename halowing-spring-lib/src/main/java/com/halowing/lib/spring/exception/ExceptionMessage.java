package com.halowing.lib.spring.exception;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

import com.halowing.lib.spring.context.support.MessageSourceFactory;

public class ExceptionMessage {
	private static final MessageSourceAccessor messageSourceAccessor = 
			new MessageSourceAccessor(MessageSourceFactory.getMessageSource()) ;
	
	public static String getMessage(String errorCode, String... args) {
		
		Locale locale = Locale.US;
		return messageSourceAccessor.getMessage(errorCode, args, locale);
	}
	
	public static String getLocalizedMessage(String errorCode, String... args) {
		return messageSourceAccessor.getMessage(errorCode, args, Locale.getDefault());
	}
}
