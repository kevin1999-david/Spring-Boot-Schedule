package com.bolsaideas.springboot.sheduleinterceptor.app.intercpetor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("shedule")
public class InterceptorShedule implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(InterceptorShedule.class);

	@Value("${config.shedule.opening}")
	private Integer opening;
	@Value("${config.shedule.closing}")
	private Integer closing;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Context path: " + request.getRequestURI());
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= opening && hour < closing) {
			StringBuilder msg = new StringBuilder("Welcome to customers hours");
			msg.append(", opening hours from at ");
			msg.append(opening);
			msg.append(" to");
			msg.append(closing);
			request.setAttribute("message", msg.toString());
			return true;
		}
		response.sendRedirect(request.getContextPath().concat("/closing"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String msg = (String) request.getAttribute("message");
		if (modelAndView != null && handler instanceof HandlerMethod) {
			modelAndView.addObject("shedule", msg);
		}

	}

}
