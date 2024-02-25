package com.test.projeto.models.Customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerDTO(
  @NotBlank
  String name,
  @NotBlank
  CustomerGender gender
) {
  
}
