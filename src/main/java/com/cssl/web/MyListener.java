package com.cssl.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
	
	private ServletContext application;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("application初始化");
		this.application = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("application销毁");
	}
	
	
	

}
