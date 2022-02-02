package com.springframework.travler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springframework.travler.entity.Users;
import com.springframework.travler.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username);
        if(user != null) {
        	List<String> authorities = new ArrayList<String>();
            authorities.add(user.getRole());
            user.setRoles(authorities);
        }
        return createUserDetails(user);
    }

    private UserDetails createUserDetails(Users users) {
        return new User(users.getUsername(), users.getPassword(), users.getAuthorities());
    }

}
