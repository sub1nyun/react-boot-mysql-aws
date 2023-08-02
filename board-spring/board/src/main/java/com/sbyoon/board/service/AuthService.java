package com.sbyoon.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sbyoon.board.dto.ResponseDto;
import com.sbyoon.board.dto.SignInDto;
import com.sbyoon.board.dto.SignInResponseDto;
import com.sbyoon.board.dto.SignUpDto;
import com.sbyoon.board.entity.UserEntity;
import com.sbyoon.board.repository.UserRepository;
import com.sbyoon.board.security.TokenProvider;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenProvider tokenProvider;
	
	public ResponseDto<?> signUp(SignUpDto dto) {
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		String userPasswordCheck = dto.getUserPasswordCheck();
		
		// email 중복 확인
		try { // 해당 email이 존재하는지 확인하여 boolean 값 반환
			if(userRepository.existsById(userEmail))
				return ResponseDto.setFailed("Existed Email");
		} catch (Exception e) {
			return ResponseDto.setFailed("DataBase Error!");
		}

		// 입력한 비밀번호가 서로 다를 경우 
		if(!userPassword.equals(userPasswordCheck)) 
			return ResponseDto.setFailed("password does not matched!");
		
		// UserEntity 생성
		UserEntity userEntity = new UserEntity(dto);
		// userRepository를 통해 데이터베이스에 Entity 저장
		
		try {
			userRepository.save(userEntity);
		} catch (Exception e) {
			return ResponseDto.setFailed("DataBase Error");
		}
		
		// 성공 시 success response 반환
		return ResponseDto.setSuccess("SignUp Success", null);
	}
	
	// SignInDto 필드 값이 @NotBlank 이기 때문에 유효값 검사를 하지 않음
	// @NotBlank -> null과 "", " " 모두 허용하지 않음
	public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
		String userEmail = dto.getUserEmail();
		System.out.println(userEmail);
		String userPassword = dto.getUserPassword();
		System.out.println(userPassword);
		try {
			boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
			if(!existed) return ResponseDto.setFailed("Sign In Information Does Not Match!");
		}catch (Exception e) {
			return ResponseDto.setFailed("DataBase Error!");
		}
		
		UserEntity userEntity = null;
		
		try {
			userEntity = userRepository.findById(userEmail).orElseThrow(() -> {
				return new IllegalArgumentException("그런 사용자 없어요");
			});
			
		}catch (Exception e) {
			return ResponseDto.setFailed("Database Error");
		}
		
		userEntity.setUserPassword("");
		
		String token = tokenProvider.create(userEmail);
		int exprTime = 3600000;
		
		SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
		
		return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
		
		
	}

}
