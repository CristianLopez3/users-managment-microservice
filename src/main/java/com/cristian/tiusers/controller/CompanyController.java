package com.cristian.tiusers.controller;

import com.cristian.tiusers.dto.CompanyDto;

import com.cristian.tiusers.service.CompanyService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> saveCompany(@RequestBody CompanyDto companyDto) {
        logger.info("Save company: {}", companyDto);
        companyService.saveCompany(companyDto);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body("Company saved successfully");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(companyService.getAllCompanies(pageable));
    }


}
