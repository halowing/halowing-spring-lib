package com.halowing.lib.spring.data.jpa.generator;

import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.halowing.lib.string.StringUtility;

public class CustomIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_LENGTH = 15;
	public static final String PARAMETER_PREFIX = "prefix";
	public static final String PARAMETER_MAX_LENGTH = "maxLength";
	
	private String prefix;
	private Integer maxLength;
	
	@Override
	public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
		
		String prefix = ConfigurationHelper.getString(PARAMETER_PREFIX, parameters);
		this.prefix = StringUtility.isBlank(prefix) ? "": prefix;
		
		Integer maxLength =  ConfigurationHelper.getInteger(PARAMETER_MAX_LENGTH, parameters) ;

		int minLength = prefix.length() + DEFAULT_LENGTH;
		this.maxLength = maxLength == null ? minLength :
			maxLength < minLength ? minLength : maxLength;
	}

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		
		return String.format("%s%0" + (maxLength - prefix.length()) + "d", prefix, System.currentTimeMillis());
	}

}
