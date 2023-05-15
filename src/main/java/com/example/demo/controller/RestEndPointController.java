package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/")
public class RestEndPointController {

	@GetMapping("/hello")
	private String hello() {
		return "Well come to the world";

	}

}
