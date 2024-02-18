package com.te.ems.repository;

import com.te.ems.entity.AppUser;
import com.te.ems.entity.Employee;

public interface EmployeeRepository {

	String saveEmployee(Employee employee, AppUser appUser);

	boolean changeEmployeePassword(String employeeId, String oldPassword, String newPassword);

}
