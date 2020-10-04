package com.sporty.shoes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChangePasswordRequest {
	
	private String oldPassword;
	private String newPassword;

}
