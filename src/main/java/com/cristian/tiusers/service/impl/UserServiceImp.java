package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;
import com.cristian.tiusers.exception.CompanyNotFound;
import com.cristian.tiusers.exception.DepartmentNotFound;
import com.cristian.tiusers.exception.UserNotFoundException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    @Transactional
    public Page<UserProjectionDto> getUsersByCompany(Long companyId, Pageable pageable) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFound(String.format("Company with id %d not found", companyId)));

        Page<User> usersPage = userRepository.findUsersByCompanyId(company.getId(), pageable);

        return usersPage.map(UserMapper::userToProjectionDto);
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("User with id {} does not exist", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
        userRepository.delete(user);
        logger.info("User with id {} deleted successfully", id);
    }

}
