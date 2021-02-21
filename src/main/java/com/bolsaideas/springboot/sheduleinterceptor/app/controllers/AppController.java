package com.bolsaideas.springboot.sheduleinterceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@Value("${config.shedule.opening}")
	private Integer opening;
	@Value("${config.shedule.closing}")
	private Integer closing;

	@GetMapping({ "/", "/index", "" })
	public String index(Model model) {
		model.addAttribute("title", "Welcome to customer hours");
		return "index";
	}

	@GetMapping("/closing")
	public String losing(Model model) {
		StringBuilder msg = new StringBuilder("Closing, please visit us from");
		msg.append(opening);
		msg.append(" to");
		msg.append(closing);
		model.addAttribute("title", "Out of business hours");
		model.addAttribute("message", msg.toString());
		return "closing";
	}
}
