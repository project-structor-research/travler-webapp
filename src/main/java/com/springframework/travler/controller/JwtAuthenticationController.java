package com.springframework.travler.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
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
