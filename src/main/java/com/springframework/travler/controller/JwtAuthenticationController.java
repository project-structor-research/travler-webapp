package com.springframework.travler.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.travler.models.JwtRequest;
import com.springframework.travler.models.JwtResponse;
import com.springframework.travler.models.Response;
import com.springframework.travler.service.JwtUserDetailsService;
import com.springframework.travler.util.JwtTokenUtil;

/*
 * 사용자가 입력한 id, pw를 body에 넣어서 POST API mapping /authenticate
 * 사용자의 id, pw를 검증
 * jwtTokenUtil을 호출해 Token을 생성하고 JwtResponse에 Token을 담아 return ResponseEntity
 * */
@RestController
public class JwtAuthenticationController {
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    	Response response = new Response();
    	
    	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());

        JwtResponse jwtResponse = jwtTokenUtil.generateToken(userDetails);

        // Redis의 String 자료구조는 opsForValue 메소드를 사용한다.
        // .set(key, value)
        redisTemplate.opsForValue()
        	.set("RT:" + userDetails.getUsername(), jwtResponse.getRefreshToken(), jwtResponse.getRefreshTokenExpireTime(), TimeUnit.MILLISECONDS);
        
        return response.success(jwtResponse, "로그인에 성공했습니다.", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(JwtRequest.Logout logout) {
    	Response response = new Response();
    	// 1. Access Token에서 User name을 가져옵니다.
    	final UserDetails userDetails = userDetailsService
                .loadUserByUsername(logout.getUsername());
    	// 2. Access Token 검증
    	if(!jwtTokenUtil.validateToken(logout.getAccessToken(), userDetails)) {
    		return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
    	}
    	// 3. Redis 에서 해당 User email로 저장된 Refresh Token이 있는지 여부를 확인
    	if (redisTemplate.opsForValue().get("RT:" + userDetails.getUsername()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + userDetails.getUsername());
        }
    	// 4. 해당 Access Token 유효시간 가지고 와서 BlackList로 저장하기
    	Long expiration = jwtTokenUtil.getExpirationDateFromToken(logout.getAccessToken()).getTime();
    	Long now = new Date().getTime();
    	redisTemplate.opsForValue()
    		.set(userDetails.getUsername(), (expiration - now), now, TimeUnit.MILLISECONDS);
    	
    	return response.success("로그아웃 되었습니다.");
    }
    
    private void authenticate(String username, String password) throws Exception {
        try {
        	// loadUserByUsername 실행
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
