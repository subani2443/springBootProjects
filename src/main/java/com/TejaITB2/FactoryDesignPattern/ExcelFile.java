package com.TejaITB2.FactoryDesignPattern;

import java.io.File;
import com.TejaITB2.Model.Employee;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




 
public class ExcelFile implements IFileGen {

	@Override
	public void genFile(List<Employee> empList, String folder) throws IOException {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("Person");
		XSSFRow row;
		  row = sheet.createRow(0);
			row.createCell(0).setCellValue("Id");
			row.createCell(1).setCellValue("Name");
			row.createCell(2).setCellValue("Salary");
		int rowcount = 1;
		for (Employee person : empList) {
		    row = sheet.createRow(rowcount);
			row.createCell(0).setCellValue(person.getEmpId());
			row.createCell(1).setCellValue(person.getFullName());
			row.createCell(2).setCellValue(person.getLocation());
			rowcount++;
		}
		FileOutputStream fos = new FileOutputStream(folder+"employee.xlsx");
		workBook.write(fos);
		fos.close();	
	}

}