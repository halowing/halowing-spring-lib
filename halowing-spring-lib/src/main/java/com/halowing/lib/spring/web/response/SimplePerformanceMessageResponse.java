package com.halowing.lib.spring.web.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimplePerformanceMessageResponse extends SimpleMessageResponse implements PerformanceMessageResponse {

	private static final long serialVersionUID = -1234462789808842557L;
	
	@JsonIgnore
	private LocalDateTime requestDateTime;
	
	@JsonIgnore
	private LocalDateTime responseDatetime;
	
	public SimplePerformanceMessageResponse(String code, String... args) {
		super(code, args);
	}

	@Override
	public String toString() {
		return "DefaultPerformanceResponse [requestDateTime=" + requestDateTime + ", "
				+ "responseDatetime=" + responseDatetime + ", "
				+ "message =" + getMessage()
				+ "]";
	}


	@Override
	public void setRequestTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
		
	}

	@Override
	public void setResponsetime(LocalDateTime responseDatetime) {
		this.responseDatetime = responseDatetime;
	}

	@Override
	public LocalDateTime getRequestDateTime() {
		return this.requestDateTime;
	}

	@Override
	public LocalDateTime getResponseDateTime() {
		return this.responseDatetime;
	}
	
	

}
