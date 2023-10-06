package com.halowing.lib.spring.data.jpa.converter;

import java.time.LocalDateTime;

import com.halowing.lib.date.DateTimeUtility;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class LocalDatetimeToStringConverter implements AttributeConverter<LocalDateTime, String> {
	
	@Override
	public String convertToDatabaseColumn(LocalDateTime attribute) {
		
		if (attribute == null ) return null;
		
		
		
		return attribute.format(DateTimeUtility.DB_DATE_TIME_FORMATTER);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		return LocalDateTime.parse(dbData, DateTimeUtility.DB_DATE_TIME_FORMATTER);
	}
	
	

}
