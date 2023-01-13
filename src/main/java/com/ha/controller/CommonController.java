package com.ha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	

	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("accessDenied() 호출");
		log.info("접근 권한 없음"+ auth);
		
		model.addAttribute("msg", "Access Denied");
		model.addAttribute("auth", auth);
	}
	
	@GetMapping("/customLogin")
	public void myLoginGET() {
		log.info("myLoginGET() 호출");
	}
	 
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("logoutGET() 호출");
	}
}
