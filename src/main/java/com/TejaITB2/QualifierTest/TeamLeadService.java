package com.TejaITB2.QualifierTest;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("teamLead")
@Primary
public class TeamLeadService implements EmployeService {

	@Override
	public String getDesignation() {
		
		return "TeamLead";
	}
	 
}
