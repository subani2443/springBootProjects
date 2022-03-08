package com.TejaITB2.FactoryDesignPattern;

import java.io.BufferedWriter;
import com.TejaITB2.Model.Employee;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


 
public class TextFile implements IFileGen {

	@Override
	public void genFile(List<Employee> empList, String folder) throws IOException {

	    BufferedWriter writer = new BufferedWriter(new FileWriter(folder+"employee.txt"));
	    
	    for(Employee emp:empList) {
	    writer.write(emp.getEmpId()+"   "+emp.getFullName()+"   "+emp.getLocation()); 
	    writer.newLine();
	   }
	    writer.close();
	}
}
