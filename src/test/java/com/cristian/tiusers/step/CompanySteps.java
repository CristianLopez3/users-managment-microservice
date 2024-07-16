package com.cristian.tiusers.step;


import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.service.CompanyService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ScenarioScope
public class CompanySteps {

    private CompanyService companyService;

    @Inject
    public CompanySteps(CompanyService companyService) {
        this.companyService = companyService;
    }

    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private ResponseEntity<CompanyDto[]> getAllResponse;

    @Given("a company with the following details")
    public void aCompanyWithTheFollowingDetails(Map<String, String> companyDetails) {
        CompanyDto companyDto = new CompanyDto(
            companyDetails.get("name"),
            companyDetails.get("address"),
            companyDetails.get("operationCity")
        );
        responseEntity = restTemplate.postForEntity("http://localhost:8083/api/v1/companies", companyDto, String.class);
    }

    @When("the client sends a POST request to {string} with the company details")
    public void theClientSendsAPostRequestToWithTheCompanyDetails(String url) {
        // The POST request is already sent in the @Given step.
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, responseEntity.getStatusCodeValue());
    }

    @Then("the response body should contain {string}")
    public void theResponseBodyShouldContain(String expectedMessage) {
        assertTrue(responseEntity.getBody().contains(expectedMessage));
    }

    @Given("the following companies exist")
    public void theFollowingCompaniesExist(List<Map<String, String>> companies) {
        for (Map<String, String> companyDetails : companies) {
            CompanyDto companyDto = new CompanyDto(
                    companyDetails.get("name"),
                    companyDetails.get("address"),
                    companyDetails.get("operationCity")
            );
            companyService.saveCompany(companyDto);
        }
    }

    @When("the client sends a GET request to {string}")
    public void theClientSendsAGetRequestTo(String url) {
        getAllResponse = restTemplate.getForEntity("http://localhost:8083" + url, CompanyDto[].class);
        System.out.println("getting body " + getAllResponse.getBody());
    }

    @Then("the response should contain the following companies")
    public void theResponseShouldContainTheFollowingCompanies(List<Map<String, String>> expectedCompanies) {
        CompanyDto[] companies = getAllResponse.getBody();
        assertEquals(expectedCompanies.size(), companies.length);

        for (int i = 0; i < expectedCompanies.size(); i++) {
            assertEquals(expectedCompanies.get(i).get("name"), companies[i].name());
            assertEquals(expectedCompanies.get(i).get("address"), companies[i].address());
        }
    }
}