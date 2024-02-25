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
import com.test.projeto.models.Course.CourseDTO;
import com.test.projeto.services.Course.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
  
  private CourseService service;

  public CourseController(CourseService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Course>> getAll(){
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
    @RequestBody CourseDTO courseData
  ){
    return new ResponseEntity<>(service.insert(courseData), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable(name = "id") Long id,
    @RequestBody CourseDTO courseData
  ){
    return new ResponseEntity<>(service.update(courseData, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(
    @PathVariable(name = "id") Long id
  ){
    service.delete(id);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

}
