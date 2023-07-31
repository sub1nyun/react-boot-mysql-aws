package com.sbyoon.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sbyoon.board.dto.SignUpDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User") // Entity 클래스임을 명시및 이름을 User 사용한다는 의미
@Table(name = "User") // DB에 존재하는 Table명과 일치하는 것으로 매핑
public class UserEntity {
	
	@Id
	private String userEmail;
	private String userPassword;
	private String userNickname;
	private String userPhoneNumber;
	private String userAddress;
	private String userProfile;

	public UserEntity(SignUpDto dto) {
		this.userEmail = dto.getUserEmail();
		this.userPassword = dto.getUserPassword();
		this.userNickname = dto.getUserNickname();
		this.userPhoneNumber = dto.getUserPhoneNumber();
		this.userAddress = dto.getUserAddress() + " " + dto.getUserAddressDetail();
	}
	
}
