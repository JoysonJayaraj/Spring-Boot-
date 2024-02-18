package com.te.ems.service;

import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeRegDTO;

public interface EmployeeService {

	String saveEmployee(EmployeeRegDTO regDTO);

	boolean changeEmployeePassword(ChangePasswordDTO passwordDTO);

	boolean updateEmployee(EmployeeDTO employeeDTO);

}
