package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.mapper.CompanyMapper;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.repository.DepartmentRepository;
import com.cristian.tiusers.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {


    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public void saveCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.dtoToCompany(companyDto);
        companyRepository.save(company);
    }

    @Override
    public Page<CompanyDto> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable).map(CompanyMapper::companyToDto);
    }


}
