package com.cristian.tiusers.mapper;

import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.model.Company;

public class CompanyMapper {

    public static Company dtoToCompany(CompanyDto dto) {
        if (dto == null) {
            return null;
        }
        Company company = new Company();
        company.setName(dto.name());
        company.setAddress(dto.address());
        company.setOperationCity(dto.operationCity());
        return company;
    }

    public static CompanyDto companyToDto(Company company) {
        if (company == null) {
            return null;
        }
        return new CompanyDto(
                company.getName(),
                company.getAddress(),
                company.getOperationCity()
        );
    }
}
