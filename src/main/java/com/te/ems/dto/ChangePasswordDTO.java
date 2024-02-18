package com.te.ems.dto;

public record ChangePasswordDTO(String employeeId,
		String oldPassword,
		String newPassword,
		String reEnterNewPassword) {

}
