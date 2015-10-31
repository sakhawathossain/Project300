package com.great.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.great.cms.db.entity.User;
import com.great.cms.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/checklogin", method = RequestMethod.POST)
	public String checkLogin(@RequestParam("user_name") String username,
			@RequestParam("password") String password, Model model,
			HttpServletRequest request) {

		System.out.println("Username and Password: " + username + " "
				+ password);
		User user = null;

		user = userService.getUserByName(username);
		if (user == null) {
			model.addAttribute("message", "Invalid usrname or password");
			return "login";
		}

		else if (user.getPassword().equals(password)) {

			System.out.println("User Id: " + user.getUserId());
			model.addAttribute("userId", user.getUserId());
			model.addAttribute("username", username);
			// request.setAttribute("userId",user.getUserId().toString());

			System.out.println("Username: " + username);
			System.out.println("Password: " + password);
			return "course";
		}

		else {
			System.out.println("Not a successful login");
			model.addAttribute("message", "Invalid usrname or password");
			return "login";
		}

	}

}