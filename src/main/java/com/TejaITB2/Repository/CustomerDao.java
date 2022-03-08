package com.TejaITB2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TejaITB2.Model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
