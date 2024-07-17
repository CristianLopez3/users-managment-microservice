package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;
import com.cristian.tiusers.exception.CompanyNotFound;
import com.cristian.tiusers.exception.UserNotFoundException;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.model.Department;
import com.cristian.tiusers.model.User;
import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.repository.DepartmentRepository;
import com.cristian.tiusers.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Collections;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    private User user;
    private UserDto userDto;
    private Company company;
    private Department department;
    private UserProjectionDto userProjectionDto;

    @BeforeEach
    void setUp(){

        company = new Company();
        company.setId(1L);
        company.setName("Company");

        department = new Department();
        department.setId(2L);
        department.setName("Department");
        department.setCompany(company);

        userDto = new UserDto(
                "John",
                "Doe",
                "Cra 87",
                "Talent Manager",
                "3222341232",
                "Bogota",
                true,
                company.getId(),
                department.getId()
        );

        user = new User();
        user.setId(1L);
        user.setName(userDto.name());
        user.setLastname(userDto.lastname());
        user.setAddress(userDto.address());
        user.setPosition(userDto.position());
        user.setResidenceCity(userDto.residenceCity());
        user.setState(userDto.state());
        user.setCompany(company);
        user.setDepartment(department);

        userProjectionDto = new UserProjectionDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getAddress(),
                user.isState(),
                user.getDepartment().getName()
        );

    }

    @Test
    @DisplayName("Test get user by company id")
    void getUsersByCompany() {
        long companyId = 1L;

        Pageable pageable = PageRequest.of(0, 10);
        Page<User> emptyPage = new PageImpl<>(Collections.singletonList(user));

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(userRepository.findUsersByCompanyId(companyId, pageable)).thenReturn(emptyPage);

        Page<UserProjectionDto> result = userServiceImp.getUsersByCompany(companyId, pageable);

        verify(companyRepository).findById(companyId);
        verify(userRepository).findUsersByCompanyId(companyId, pageable);

        assertNotNull(result);

        UserProjectionDto projectionDto = result.getContent().get(0);

        assertEquals(userProjectionDto.name(), projectionDto.name());
        assertEquals(userProjectionDto.departmentName(), projectionDto.departmentName());
    }

    @Test
    @DisplayName("Test save a new user")
    void saveUser() {
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userServiceImp.saveUser(userDto);

        verify(companyRepository).findById(anyLong());
        verify(departmentRepository).findById(anyLong());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Test delete user by id - success")
    void testDeleteUserByIdSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userServiceImp.deleteUserById(1L);

        verify(userRepository).findById(1L);
        verify(userRepository).delete(user);
    }

    @Test
    @DisplayName("Test delete user by id - user not found")
    void testDeleteUserByIdUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userServiceImp.deleteUserById(1L);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
        verify(userRepository).findById(1L);
        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    @DisplayName("Test find users by company throws company not found exception")
    void testThrowsCompanyNotFoundException() {
        when(companyRepository.findById(anyLong())).thenThrow(CompanyNotFound.class);

        assertThrows(CompanyNotFound.class, () -> {
            userServiceImp.saveUser(userDto);
        });
    }


}