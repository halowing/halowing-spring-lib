package com.halowing.lib.spring.context.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(MessageSourceConfiguration.class);

//    @Bean
    MessageSource messageSource() {
		log.debug("MessageSource inited.");
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames("classpath:/messages/messages");
		ms.setDefaultEncoding("UTF-8");
//		ms.setFallbackToSystemLocale(false);
		return ms;
	}

    @Bean
    MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
    	log.debug("MessageSourceAccessor inited.");
		return new MessageSourceAccessor(MessageSourceFactory.getMessageSource());
	}

}
