package com.springframework.travler.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -710991044182811407L;
	private final String accessToken;
	private final String refreshToken;
	private final Long refreshTokenExpireTime;
	private final String username;
    
    public JwtResponse(String accessToken, String refreshToken, Long refreshTokenExpireTime, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.username = username;
    }
    
	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	
	public Long getRefreshTokenExpireTime() {
		return refreshTokenExpireTime;
	}

	public String getUsername() {
		return username;
	}
}
