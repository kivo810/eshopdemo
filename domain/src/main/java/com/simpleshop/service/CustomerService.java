package com.simpleshop.service;

import com.simpleshop.dao.CustomerRep;
import com.simpleshop.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService {

    @Autowired
    CustomerRep customerRepository;

    @PostConstruct
    public void initCustomers(){
        customerRepository.saveAll(Stream.of
                (new Customer(1,"John","Cardiac", "new","xxx"),
                        new Customer(2,"peter","eye","old","newone"))
                .collect(Collectors.toList()));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
