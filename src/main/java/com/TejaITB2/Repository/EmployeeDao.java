package com.TejaITB2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TejaITB2.Model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	List<Employee> findBySalary(Double salary);

	List<Employee> findByDept(String fullName);

	List<Employee> findByFirstName(String firstName);

	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

	List<Employee> findBySalaryLessThan(Double salary);

	Boolean existsByFirstName(String fname);
	
	List<Employee> findByFullName(String fullName);

}
