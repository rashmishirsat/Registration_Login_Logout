package com.springjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springjava.dto.UserRegisterDto;
import com.springjava.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public UserRegisterDto userRegistrationDto() {
		return new UserRegisterDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegisterDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}