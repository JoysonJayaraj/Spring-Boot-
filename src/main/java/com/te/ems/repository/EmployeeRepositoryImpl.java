package com.te.ems.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.te.ems.entity.AppUser;
import com.te.ems.entity.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MySQL_BOOT");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public String saveEmployee(Employee employee, AppUser appUser) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityManager.persist(appUser);
		entityTransaction.commit();

		return employee.getEmployeeId();
	}

	@Override
	public boolean changeEmployeePassword(String employeeId, String oldPassword, String newPassword) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
//		Employee employee = entityManager.find(Employee.class, employeeId);
		AppUser appUser = entityManager.find(AppUser.class, employeeId);
		if (!appUser.getPassword().equals(oldPassword)) {
			System.out.println("Password Mismatch");
			return false;
		}
		appUser.setPassword(newPassword);
		entityManager.persist(appUser);
		entityTransaction.commit();
		return true;
	}

}
