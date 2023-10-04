package com.halowing.lib.spring.exception;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

import com.halowing.lib.spring.context.support.MessageSourceFactory;
import com.halowing.lib.spring.web.response.HttpMessageCode;

public abstract class AbstractSpringApplicationException extends RuntimeException {

	private static final long serialVersionUID = -6079824549817596416L;
	
	private static MessageSourceAccessor messageSourceAccessor = null ;
	
	public AbstractSpringApplicationException(String message) {
		super(message);
	}

	public AbstractSpringApplicationException(Throwable cause) {
		super(cause);
	}

	public AbstractSpringApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	protected static String getConstructMessage(HttpMessageCode messageCode, String... args) {
		
		MessageSourceAccessor msa = new MessageSourceAccessor(MessageSourceFactory.getMessageSource()) ;
		Locale locale = Locale.US;
		return msa.getMessage(messageCode.getMessageCode(), args, locale);
	}
	
	protected static String getConstructMessage(String errorCode, String... args) {
		
		MessageSourceAccessor msa = new MessageSourceAccessor(MessageSourceFactory.getMessageSource()) ;
		Locale locale = Locale.US;
		return msa.getMessage(errorCode, args, locale);
	}
	
	@Override
	public String getLocalizedMessage() {
		
		if(messageSourceAccessor == null)
			messageSourceAccessor = new MessageSourceAccessor(MessageSourceFactory.getMessageSource()) ;
		
		return messageSourceAccessor.getMessage(getErrorCode(), getArgs());
	}

	public abstract String getErrorCode() ;
	
	public abstract String[] getArgs();
	
	public String getLocalizedMessage(Locale locale) {
		
		if(locale == null)
			throw new NullPointerException("locale is null");
		
		if(messageSourceAccessor == null)
			messageSourceAccessor = new MessageSourceAccessor(MessageSourceFactory.getMessageSource()) ;
		
		return messageSourceAccessor.getMessage(getErrorCode(), getArgs(), locale);
	}

}
