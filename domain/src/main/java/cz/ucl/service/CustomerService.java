package cz.ucl.service;

import cz.ucl.dao.CustomerRep;
import cz.ucl.model.customer.Customer;
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
                (new Customer(56415616,"John","Cardiac", "new","xxx","aaa"),
                        new Customer(2,"peter","eye","old","newone","bbb"))
                .collect(Collectors.toList()));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
