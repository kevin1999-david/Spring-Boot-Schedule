package com.bolsaideas.springboot.sheduleinterceptor.app.intercpetor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("closing")
public class InterceptorSheduleClosing implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(InterceptorSheduleClosing.class);
	
	@Value("${config.shedule.opening}")
	private Integer opening;
	
	@Value("${config.shedule.closing}")
	private Integer closing;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Calendar calendar = Calendar.getInstance();
	
		
		Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (!(hour >= opening && hour < closing)) {
			logger.info("INTERCEPTOR CLOSING");
			return true;
		}
		response.sendRedirect(request.getContextPath().concat("/index"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		
	}
	
	
	

}
