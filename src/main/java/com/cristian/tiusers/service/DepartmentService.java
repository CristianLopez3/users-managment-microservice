package com.cristian.tiusers.service;

import com.cristian.tiusers.dto.DepartmentDto;

public interface DepartmentService {

    void saveDepartment(DepartmentDto department);

    void updateDepartment(Long id, DepartmentDto departmentdto);

    DepartmentDto getDepartmentById(Long id);

}
