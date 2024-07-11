package com.cristian.tiusers.service;

import com.cristian.tiusers.dto.DepartmentDto;
import com.cristian.tiusers.model.Department;

public interface DepartmentService {

    void saveDepartment(DepartmentDto department);

    void updateDepartment(Department department);

}
