package com.TejaITB2.QualifierTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qualifier")
public class EmployeeDataControl {
	@Autowired
	@Qualifier("manger")
	EmployeService employeService;
	@RequestMapping(value="/getDesignation", method=RequestMethod.GET)
	public String getDesignation() {
		String designation=employeService.getDesignation();
		return designation;
		
	}
}
