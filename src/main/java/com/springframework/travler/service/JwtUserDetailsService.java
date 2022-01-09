package com.springframework.travler.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springframework.travler.models.Member;
import com.springframework.travler.repository.MemberRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.list();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(member != null) {			
			if (username.equals(member.getUsername())) {
				return new User(member.getUsername(), passwordEncoder.encode(member.getPassword()), new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	
}
