package com.sbyoon.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbyoon.board.dto.ResponseDto;
import com.sbyoon.board.dto.SignUpDto;
import com.sbyoon.board.entity.UserEntity;
import com.sbyoon.board.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseDto<?> signUp(SignUpDto dto) {
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		String userPasswordCheck = dto.getUserPasswordCheck();
		
		// email 중복 확인
		try {
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

}