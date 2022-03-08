package com.TejaITB2.FactoryDesignPattern;

import java.io.FileOutputStream;
import com.TejaITB2.Model.Employee;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


 
public class DocxFile implements IFileGen {

		@Override
		public void genFile(List<Employee> empList, String folder) throws IOException {
			XWPFDocument document = new XWPFDocument();
			XWPFTable table = document.createTable();
			boolean isFirstime=true;
			for (Employee emp : empList) {
				 XWPFTableRow row =null;
				if(isFirstime){
				     row = table.getRow(0);
				     row.getCell(0).setText(emp.getEmpId()+"");
					 row.addNewTableCell().setText(emp.getFullName()+"");
					 row.addNewTableCell().setText(emp.getLocation()+"");
				   isFirstime=false;
				}else{
					 row=table.createRow();
				     row.getCell(0).setText(emp.getEmpId()+"");
					 row.getCell(1).setText(emp.getFullName()+"");
					 row.getCell(2).setText(emp.getLocation()+"");
				}
				
				XWPFParagraph paraGraph = document.createParagraph();
				XWPFRun run = paraGraph.createRun();
				run.setText(emp.getEmpId()+"::"+emp.getFullName()+"::"+emp.getLocation());
			}
			
			FileOutputStream wordfos = new FileOutputStream(folder+"employee.docx");
			document.write(wordfos);
			wordfos.close();
			
			}
		}

