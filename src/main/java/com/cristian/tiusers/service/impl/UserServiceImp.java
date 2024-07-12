package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;
import com.cristian.tiusers.exception.CompanyNotFound;
import com.cristian.tiusers.exception.DepartmentNotFound;
import com.cristian.tiusers.mapper.UserMapper;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.model.Department;
import com.cristian.tiusers.model.User;
import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.repository.DepartmentRepository;
import com.cristian.tiusers.repository.UserRepository;
import com.cristian.tiusers.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    @Transactional
    public Page<UserProjectionDto> getUsersByCompany(String companyName, Pageable pageable) {
        Company company = companyRepository.findByNameLike(companyName)
                .orElseThrow(() -> new CompanyNotFound("Company not found"));
        return userRepository.findUsersByCompany(company.getName(), pageable);
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user = UserMapper.dtoToUser(userDto);

        Company company = companyRepository.findById(userDto.companyId())
                .orElseThrow(() -> new CompanyNotFound("Company not found"));

        Department department = departmentRepository.findById(userDto.departmentId())
                .orElseThrow(() -> new DepartmentNotFound("Department not found"));

        user.setCompany(company);
        user.setDepartment(department);

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
