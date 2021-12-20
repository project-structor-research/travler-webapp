package com.springframework.travler.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -710991044182811407L;
	private final String jwttoken;
    
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
    
	public String getJwttoken() {
		return jwttoken;
	}
}
