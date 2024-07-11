package com.cristian.tiusers.controller;

import com.cristian.tiusers.model.Company;
import com.cristian.tiusers.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> saveCompany(@RequestBody Company company) {
        companyService.saveCompany(company);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body("Company saved successfully");
    }

}
