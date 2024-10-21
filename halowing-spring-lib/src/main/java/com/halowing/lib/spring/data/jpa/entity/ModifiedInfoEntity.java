package com.halowing.lib.spring.data.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.halowing.lib.spring.data.jpa.converter.LocalDatetimeToStringConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
/**
 * 콘텐트의 수정일을 저장하는  Entity.
 */
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class ModifiedInfoEntity extends CreatedInfoEntity{
	
	public static final String DEFAULT_MODIFIER_COLUMN_NAME = "modifier";
	public static final String DEFAULT_DATE_TIME_COLUMN_NAME = "modified_date_time";

	@LastModifiedDate
	@Convert(converter = LocalDatetimeToStringConverter.class)
	@Column(name = DEFAULT_DATE_TIME_COLUMN_NAME, updatable = true)
	private LocalDateTime modifiedDateTime;
	
	@LastModifiedBy
	@Column(name = DEFAULT_MODIFIER_COLUMN_NAME, updatable = true)
	private String modifier;

	public ModifiedInfoEntity() {
		super();
	}

	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	
}
