package com.halowing.lib.spring.web.response;

import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.halowing.lib.spring.web.request.PageDTO;

import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseDTO<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private List<E> data;
	
	@NotNull
	private PageDTO page;
	
	@NotNull
	private E request;

	@Override
	public String toString() {
		return "SearchResponseDTO [data=" + data + ", page=" + page + ", request=" + request + "]";
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public PageDTO getPage() {
		return page;
	}

	public void setPage(PageDTO page) {
		this.page = page;
	}

	public E getRequest() {
		return request;
	}

	public void setRequest(E request) {
		this.request = request;
	}
	

	
}
