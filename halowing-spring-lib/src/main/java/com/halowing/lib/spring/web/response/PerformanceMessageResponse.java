package com.halowing.lib.spring.web.response;

import java.time.LocalDateTime;

public interface PerformanceMessageResponse extends MessageResponse {

	public void setRequestTime(LocalDateTime requestDateTime);
	public void setResponsetime(LocalDateTime responseDatetime);
	public LocalDateTime getRequestDateTime();
	public LocalDateTime getResponseDateTime();
}
