package com.halowing.lib.spring.web.exception;

import org.springframework.http.HttpStatus;

import com.halowing.lib.spring.exception.AbstractSpringApplicationException;


public abstract class AbstractSpringWebApplicationException extends AbstractSpringApplicationException {

	private static final long serialVersionUID = 603718905693431959L;
	
	public AbstractSpringWebApplicationException(String message) {
		super(message);
	}
	
	public AbstractSpringWebApplicationException(String message, Throwable t) {
		super(message, t);
	}

	public AbstractSpringWebApplicationException(Throwable t) {
		super(t);
	}

	public abstract HttpStatus getHttpStatus() ;
}
