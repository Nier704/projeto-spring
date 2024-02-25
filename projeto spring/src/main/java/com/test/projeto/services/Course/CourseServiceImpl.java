package com.test.projeto.services.Course;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.test.projeto.models.Course.Course;
import com.test.projeto.models.Course.CourseDTO;
import com.test.projeto.models.Course.exceptions.CourseAlreadyExistsException;
import com.test.projeto.models.Course.exceptions.CourseNotFoundException;
import com.test.projeto.models.Customer.exceptions.CustomerNotFoundException;
import com.test.projeto.repositories.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
  
  private CourseRepository repository;

  public CourseServiceImpl(CourseRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Course> getAll(){
    return repository.findAll();
  }

  @Override
  public Course findById(Long id){
    Course course = repository.findById(id)
      .orElseThrow(CustomerNotFoundException::new);
    return course;
  }

  @Override
  @Transactional
  public Course insert(CourseDTO courseData){
    if (repository.existsByTitle(courseData.title())){
      throw new CourseAlreadyExistsException();
    }
  
    var newCourse = new Course();
    BeanUtils.copyProperties(courseData, newCourse);

    return repository.save(newCourse);
  }

  @Override
  @Transactional
  public Course update(CourseDTO courseData, Long id){
    Course course = repository.findById(id)
      .orElseThrow(CourseNotFoundException::new);

    BeanUtils.copyProperties(courseData, course);
    return repository.save(course);
  }

  @Override
  @Transactional
  public void delete(Long id){
    repository.findById(id)
      .ifPresentOrElse(
        course -> repository.delete(course),
        () -> { throw new CourseNotFoundException(); }
      );
  }

}
