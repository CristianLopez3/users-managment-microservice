package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {


    private final CompanyRepository companyRepository;

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }


}
