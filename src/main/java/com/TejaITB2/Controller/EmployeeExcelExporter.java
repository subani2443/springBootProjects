package com.TejaITB2.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TejaITB2.Model.Employee;
import com.lowagie.text.Cell;
import com.lowagie.text.Row;

import lombok.Data;

public class EmployeeExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Employee> listEmployee;

	public EmployeeExcelExporter(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Employee");

		XSSFRow row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "empId", style);
		createCell(row, 1, "fullName", style);
		createCell(row, 2, "location", style);
		createCell(row, 3, "dept", style);
		createCell(row, 4, "salary", style);

	}

	private void createCell(XSSFRow row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		XSSFCell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Employee employee : listEmployee) {
			XSSFRow row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, employee.getEmpId(), style);
			createCell(row, columnCount++, employee.getFullName(), style);
			createCell(row, columnCount++, employee.getLocation(), style);
			createCell(row, columnCount++, employee.getDept(), style);
			createCell(row, columnCount++, employee.getSalary(), style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		System.out.println(outputStream);
		workbook.write(outputStream);
//		workbook.close();

		outputStream.close();

	}
}
