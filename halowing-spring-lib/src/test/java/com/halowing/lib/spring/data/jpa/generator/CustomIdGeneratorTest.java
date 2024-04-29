package com.halowing.lib.spring.data.jpa.generator;

import java.util.Properties;

import org.junit.jupiter.api.Test;

public class CustomIdGeneratorTest {

	@Test
	void createId() {
		CustomIdGenerator generator = new CustomIdGenerator();
		
		Properties properties = new Properties();
		properties.setProperty(CustomIdGenerator.PARAMETER_PREFIX, "sgkim");
		properties.setProperty(CustomIdGenerator.PARAMETER_MAX_LENGTH, 64+"");
		
		generator.configure(null, properties, null);
		System.out.println(generator.generate(null, null));
	}
}
