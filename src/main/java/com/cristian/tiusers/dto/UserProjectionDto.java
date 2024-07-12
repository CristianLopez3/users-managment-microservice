package com.cristian.tiusers.dto;

public record UserProjectionDto(
        Long id,
        String name,
        String lastname,
        String telephone,
        boolean state,
        String departmentName
) {
}
