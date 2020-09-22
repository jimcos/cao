package com.cssl.mvcconfig;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyAdvice {
	
	@ExceptionHandler(Exception.class)
	public String doException(Exception e) {
		System.out.println("error:"+e.getMessage());
		return "error";
	}

}
