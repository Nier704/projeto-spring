package com.test.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.projeto.models.Customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  
  boolean existsByName(String name);

}
