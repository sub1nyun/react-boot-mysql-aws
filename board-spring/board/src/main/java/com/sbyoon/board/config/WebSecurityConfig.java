package com.sbyoon.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.sbyoon.board.filter.JwtAuthencationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired private JwtAuthencationFilter jwtAuthencationFilter;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		// cors 정책 (현재는 Application에서 작업을 해뒀으므로 기본 설정 사용)
			.cors(Customizer.withDefaults())
			// csrf 대책 (현재는 CSRF에 대한 대책 비활성화)
			.csrf((csrf)-> csrf.disable())
			// Basic 인증 (현재는 Bearer token 인증방법을 사용하기 때문에 비활성화)
			.httpBasic((httpBasic)->httpBasic.disable())
			// 세선 기반 인증 (현재는 Session 기반 인증을 사용하지 않기 때문에 상태를 없앰) 
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			// '/', '/api/auth/' 모듈에 대해서는 모두 허용 (인증을 하지않고 사용 가능)
			.authorizeHttpRequests().antMatchers("/", "/api/auth/**/").permitAll()
			// 나머지 모든 Request에 대해서는 인증된 사용자만 사용이 가능함
			.anyRequest().authenticated();
		
		http.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
