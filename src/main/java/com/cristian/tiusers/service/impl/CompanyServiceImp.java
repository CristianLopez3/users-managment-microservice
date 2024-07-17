package com.cristian.tiusers.service.impl;

import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.mapper.CompanyMapper;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;
    private final Logger logger = LoggerFactory.getLogger(CompanyServiceImp.class);

    @Override
    public void saveCompany(CompanyDto companyDto) {
        if (companyDto == null) {
            logger.error("Attempted to save a null company");
            throw new IllegalArgumentException("Company can't be null");
        }

        if (!isValidCompanyDto(companyDto)) {
            logger.error("Invalid company data: {}", companyDto);
            throw new IllegalArgumentException("Company data is invalid");
        }

        Company company = CompanyMapper.dtoToCompany(companyDto);
        companyRepository.save(company);
        logger.info("Company saved successfully: {}", company);
    }


    @Override
    public Page<CompanyDto> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable).map(CompanyMapper::companyToDto);
    }


    private boolean isValidCompanyDto(CompanyDto companyDto) {
        return StringUtils.hasText(companyDto.name()) &&
                StringUtils.hasText(companyDto.address()) &&
                StringUtils.hasText(companyDto.operationCity());
    }

}
