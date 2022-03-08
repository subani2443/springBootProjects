package com.TejaITB2.FactoryDesignPattern;

import java.io.IOException;
import java.util.List;

import com.TejaITB2.Model.Employee;

public interface IFileGen {
	public void genFile(List<Employee> empList, String folder) throws IOException;
}
