package com.te.ems.service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeRegDTO;
import com.te.ems.entity.AppUser;
import com.te.ems.entity.Employee;
import com.te.ems.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(EmployeeRegDTO regDTO) {

		Random random = new Random();
		Employee employee = Employee.builder().id(UUID.randomUUID().toString())
				.employeeId("TYSS" + random.nextInt(100000)).employeeFN(regDTO.getEmployeeFN())
				.employeeLN(regDTO.getEmployeeLN()).employeeDOJ(LocalDateTime.now().toString()).build();

		AppUser appUser = AppUser.builder().username(employee.getEmployeeId()).password(regDTO.getPassword()).build();

		return employeeRepository.saveEmployee(employee, appUser);
	}

	@Override
	public boolean changeEmployeePassword(ChangePasswordDTO passwordDTO) {
		if (!passwordDTO.newPassword().equals(passwordDTO.reEnterNewPassword())) {
			System.out.println("Password retype mismatch ");
			return false;
		}

		return employeeRepository.changeEmployeePassword(passwordDTO.employeeId(),
				passwordDTO.oldPassword(),passwordDTO.newPassword());
	}
	
	@Override
	public boolean updateEmployee(EmployeeDTO employeeDTO) {
		return false;
	}

}
