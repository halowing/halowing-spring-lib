package com.halowing.lib.spring.data.jpa.converter;

import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.halowing.lib.date.DateTimeUtility;


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
