package com.halowing.lib.spring.web.response;

import java.util.Arrays;

public class SimpleMessageResponse implements MessageResponse {

	private static final long serialVersionUID = 1L;
	
	private final String code;
	private final String[] args;
	
	public SimpleMessageResponse(String code, String... args ) {
		this.code = code;
		this.args = args;
	}

	@Override
	public String toString() {
		return "SimpleMessageResponse [code=" + code + ", args=" + Arrays.toString(args) + "]";
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static class Created extends SimpleMessageResponse {

		private static final long serialVersionUID = 1L;
		
		public Created(String args ) {
			super(HttpMessageCode.CREATED.getMessageCode(), args);
		}
	}
	
	public static class Deleted extends SimpleMessageResponse {

		private static final long serialVersionUID = 1L;
		
		public Deleted(String args ) {
			super(HttpMessageCode.DELETED.getMessageCode(), args);
		}
	}
	
	public static class Updated extends SimpleMessageResponse {

		private static final long serialVersionUID = 1L;
		
		public Updated(String args ) {
			super(HttpMessageCode.UPDATED.getMessageCode(), args);
		}
	}
	
	public static class UnAuthenticated extends SimpleMessageResponse {

		private static final long serialVersionUID = 1L;
		
		public UnAuthenticated(String args ) {
			super(HttpMessageCode.UNAUTHENTICATED.getMessageCode(), args);
		}
	}
	
	public static class UnAuthorized extends SimpleMessageResponse {

		private static final long serialVersionUID = 1L;
		
		public UnAuthorized(String args ) {
			super(HttpMessageCode.UNAUTHORIZED.getMessageCode(), args);
		}
	}
	
	public static class NotFound extends SimpleMessageResponse {

		private static final long serialVersionUID = 1L;
		
		public NotFound(String args ) {
			super(HttpMessageCode.NOT_FOUND.getMessageCode(), args);
		}
	}
	
}
