package com.ant.gc.dto;

import lombok.Data;

@Data
public class PasswordDto {
	
	private Integer id;
	private String oldPassword;
	private String newPassword;
}
