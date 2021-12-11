package com.springframework.travler.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String reqUrl = request.getRequestURL().toString();
		System.out.println(reqUrl);
		System.out.println(request.getContextPath());
		
		if("".equals(email) && "".equals(password)) {
			response.sendRedirect("/login");
			return false;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("email", email);
		
		return super.preHandle(request, response, handler);
	}	
}
