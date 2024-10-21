package com.halowing.lib.spring.web.jacson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Request Body로 전송되는 JSON String에 있는 HTML escaping 처리
 * 
 * @JsonDeserialize(using = HtmlEscapingStringDeSerializer.class)
	private String message;
 * 
 * @author sgkim
 * @since 3.3.2-SNAPSHOT
 */
public class HtmlEscapingStringDeSerializer extends JsonDeserializer<String> {
	
	private final static String DEFAULT_ENCODING = StandardCharsets.UTF_8.name();
//	private static final Logger log = LoggerFactory.getLogger(HtmlEscapingStringDeSerializer.class);
	

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String in = p.getText();
		String out = HtmlUtils.htmlEscape(in , DEFAULT_ENCODING);
//		log.debug("in = {}, out = {}", in, out);
		return out;
	}

}
