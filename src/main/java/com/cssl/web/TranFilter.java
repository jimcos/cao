package com.cssl.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class TranFilter
 */
@WebFilter("/*")
public class TranFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TranFilter() {
        System.out.println("filter构造");
    }

    /**
	 * @see 初始化
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init...........");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter before...");
		chain.doFilter(request, response);
		System.out.println("doFilter after...");
	}

	

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("destroy...");
	}
}
