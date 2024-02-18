package com.te.ems.dto;

import java.time.LocalDateTime;

import com.te.ems.response.SuccessResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {
	private String ID;
	private String employeeID;
	private String employeeFN;
	private String employeeLN;
	private String employeeDOJ;

}
