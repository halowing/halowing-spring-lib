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
	
	@Min(value = 0)
	private Integer page;

	@NotNull
	@Min(value = 1)
	private Integer pageSize;
	
	public void setLimit(Integer limit) {
		this.pageSize = limit;
	}
	
	@Override
	public String toString() {
		return "PageDTO [totalCount=" + totalCount + ", page=" + page + ", pageSize=" + pageSize + "]";
	}



	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
