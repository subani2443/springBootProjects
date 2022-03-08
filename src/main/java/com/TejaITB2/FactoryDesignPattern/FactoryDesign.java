package com.TejaITB2.FactoryDesignPattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.TejaITB2.Model.Employee;

public class FactoryDesign {
	List<IFileGen> commands = new ArrayList<IFileGen>();
	public FactoryDesign(String types) {
		String[] fileTypes=types.split(",");
		for(String file:fileTypes) {
			 IFileGen fileGen=FileGenUtil.fileGeneration(file);
			 commands.add(fileGen);
		}
	}

	public void executeCommand(List<Employee> listEmp, String folder) throws IOException {
		for(IFileGen iFileGen:commands) {
			iFileGen.genFile(listEmp, folder);
		}
		
	}
}
