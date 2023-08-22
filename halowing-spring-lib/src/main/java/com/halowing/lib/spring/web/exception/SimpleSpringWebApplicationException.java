package com.halowing.lib.spring.web.exception;

import org.springframework.http.HttpStatus;

import com.halowing.lib.exception.ApplicationException;
import com.halowing.lib.spring.exception.AbstractSpringApplicationException;
import com.halowing.lib.web.exception.WebApplicationException;

public class SimpleSpringWebApplicationException extends AbstractSpringWebApplicationException {

	private static final long serialVersionUID = 7114663106352785209L;
	
	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String[] args;

	
	public SimpleSpringWebApplicationException(String errorCode, String... args){
		super(getConstructMessage(errorCode, args));
		
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.errorCode = errorCode;
		this.args = args;
	}
	
	public SimpleSpringWebApplicationException(HttpStatus httpStatus, String errorCode, String... args){
		super(getConstructMessage(errorCode, args));
		
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.args = args;
	}
	
	public SimpleSpringWebApplicationException(Throwable cause){
		super(cause);
		
		if(cause instanceof AbstractSpringWebApplicationException) {
			AbstractSpringWebApplicationException e = (AbstractSpringWebApplicationException) cause;
			this.httpStatus = e.getHttpStatus();
			this.errorCode = e.getErrorCode();
			this.args = e.getArgs();
		}else if( cause instanceof AbstractSpringApplicationException) {
			AbstractSpringApplicationException e = (AbstractSpringApplicationException) cause;
			this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			this.errorCode = e.getErrorCode();
			this.args = e.getArgs();
		}else if( cause instanceof WebApplicationException) {
			WebApplicationException e = (WebApplicationException) cause;
			this.httpStatus = HttpStatus.valueOf(e.getHttpStatus());
			this.errorCode = e.getErrorCode();
			this.args = e.getArgs();
		}else if( cause instanceof ApplicationException) {
			ApplicationException e = (ApplicationException) cause;
			this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			this.errorCode = e.getErrorCode();
			this.args = e.getArgs();
		}else {
			this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			this.errorCode = null;
			this.args = null;
		}
	}
	

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String[] getArgs() {
		return args;
	}
	


}
