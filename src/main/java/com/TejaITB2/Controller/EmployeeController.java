package com.TejaITB2.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.TejaITB2.FactoryDesignPattern.FactoryDesign;
import com.TejaITB2.GlobalException.UserNotFounfException;
import com.TejaITB2.Model.Employee;
import com.TejaITB2.Service.EmployeeService;
import com.lowagie.text.DocumentException;

import PDF.UserPDFExporter;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public String saveEmployee(@RequestBody Employee employee) {
		System.out.println(employee.getLocation());
		return "successfully saved";
	}

	@RequestMapping(value = "/empSave", method = RequestMethod.POST)
	public String save(@RequestBody Employee employee) {
		System.out.println(employee.getDept());
		return "saved";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployeeData(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		ResponseEntity<Employee> re = new ResponseEntity<Employee>(HttpStatus.CREATED);
		return re;

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployeeData(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		ResponseEntity<Employee> re = new ResponseEntity<Employee>(HttpStatus.CREATED);
		return re;

	}

	@RequestMapping(value = "/deleteById/{eid}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployeeData(@PathVariable Integer eid) {
		employeeService.deleteEmployee(eid);
		ResponseEntity<Employee> re = new ResponseEntity<Employee>(HttpStatus.OK);
		return re;

	}

	@RequestMapping(value = "/getById/{eid}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeDataById(@PathVariable Integer eid) {
		Optional<Employee> empl = employeeService.getEmployee(eid);
		return new ResponseEntity<Employee>(empl.get(), HttpStatus.FOUND);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> listEmp = employeeService.getAllData();
		return new ResponseEntity<List<Employee>>(listEmp, HttpStatus.OK);
	}

	@RequestMapping(value = "/getBySalary/{salary}", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeeBySalary(@PathVariable Double salary) {
		List<Employee> empl = employeeService.getDataBySalary(salary);
		return new ResponseEntity<List<Employee>>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public ResponseEntity<Page<Employee>> pagination(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
		Page<Employee> pageOfRecords = employeeService.getPagination(pageNum, pageSize);
		return new ResponseEntity<Page<Employee>>(pageOfRecords, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/dept/{dept}", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeeBydept(@PathVariable String dept) {
		List<Employee> empl = employeeService.getDataBydept(dept);
		System.out.println(empl.getClass().getName());
		return new ResponseEntity<List<Employee>>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/getByFirstName/{firstName}", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeeByFirstName(@PathVariable String firstName) {
		List<Employee> empl = employeeService.getDataByFirstName(firstName);
		return new ResponseEntity<List<Employee>>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/getByFirstNameAndLastName", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeeByFirstNameAndLastName(@RequestParam String firstName,
			String lastName) {
		List<Employee> empl = employeeService.getDataByFirstNameAndLastName(firstName, lastName);
		return new ResponseEntity<List<Employee>>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/between", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> between(@RequestParam Double minSalary, Double maxSalary) {
		List<Employee> empl = employeeService.between(minSalary, maxSalary);
		return new ResponseEntity<List<Employee>>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/lessthan/{salary}", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> lessthan(@PathVariable Double salary) {
		List<Employee> empl = employeeService.lessthan(salary);
		return new ResponseEntity<List<Employee>>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/userAvailability", method = RequestMethod.GET)
	public ResponseEntity<Boolean> isUserAvailable(@RequestParam String fname) {
		Boolean userStatus = employeeService.userAvailableOrNot(fname);
		if (userStatus == false) {
			throw new UserNotFounfException();
		} else {
			return new ResponseEntity<Boolean>(userStatus, HttpStatus.OK);
		}

	}

	@GetMapping("/users/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Employee> listUsers = employeeService.listAll();

		UserPDFExporter exporter = new UserPDFExporter(listUsers);
		exporter.export(response);

	}

	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Employee_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Employee> listEmployee = employeeService.listAll();

		EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(listEmployee);

		excelExporter.export(response);

	}

//	@RequestMapping(value = "/getEmp", method = RequestMethod.GET)
//	public Employee getEmpData() {
//		Employee empl = new Employee(101, "subani", "sk", "subani sk", "nrt", "java", 200.20);
//		return empl;
//
//	}

	@RequestMapping(value = "/getName", method = RequestMethod.GET)
	public String getEmpName() {
		RestTemplate restTemplate = new RestTemplate();
		String empname = restTemplate.getForObject("http://localhost:8085/controll/getData", String.class);
		return empname;

	}

	@RequestMapping(value = "/factoryDesign/{types}", method = RequestMethod.GET)
	public ResponseEntity<Object> factoryDesign(@PathVariable String types) throws IOException {
		String folder = "D:\\notepad\\";
		List<Employee> listEmp = employeeService.getAllData();
		FactoryDesign factory = new FactoryDesign(types);
		factory.executeCommand(listEmp, folder);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
}
