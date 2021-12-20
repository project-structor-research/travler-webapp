package com.springframework.travler.models;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 1740919303625615369L;
	private String username;
	private String password;
	
	public JwtRequest() {
		
	}
	
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
