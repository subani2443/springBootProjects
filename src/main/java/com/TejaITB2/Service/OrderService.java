package com.TejaITB2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TejaITB2.Model.Orders;
import com.TejaITB2.Repository.OrderDao;
@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	public void saveOrders(Orders orders) {
		orderDao.save(orders);
	}

}
