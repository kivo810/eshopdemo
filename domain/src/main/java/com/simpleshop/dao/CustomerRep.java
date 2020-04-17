package com.simpleshop.dao;

import com.simpleshop.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRep extends JpaRepository <Customer, Integer> {
}
