package com.cristian.tiusers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CompanyDto(
        @NotNull
        @Length(min = 1, max = 50)
        String name,
        @NotBlank
        @Length(min = 1, max = 500)
        String address,
        @NotBlank
        String operationCity
){

}
