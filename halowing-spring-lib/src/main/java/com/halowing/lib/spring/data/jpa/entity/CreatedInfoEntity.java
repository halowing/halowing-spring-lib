package com.halowing.lib.spring.data.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.halowing.lib.spring.data.jpa.converter.LocalDatetimeToStringConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class CreatedInfoEntity {
	
	public static final String DEFAULT_CREATOR_COLUMN_NAME = "creator";
	public static final String DEFAULT_DATE_TIME_COLUMN_NAME = "created_date_time";

	@CreatedBy
	@Column(name = DEFAULT_CREATOR_COLUMN_NAME , updatable = false)
	private String creator;
	
	@CreatedDate
	@Convert(converter = LocalDatetimeToStringConverter.class)
	@Column(name = DEFAULT_DATE_TIME_COLUMN_NAME, updatable = false)
	private LocalDateTime createdDateTime;
	
	public CreatedInfoEntity() {}
	
	@Override
	public String toString() {
		return "CreatedInfoEntity [createdDateTime=" + createdDateTime + ", creator=" + creator + "]";
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	
	
}
