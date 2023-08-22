package com.halowing.lib.spring.context.support;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;

@SpringBootTest(
		properties = {
				"logging.level.root=info","logging.level.com.halowing.lib=debug"
		},
		classes = {
				MessageSourceFactory.class, MessageSourceConfiguration.class
		}
)
public class MessageSourceFactoryTest {

	private static final Logger log = LoggerFactory.getLogger(MessageSourceFactoryTest.class);
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
	
	@Test
	public void test() {
		
		log.debug("hello world");
		String msg = null;
		
		try {
			msg = messageSourceAccessor.getMessage("response.success.created");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		log.debug("msg = {}",msg);
		
	}
	
	
}
