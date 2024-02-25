package com.test.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.projeto.models.Course.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>  {
  
  boolean existsByTitle(String name);

}
