package com.halowing.lib.spring.exception;

public abstract class AbstractSpringApplicationException extends RuntimeException {

	private static final long serialVersionUID = -6079824549817596416L;
	
	public AbstractSpringApplicationException(String message) {
		super(message);
	}

	public AbstractSpringApplicationException(Throwable cause) {
		super(cause);
	}

	public AbstractSpringApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract String getErrorCode() ;
	
	public abstract String[] getArgs();
	
	@Override
	public String getLocalizedMessage() {
		return ExceptionMessage.getLocalizedMessage(getErrorCode(), getArgs());
	}

}
