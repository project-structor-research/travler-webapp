package com.springframework.travler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springframework.travler.jwt.JwtAuthenticationFilter;
import com.springframework.travler.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.formLogin().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.authorizeRequests()	
			.antMatchers("/api/sign-up", "/api/login", "/api/authority", "/api/reissue", "/api/logout").permitAll()
			.antMatchers("/api/userTest").hasRole("USER")
			.antMatchers("/api/adminTest").hasRole("ADMIN")
			.anyRequest().authenticated();
		
		httpSecurity.logout().logoutUrl("/api/logout").permitAll()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
		
		httpSecurity
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class);
		// JwtAuthenticationFilter를 UsernamePasswordAuthentictaionFilter 전에 적용시킨다.
	}

	// 암호화에 필요한 PasswordEncoder Bean 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
