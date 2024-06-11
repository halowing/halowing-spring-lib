package com.halowing.lib.spring.web.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.halowing.lib.spring.web.request.PageDTO;

import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseDTO<T,V> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private List<T> contents;
	
	@NotNull
	private PageDTO page;
	
	@NotNull
	private V searchCriteria;
	
	public SearchResponseDTO(@NotNull PageDTO page, @NotNull V searchCriteria) {
		this.page = page;
		this.searchCriteria = searchCriteria;
	}

	@Override
	public String toString() {
		return "SearchResponseDTO [contents=" + contents + ", page=" + page + ", searchCriteria=" + searchCriteria
				+ "]";
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

	public PageDTO getPage() {
		return page;
	}

	public void setPage(PageDTO page) {
		this.page = page;
	}

	public V getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(V searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
}
