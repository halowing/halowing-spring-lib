package com.halowing.lib.spring.web.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * HttpServletRequest 로 전달되는 Header와 Parameter 에 대한 HTML Escaping 처리를 위한 Wrapper 
 * 
 * @author sgkim
 * @since 3.3.2-SNAPSHOT
 */
public class HtmlEscapeRequestWrapper extends HttpServletRequestWrapper {
	
	private static final Logger log = LoggerFactory.getLogger(HtmlEscapeRequestWrapper.class);
	
	private Map<String, String[]> parameterMap = null;

	public HtmlEscapeRequestWrapper(HttpServletRequest request) {
		super(request);
		
//		log.debug("\n pathInfo = {}\n contextPath = {}\n pathTranslated = {}\n servletPath = {}",request.getPathInfo(), request.getContextPath(), request.getPathTranslated(), request.getServletPath());
//		log.debug("\n characterEncoding = {}",request.getCharacterEncoding());
		this.parameterMap = this.escapeParameterMap(request);
		
	}
	
	
	
	@Override
	public String getParameter(String name) {
//		log.debug("called ##################### : {}", name);
		
		String[] parameters = this.parameterMap.get(name);
		if(parameters == null || parameters.length == 0)
			return null;
		
		return parameters[0];
	}

	@Override
	public Map<String, String[]> getParameterMap() {
//		log.debug("called #####################");
		return this.parameterMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
//		log.debug("called #####################");
		Set<String> set = this.parameterMap.keySet();
		if(set == null)
			return null;
		
		return Collections.enumeration(set);
	}

	@Override
	public String[] getParameterValues(String name) {
//		log.debug("called #####################");
		return this.parameterMap.get(name);
	}

//	@Override
//	public ServletInputStream getInputStream() throws IOException {
//		
//		log.debug("called #####################");
//		
//		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
//		
//		return new ServletInputStream() {
//			
//			private boolean finished = false;
//			
//			@Override
//			public int read() throws IOException {
//				
//				int out = byteArrayInputStream.read();
//				if(out == -1)
//					this.finished = true;
//				
//				return out;
//			}
//			
//			@Override
//			public void setReadListener(ReadListener listener) {
//				throw new UnsupportedOperationException();
//			}
//			
//			@Override
//			public boolean isReady() {
//				return true;
//			}
//			
//			@Override
//			public boolean isFinished() {
//				return finished;
//			}
//		};
//	}

//	@Override
//	public BufferedReader getReader() throws IOException {
//		log.debug("called #####################");
//		return new BufferedReader(new InputStreamReader(this.getInputStream()));
//	}

	
	

	@Override
	public String getHeader(String name) {
		
		String header = super.getHeader(name);
		if(header == null )
			return header;
		
		return HtmlUtils.htmlEscape(header, super.getCharacterEncoding()) ;
	}



	@Override
	public Enumeration<String> getHeaders(String name) {
		
		Enumeration<String> as = super.getHeaders(name);
		
		if(!as.hasMoreElements())
			return as;
		
		Set<String> isSet = new HashSet<>();
		
		while(as.hasMoreElements()) {
			 isSet.add(HtmlUtils.htmlEscape(as.nextElement(), super.getCharacterEncoding()) ) ;
		}
		
		return Collections.enumeration(isSet);
	}


/*
 * Body를 Escape 한경우 HttpMessageConverter에서 '&'를 파싱하지 못하고 org.springframework.http.converter.HttpMessageNotReadableException 를 발생함. 
 * Model DTO에 PropertyEditor를 이용해볼 예정임.
 */
//	private String escapeBody(HttpServletRequest request) {
//		
//		log.debug("escapeBody start ");
//		
//		if( !MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType()) ) 
//			return null;
//		
//		try(BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
//			
//			StringBuffer sb = new StringBuffer();
//			String str = null;
//			while((str = reader.readLine()) != null) {
//				sb.append(str);
//			}
//			
//			String escaped = HtmlUtils.htmlEscape(sb.toString(), request.getCharacterEncoding());
//			
//			log.debug("as = {}, is = {}", sb.toString(), escaped);
//			
//			return escaped ;
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			log.debug("escapeBody end ");
//		}
//	}

	private Map<String, String[]> escapeParameterMap(HttpServletRequest request) {
		
//		log.debug("escapeParameterMap start , keyset = {}", request.getParameterMap().keySet());
		
		if(request.getParameterMap() == null || request.getParameterMap().size() == 0)
			return request.getParameterMap();
		
		try {
			Map<String, String[]> out = new HashMap<String, String[]>();
			
			for( String key : request.getParameterMap().keySet()) {
				String[] values = request.getParameterValues(key);
				String[] escaped = 
				Arrays.asList(values).stream()
					.map(str -> {
						String r = HtmlUtils.htmlEscape(str, request.getCharacterEncoding());
//						log.debug("intput = {}, escaped = {}", str, r);
						return  r;
					})
					.collect(Collectors.toList())
					.toArray(new String[values.length])
					;
				
//				log.debug("key = {}, escaped = {}", key, escaped);
				
				out.put(key, escaped);
				
			}
			return out;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
//			throw new SimpleSpringWebApplicationException(e);
//			e.printStackTrace();
			return request.getParameterMap();
		} finally {
//			log.debug("escapeParameterMap end");
		}
	}



}
