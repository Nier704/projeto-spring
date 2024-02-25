package com.test.projeto.models.Course;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
  @NotBlank
  String title,
  @NotBlank
  String description,
  @NotBlank
  CourseType courseType
) {
  
}
