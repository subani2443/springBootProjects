package com.TejaITB2.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.TejaITB2.Model.Employee;
import com.TejaITB2.Repository.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;

	public void saveEmployee(Employee employee) {
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String fullName = firstName.concat(lastName);
		employee.setFullName(fullName);
		employeeDao.save(employee);
	}

	public void updateEmployee(Employee employee) {
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String fullName = firstName.concat(lastName);
		employee.setFullName(fullName);
		employeeDao.save(employee);

	}

	public void deleteEmployee(Integer eid) {
		employeeDao.deleteById(eid);

	}

	public Optional<Employee> getEmployee(Integer eid) {
		Optional<Employee> employee = employeeDao.findById(eid);
		return employee;

	}

	public List<Employee> getAllData() {
		List<Employee> list = employeeDao.findAll();
		return list;
	}

	public List<Employee> getDataBySalary(Double salary) {
		List<Employee> list = employeeDao.findBySalary(salary);
		return list;
	}

	public Page<Employee> getPagination(Integer pageNum, Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
		Page<Employee> pageRecords = employeeDao.findAll(pageRequest);
		return pageRecords;
	}

	public List<Employee> getDataBydept(String dept) {
		List<Employee> list = employeeDao.findByDept(dept);
		return list;
	}

	public List<Employee> getDataByFirstName(String firstName) {
		List<Employee> list = employeeDao.findByFirstName(firstName);
		return list;
	}

	public List<Employee> getDataByFirstNameAndLastName(String firstName, String lastName) {
		List<Employee> list = employeeDao.findByFirstNameAndLastName(firstName, lastName);
		return list;
	}

	public List<Employee> between(Double minSalary, Double maxSalary) {
		List<Employee> list = employeeDao.findBySalaryBetween(minSalary, maxSalary);
		return list;
	}

	public List<Employee> lessthan(Double salary) {
		List<Employee> list = employeeDao.findBySalaryLessThan(salary);
		return list;
	}

	public Boolean userAvailableOrNot(String fname) {
		boolean userStatus = employeeDao.existsByFirstName(fname);
		return userStatus;
	}
	
	public List<Employee> listAll() {
		return employeeDao.findAll(Sort.by("empId").ascending());
	}
	
	public List<Employee> listAll1() {
		return employeeDao.findAll(Sort.by("empId").ascending());
		}
}
