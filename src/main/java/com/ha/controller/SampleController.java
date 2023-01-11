package com.ha.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sample/*")
public class SampleController {

	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	
	@GetMapping(value="/all")
	public void allGet() {
		log.info("allGet() 호출");
	}
	
	@GetMapping(value="/member")
	public void memberGet() {
		log.info("memberGet() 호출");
	}
	
	@GetMapping(value="/admin")
	public void amdinGet() {
		log.info("amdinGet() 호출");
	}
	
	
	
}
