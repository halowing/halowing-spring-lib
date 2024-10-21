package com.halowing.lib.spring.web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * HTML Escaping 처리를 위한 filter
 * Header 와 Parameter 로 전달되는   html 을 처리함.
 * 
 * filter 등록, WebMvcConfigurer 에 등록
 * 
 * @Bean
	FilterRegistrationBean<HtmlEscapeFilter> htmlEscapeFilter() {
		
		FilterRegistrationBean<HtmlEscapeFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new HtmlEscapeFilter());
		bean.addUrlPatterns("/api/*");
		return bean;
	}
 * 
 * 
 * @author sgkim
 * @since 3.3.2-SNAPSHOT
 */
public class HtmlEscapeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		chain.doFilter(new HtmlEscapeRequestWrapper((HttpServletRequest)request), response);

	}

}
