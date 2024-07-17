package com.cristian.tiusers.service.impl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;

import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Company Service Implementation")
class CompanyServiceImpTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImp companyServiceImp;

    private CompanyDto companyDto;
    private Company company;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        companyDto = new CompanyDto("Mock Company", "Mock Address", "Mock City");
        company = new Company();
        company.setId(1L);
        company.setName("Mock Company");
        company.setAddress("Mock Address");
        company.setOperationCity("Mock City");
    }

    @Test
    @DisplayName("Test save a new company")
    void testSaveCompany() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        when(companyRepository.save(any(Company.class))).thenReturn(company);
        companyServiceImp.saveCompany(companyDto);

        verify(companyRepository, times(1)).save(any(Company.class));
    }


    @Test
    @DisplayName("Test get all the companies")
    void testGetAllCompanies() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Company> companyPage = new PageImpl<>(Collections.singletonList(company));

        when(companyRepository.findAll(pageable)).thenReturn(companyPage);

        Page<CompanyDto> result = companyServiceImp.getAllCompanies(pageable);

        verify(companyRepository).findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());

        CompanyDto resultCompany = result.getContent().getFirst();

        assertEquals(companyDto.name(), resultCompany.name());
        assertEquals(companyDto.address(), resultCompany.address());
        assertEquals(companyDto.operationCity(), resultCompany.operationCity());
    }

    @Test
    @DisplayName("Test save company with null input throws exception")
    void testSaveCompanyWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            companyServiceImp.saveCompany(null);
        });
    }

    @Test
    @DisplayName("Test get all companies with empty result")
    void testGetAllCompaniesWithEmptyResult() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Company> emptyPage = Page.empty(pageable);

        when(companyRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<CompanyDto> result = companyServiceImp.getAllCompanies(pageable);

        verify(companyRepository).findAll(pageable);
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
    }
}