package com.test.projeto.services.Customer;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.test.projeto.models.Course.Course;
import com.test.projeto.models.Customer.Customer;
import com.test.projeto.models.Customer.CustomerDTO;
import com.test.projeto.models.Customer.exceptions.CustomerAlreadyExistsException;
import com.test.projeto.models.Customer.exceptions.CustomerNotFoundException;
import com.test.projeto.repositories.CustomerRepository;
import com.test.projeto.services.Course.CourseService;

import jakarta.transaction.Transactional; 

@Service
public class CustomerServiceImpl implements CustomerService {
  
  private CustomerRepository repository;

  private CourseService courseService;

  public CustomerServiceImpl(CustomerRepository repository, CourseService courseService) {
    this.repository = repository;
    this.courseService = courseService;
  }

  @Override
  public List<Customer> getAll(){
    return repository.findAll();
  }

  @Override
  public Customer findById(Long id){
    Customer customer = repository.findById(id)
      .orElseThrow(CustomerNotFoundException::new);
    return customer;
  }

  // insert - Customer DTO
  @Override
  @Transactional
  public Customer insert(CustomerDTO customerData){
    if (repository.existsByName(customerData.name())){
      throw new CustomerAlreadyExistsException();
    }
  
    var newCustomer = new Customer();
    BeanUtils.copyProperties(customerData, newCustomer);

    return repository.save(newCustomer);
  }

  @Override
  @Transactional
  public Course insertCourse(Long customerId, Long courseId){
    Customer customer = repository.findById(customerId)
      .orElseThrow(CustomerNotFoundException::new);
    Course course = courseService.findById(courseId);

    customer.getCourses().add(course);

    course.setCustomer(customer);

    repository.save(customer);

    return course;
  }

  @Override
  @Transactional
  public Customer update(CustomerDTO customerData, Long id){
    Customer customer = repository.findById(id)
      .orElseThrow(CustomerNotFoundException::new);

    customer.setName(customerData.name());
    customer.setGender(customerData.gender());

    return repository.save(customer);
  }

  @Override
  @Transactional
  public void delete(Long id){
    repository.findById(id)
      .ifPresentOrElse(
        customer -> repository.delete(customer),
        () -> { throw new CustomerNotFoundException(); }
      );
  }

}