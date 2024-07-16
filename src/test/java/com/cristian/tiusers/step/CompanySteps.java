package com.cristian.tiusers.step;


import com.cristian.tiusers.dto.CompanyDto;
import com.cristian.tiusers.service.CompanyService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CompanySteps {

    @Autowired
    private CompanyService companyService;

    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private PagedModel<CompanyDto> getAllResponse;

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
        ParameterizedTypeReference<PagedModel<CompanyDto>> responseType = new ParameterizedTypeReference<PagedModel<CompanyDto>>() {};
        ResponseEntity<PagedModel<CompanyDto>> response = restTemplate.exchange("http://localhost:8080" + url, HttpMethod.GET, null, responseType);
        getAllResponse = response.getBody();
    }

    @Then("the response should contain the following companies")
    public void theResponseShouldContainTheFollowingCompanies(List<Map<String, String>> expectedCompanies) {
        List<CompanyDto> companies = getAllResponse.getContent().stream().collect(Collectors.toList());
        assertEquals(expectedCompanies.size(), companies.size());

        for (int i = 0; i < expectedCompanies.size(); i++) {
            assertEquals(expectedCompanies.get(i).get("name"), companies.get(i).name());
            assertEquals(expectedCompanies.get(i).get("address"), companies.get(i).address());
        }
    }


}