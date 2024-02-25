package com.test.projeto.services.Customer;

import java.util.List;

import com.test.projeto.models.Course.Course;
import com.test.projeto.models.Customer.Customer;
import com.test.projeto.models.Customer.CustomerDTO;

public interface CustomerService {
  
  List<Customer> getAll();
  Customer findById(Long id);
  Customer insert(CustomerDTO customerData);
  Course insertCourse(Long customerId, Long courseId);
  Customer update(CustomerDTO customerData, Long id);
  void delete(Long id);

}