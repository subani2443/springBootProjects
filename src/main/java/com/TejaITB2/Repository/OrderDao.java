package com.TejaITB2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TejaITB2.Model.Orders;

public interface OrderDao  extends JpaRepository<Orders, Integer>{

}
