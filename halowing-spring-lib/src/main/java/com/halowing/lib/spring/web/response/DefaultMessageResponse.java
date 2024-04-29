package com.halowing.lib.spring.web.response;

public class DefaultMessageResponse implements MessageResponse {

	private static final long serialVersionUID = -1234462789808842557L;
	
	private final String message;
	
	public DefaultMessageResponse(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "SimpleMessageResponse [message=" + message + "]";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
