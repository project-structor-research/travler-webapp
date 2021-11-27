package com.springframework.webapp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String email = null;
		String password = null;

		if(email == null && password == null) {
			response.sendRedirect("/forgot");
			return false;
		}
		// session 처리
		return false;
//		System.err.println("Session Create #######");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("userEmail", email);
//		session.setAttribute("userPassword", password);
//		response.sendRedirect(ip + "/forgot");
//		return false;
	}	
}
