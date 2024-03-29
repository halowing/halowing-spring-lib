package com.halowing.lib.spring.web.response;

public enum HttpMessageCode {
	CREATED("http.method.created.success"),
	DELETED("http.method.deleted.success"),
	UPDATED("http.method.updated.success"),
	UNAUTHENTICATED("http.status.unauthenticated"),
	UNAUTHORIZED("http.status.unauthorized"),
	
	;
	
	private final String messageCode;
	
	
	private HttpMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
	public String getMessageCode() {
		return this.messageCode;
	}
	
}
