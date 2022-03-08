package com.TejaITB2.QualifierTest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service

public interface EmployeService {
	public String getDesignation();
}
