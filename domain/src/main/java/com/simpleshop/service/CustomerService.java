package com.simpleshop.service;

import com.simpleshop.dao.CustomerRep;
import com.simpleshop.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRep customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
