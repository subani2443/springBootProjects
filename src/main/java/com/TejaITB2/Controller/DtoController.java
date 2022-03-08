package com.TejaITB2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TejaITB2.Dto.CustomerDtls;
import com.TejaITB2.Model.Customer;
import com.TejaITB2.Model.Items;
import com.TejaITB2.Model.Orders;
import com.TejaITB2.Service.CustomerService;
import com.TejaITB2.Service.Itemservice;
import com.TejaITB2.Service.OrderService;

@RestController
@RequestMapping("/customerDto")
public class DtoController {
	@Autowired
	Itemservice itemService;

	@Autowired
	CustomerService cumService;

	@Autowired
	OrderService orderservice;

	@PostMapping("/save")
	public ResponseEntity<CustomerDtls> saveDtls(@RequestBody CustomerDtls dto) {

		Customer customer = new Customer();
		customer.setCusName(dto.getCustomerDto().getCusName());
		customer.setLocation(dto.getCustomerDto().getLocation());
		cumService.saveCustomer(customer);

		Orders orders = new Orders();
		orders.setOrderName(dto.getOrders().getOrderName());
		orders.setPrice(dto.getOrders().getPrice());
		orderservice.saveOrders(orders);

		Items items = new Items();
		items.setItemName(dto.getItemsDto().getItemName());
		items.setAmount(dto.getItemsDto().getPrice());
		itemService.saveItems(items);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

}
