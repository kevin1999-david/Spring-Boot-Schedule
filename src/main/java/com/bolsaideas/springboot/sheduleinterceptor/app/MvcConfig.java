package com.bolsaideas.springboot.sheduleinterceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("shedule")
	private HandlerInterceptor shedule;
	@Autowired
	@Qualifier("closing")
	private HandlerInterceptor closing;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shedule).excludePathPatterns("/closing");
		registry.addInterceptor(closing).excludePathPatterns("/index", "", "/");
	}

}
