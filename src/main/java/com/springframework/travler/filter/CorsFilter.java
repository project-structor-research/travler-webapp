package com.springframework.travler.filter;

import java.io.IOException;
//import java.util.Optional;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CorsFilter implements Filter {
	
//	private boolean acceptCors = false;
	private String allowOrigin = "*";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		this.acceptCors = Boolean.parseBoolean(
//				(String) Optional.ofNullable(DzGlobalVariable.tryGetValue("application.filter.cors.accept"))
//						.orElse(Optional.ofNullable(filterConfig.getInitParameter("acceptCors")).orElse("false")));
		this.allowOrigin = (String) Optional
				.ofNullable(System.getProperty("application.filter.cors.allow-origin"))
				.orElse(Optional.ofNullable(filterConfig.getInitParameter("allowOrigin")).orElse("*"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequestWrapper req = new HttpServletRequestWrapper((HttpServletRequest) request);
		HttpServletResponseWrapper res = new HttpServletResponseWrapper((HttpServletResponse) response);
		
//		res.setHeader("Access-Control-Allow-Origin", this.allowOrigin);
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
		if ("OPTIONS".equals(req.getMethod())) {
			res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			res.setHeader("Access-Control-Max-Age", "3600");
			res.setHeader("Access-Control-Allow-Headers",
					"authorization, content-type, xsrf-token, x-authenticate-token");
			res.setStatus(200);
			return;
		}
		
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		
	}
}
