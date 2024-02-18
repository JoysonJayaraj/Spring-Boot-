package com.te.ems.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_PROVIDED_FOR_ID;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_PROVIDED_FOR_IDS;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_SAVED;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_CHANGE_PASSWORD;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE__DATA_UPDATED;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE__DATA_DELETED;

import com.te.ems.constant.EmployeeConstant;
import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.DeleteEmployeeDTO;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeRegDTO;
import com.te.ems.response.SuccessResponse;
import com.te.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequestMapping(path = "api/employees")
@RestController
public record EmployeeController(EmployeeService employeeService) {

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "dummy")
	public ResponseEntity<SuccessResponse> dummyApi() {
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder().message("Some dummy response")
				.data(null).timestamp(LocalDateTime.now()).build());
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "")
	public ResponseEntity<SuccessResponse> getEmployee(@RequestParam(name = "eid") String employeeID) {
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				.message(EMPLOYEE_DATA_PROVIDED_FOR_ID + employeeID)
				.data(EmployeeDTO.builder().employeeDOJ(LocalDateTime.now().toString()).build())
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "ids")
	public ResponseEntity<SuccessResponse> getEmployees(@RequestBody List<String> ids) {
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
//				.message(EmployeeConstant.EMPLOYEE_DATA_PROVIDED_FOR_IDS + ids)
				.message(EMPLOYEE_DATA_PROVIDED_FOR_IDS + ids)
				.data(List.of())
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(path = "")
	public ResponseEntity<SuccessResponse> saveEmployee(@RequestBody EmployeeRegDTO regDTO) {
		
		String eid =  employeeService.saveEmployee(regDTO);
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				.message(EMPLOYEE_DATA_SAVED + eid)
				.data(regDTO)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED )
	@PutMapping(path = "change-password")
	public ResponseEntity<SuccessResponse> changePassword(@RequestBody ChangePasswordDTO passwordDTO) {
		
		boolean isUpdated =  employeeService.changeEmployeePassword(passwordDTO);
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				.message(EMPLOYEE_CHANGE_PASSWORD)
				.data(passwordDTO)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping(path = "")
	public ResponseEntity<SuccessResponse> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		
		boolean isUpdated = employeeService.updateEmployee(employeeDTO);
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				.message(EMPLOYEE__DATA_UPDATED)
				.data(employeeDTO)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(path = "")
	public ResponseEntity<SuccessResponse> deleteEmployee(@RequestBody DeleteEmployeeDTO deleteEmployeeDTO) {
		
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				.message(EMPLOYEE__DATA_DELETED)
				.data(deleteEmployeeDTO)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	
	
	
	
	
	
}