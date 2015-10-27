package com.great.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		//System.out.println("Spring - Great Web Hello Controller!");
		return "index";
	}
	
	@RequestMapping("/tasks")
	public String showTasks(){
		//System.out.println("Spring - Great Web Index Controller!");
		return "tasks";
	}

	@RequestMapping("/submission")
	public String showSubmission(){
		//System.out.println("Submission Page Mapping");
		return "submission";
	}
	
	@RequestMapping(value="/projectgroups")
	public String showProjectGroup(Model model,@RequestParam("task_id")int taskId){
		System.out.println("Project Group  Page Mapping");
		//model.addAttribute("task_id", taskId);
		//return "project-groups";
		return "project-groups";
	}
	
}
