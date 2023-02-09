package com.phonenumber.com.phonenumbervalidator.repositories;

import com.phonenumber.com.phonenumbervalidator.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
