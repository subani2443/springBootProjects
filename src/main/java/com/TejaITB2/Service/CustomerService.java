package com.TejaITB2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TejaITB2.Model.Customer;
import com.TejaITB2.Repository.CustomerDao;

@Service
public class CustomerService {
	@Autowired
	CustomerDao customerDao;
	

	public void saveCustomer(Customer customer) {
		customerDao.save(customer);
	}

}
