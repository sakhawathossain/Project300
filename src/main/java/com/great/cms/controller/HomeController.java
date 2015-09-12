package com.great.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("organization")

public class HomeController {
	
	@RequestMapping("/hello")
	public String showHello(){
		//System.out.println("Spring - Great Web Hello Controller!");
		return "hello";
	}
	
	@RequestMapping("/")
	public String showIndex(){
		//System.out.println("Spring - Great Web Index Controller!");
		return "tasks";
	}

}
