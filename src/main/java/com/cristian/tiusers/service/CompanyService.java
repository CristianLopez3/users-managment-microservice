package com.cristian.tiusers.service;


import com.cristian.tiusers.dto.CompanyDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    void saveCompany(CompanyDto company);

    Page<CompanyDto> getAllCompanies(Pageable pageable);

}
