package com.cristian.tiusers.dto;

import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.model.Department;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record UserDto(
        String name,
        String lastname,
        String address,
        String telephone,
        String residenceCity,
        boolean state,
        Long companyId,
        Long departmentId
) {
}
