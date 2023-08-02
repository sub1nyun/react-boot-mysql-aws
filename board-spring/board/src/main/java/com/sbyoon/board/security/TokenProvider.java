package com.sbyoon.board.security;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
	// JWT 생성 및 검증을 위한 키 생성
	private static final String SECURITY_KEY = "jwtseckey!@";
	
	// JWT 생성하는 메서드
	public String create(String userEmail) {
		// 만료날짜를 -> 현재 날짜 + 1시간으로 설정
		Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
		
		// JWT 생성
		// builder는 가변 객체 create 불변 객체 반환 생성 기능은 동일하나 create 방식을 권장
		return Jwts.builder()
				// 암호화 알고리즘과 키를 지정
				.signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
				// JWT의 제목과 생성일, 만료일 지정
				.setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime)
				// 생성
				.compact();
	}
	// JWT 검증 -> 복호화 하는 메서드
	public String validate(String token) {
		// 매개변수로 전달 받은 token을 키를 사용해서 복호화(디코딩)
		Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
		// 복호화된 토큰의 payload에서 제목을 가져옴
		return claims.getSubject();
	}
	
	/*
	 * 1. Jwts.parser(): JWT 파서를 생성합니다. 이 파서는 JWT를 검증하기 위해 사용됩니다.
	 * 2. setSigningKey(SECURITY_KEY): 생성한 JWT 파서에 검증에 사용할 비밀 키 (SECURITY_KEY)를 설정합니다. 이는 JWT를 검증할 때 서명을 검증하는 데 사용됩니다.
	 * 3. parseClaimsJws(token): JWT 파서를 사용하여 전달받은 JWT 토큰(token)을 파싱하여 검증합니다. 이 과정에서 JWT의 서명을 확인하고, 서명이 유효하다면 JWT의 내용(헤더와 페이로드)을 추출합니다.
	 * 4. getBody(): 검증이 완료된 JWT의 페이로드(claims)를 추출합니다. 페이로드에는 JWT에 포함된 정보가 담겨있습니다.
	 * 5. claims.getSubject(): 페이로드에서 제목(Subject) 값을 추출하여 해당 값을 리턴합니다. JWT의 페이로드에는 클라이언트 식별 정보 또는 추가적인 정보가 포함되어 있으며, 이 중에서 Subject 값은 일반적으로 사용자의 고유 식별자(User ID 또는 이메일 등)로 사용됩니다.
	 * 
	 */

}
