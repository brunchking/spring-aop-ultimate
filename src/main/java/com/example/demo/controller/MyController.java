package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@GetMapping("/hello")
	public String helloController() throws Exception {
		System.out.println("Hello world");
		boolean isSuccess = false;
		if (!isSuccess) {
			throw new Exception();
		}
		return "Hello world";
	}
}
