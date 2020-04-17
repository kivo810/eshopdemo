package cz.ucl.dao;

import cz.ucl.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRep extends JpaRepository <Customer, Integer> {
}
