package com.springframework.travler.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * DB에서 UserDetail를 얻어와 AuthenticationManager에게 제공하는 역할
 * */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	/* 해당 메소드에서 
	 * DB 사용자 정보가져오기
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user_id".equals(username)) {
            return new User("user_id", "$2a$10$m/enYHaLsCwH2dKMUAtQp.ksGOA6lq7Fd2pnMb4L.yT4GyeAPRPyS",
                new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
	}
	
//	@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usersRepository.findByEmail(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
//    }
//
//    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
//    private UserDetails createUserDetails(Users users) {
//        return new User(users.getUsername(), users.getPassword(), users.getAuthorities());
//    }
}
