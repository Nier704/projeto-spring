package com.test.projeto.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.projeto.models.Course.Course;
import com.test.projeto.models.Customer.Customer;
import com.test.projeto.models.Customer.CustomerDTO;
import com.test.projeto.services.Customer.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAll(){
    return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(
    @PathVariable(name = "id") Long id
  ){
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> insert(
    @RequestBody CustomerDTO customerData
  ){
    return new ResponseEntity<>(service.insert(customerData), HttpStatus.CREATED);
  }

  @PutMapping("/{customerId}/add/{courseId}")
  public ResponseEntity<?> insertCourse(
    @PathVariable(name = "customerId") Long customerId,
    @PathVariable(name = "courseId") Long courseId
  ){
    Course course = service.insertCourse(customerId, courseId);
    return new ResponseEntity<>(course, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable(name = "id") Long id,
    @RequestBody CustomerDTO customerData
  ){
    return new ResponseEntity<>(service.update(customerData, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(
    @PathVariable(name = "id") Long id
  ){
    service.delete(id);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }
  
}
