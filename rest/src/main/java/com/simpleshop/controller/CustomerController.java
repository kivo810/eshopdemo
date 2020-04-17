package com.simpleshop.controller;

import com.simpleshop.model.customer.Customer;
import com.simpleshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }
}
