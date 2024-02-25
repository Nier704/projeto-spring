package com.test.projeto.services.Course;

import java.util.List;

import com.test.projeto.models.Course.Course;
import com.test.projeto.models.Course.CourseDTO;

public interface CourseService {
  
  List<Course> getAll();
  Course findById(Long id);
  Course insert(CourseDTO courseData);
  Course update(CourseDTO courseData, Long id);
  void delete(Long id);

}
