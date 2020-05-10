package com.swb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String test(){
		System.out.println("test");
		return "test";
	}
	
}
