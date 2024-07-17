package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.dto.DepartmentDto;
import com.cristian.tiusers.exception.CompanyNotFound;
import com.cristian.tiusers.exception.DepartmentNotFound;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.model.Department;
import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.repository.DepartmentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing department service implementation")
class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CompanyRepository companyRepository;

    private Company company;
    private Department department;
    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp(){
        company = new Company();
        company.setId(1L);
        departmentDto = new DepartmentDto("mock title", "mock description", company.getId());
        department = new Department();
        department.setId(1L);
        department.setName(departmentDto.name());
        department.setDescription(departmentDto.description());
        department.setCompany(company);
    }

    @Test
    @DisplayName("test save a new department")
    void saveDepartment() {
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        departmentService.saveDepartment(departmentDto);

        verify(companyRepository, times(1)).findById(anyLong());
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    @DisplayName("test updating a department")
    void updateDepartment() {
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        departmentService.saveDepartment(departmentDto);
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    @DisplayName("test getting a department by id")
    void getDepartmentById() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        DepartmentDto updatedDepartment = departmentService.getDepartmentById(anyLong());

        verify(departmentRepository, times(1)).findById(anyLong());
        assertEquals(updatedDepartment, departmentDto);
    }

    @Test
    @DisplayName("test that when we pass a wrong input it's going to throws a department not found exception")
    void throwsDepartmentNotFoundException() {
        assertThrows(DepartmentNotFound.class, ()  -> departmentService.getDepartmentById(null));
    }

    @Test
    @DisplayName("Test save department throws company not found exception")
    void testThrowsCompanyNotFoundException() {
        when(companyRepository.findById(anyLong())).thenThrow(CompanyNotFound.class);

        assertThrows(CompanyNotFound.class, () -> {
            departmentService.saveDepartment(departmentDto);
        });
    }

}