package com.sbyoon.board.contoroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbyoon.board.dto.ResponseDto;
import com.sbyoon.board.dto.SignInDto;
import com.sbyoon.board.dto.SignInResponseDto;
import com.sbyoon.board.dto.SignUpDto;
import com.sbyoon.board.service.AuthService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = "/signUp")
	public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
		ResponseDto<?> result = authService.signUp(requestBody);
		return result;
	}
	
	@PostMapping(value = "/signIn")
	public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
		ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
		return result;
	}
	
	
}
