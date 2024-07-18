package com.cristian.tiusers.controller;

import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveCompanyShouldReturnCreated() throws Exception {
        CompanyDto companyDto = new CompanyDto(
                "Test Company",
                "123 Test Street",
                "Test City");

        mockMvc.perform(post("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllCompaniesShouldReturnOk() throws Exception {
        Page<CompanyDto> companyDtoPage = new PageImpl<>(Collections.singletonList(
                new CompanyDto("Test Company", "123 Test Street", "Test City")));
        given(companyService.getAllCompanies(any(PageRequest.class))).willReturn(companyDtoPage);

        mockMvc.perform(get("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}