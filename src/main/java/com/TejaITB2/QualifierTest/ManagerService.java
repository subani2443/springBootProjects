package com.TejaITB2.QualifierTest;

import org.springframework.stereotype.Service;

@Service("manger")
public class ManagerService implements EmployeService {

	@Override
	public String getDesignation() {

		return "manager";
	}

}
