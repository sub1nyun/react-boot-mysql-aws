package com.sbyoon.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set") // set이라는 고정된 이름으로 사용한다는 의미
public class ResponseDto<D> {
	
	private boolean result;
	private String message;
	private D data;
	
	// 통신에 성공했을때 사용
	public static <D> ResponseDto<D> setSuccess(String message, D data) {
		return ResponseDto.set(true, message, data);
			 //new ResponseDto<D>(true, message, data);
	}	
	// 통신에 실패했을때 사용
	public static <D> ResponseDto<D> setFailed(String message) {
		return ResponseDto.set(false, message, null);
	}

}
