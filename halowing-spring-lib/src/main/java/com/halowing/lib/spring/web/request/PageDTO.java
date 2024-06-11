package com.halowing.lib.spring.web.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * PageDTO include information of request page
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long totalCount;
	
	@NotNull
	@Min(value = 1)
	private Integer pageNumber;

	@NotNull
	@Min(value = 1)
	private Integer pageSize;
	
	public void setLimit(Integer limit) {
		this.pageSize = limit;
	}

	@Override
	public String toString() {
		return "PageDTO [totalCount=" + totalCount + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + "]";
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
