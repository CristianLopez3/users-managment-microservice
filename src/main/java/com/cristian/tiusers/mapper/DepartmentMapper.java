package com.cristian.tiusers.mapper;

import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.dto.DepartmentDto;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.model.Department;

public class DepartmentMapper {

    public static Department dtoToDepartment(DepartmentDto dto) {
        if (dto == null) {
            return null;
        }
        Department department = new Department();
        department.setName(dto.name());
        department.setDescription(dto.description());
        return department;
    }

    public static DepartmentDto departmentToDto(Department department) {
        if (department == null) {
            return null;
        }
        return new DepartmentDto(
                department.getName(),
                department.getDescription(),
                department.getCompany().getId()
        );
    }
}
