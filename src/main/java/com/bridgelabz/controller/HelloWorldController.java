package com.bridgelabz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping("/hello")
public String home() {
		return "Hello frens!";
	}
}
