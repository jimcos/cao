package com.cssl.mvcconfig;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cssl.interceptor.MyInterceptor;

@Component
public class MyConfiger implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		MyInterceptor mi = new MyInterceptor();
		registry.addInterceptor(mi)
				.addPathPatterns("/**")
				.excludePathPatterns("/static/**");
	}
	
	

}
