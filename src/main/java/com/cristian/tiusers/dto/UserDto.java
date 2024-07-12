package com.cristian.tiusers.dto;



public record UserDto(
        String name,
        String lastname,
        String address,
        String position,
        String telephone,
        String residenceCity,
        boolean state,
        Long companyId,
        Long departmentId
) {
}
