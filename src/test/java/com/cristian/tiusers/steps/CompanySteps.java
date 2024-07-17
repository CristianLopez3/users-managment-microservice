package com.cristian.tiusers.steps;

import com.cristian.tiusers.dto.CompanyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ALL")
@RequiredArgsConstructor
@TestPropertySource("classpath:application-test.yml")
public class CompanySteps {

    @LocalServerPort
    private int port;

    @Autowired
    private final TestRestTemplate restTemplate;

    @Autowired
    private final ObjectMapper objectMapper;

    private CompanyDto companyDto;
    private ResponseEntity<String> response;
    private final Logger logger = LoggerFactory.getLogger(CompanySteps.class);

    @Given("I have a company with the following details")
    public void iHaveACompanyWithTheFollowingDetails(Map<String, String> companyDetails) {
        companyDto = new CompanyDto(
                companyDetails.get("name"),
                companyDetails.get("address"),
                companyDetails.get("operationCity")
        );
    }

    @When("in company I send a POST request to {string}")
    public void inCompanyISendAPostRequestTo(String endpoint) {
        String url = createUrl(endpoint);
        String companyJson = convertToJson(companyDto);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(companyJson, headers);

        response = restTemplate.postForEntity(url, request, String.class);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        var actualStatusCode = response.getStatusCode().value();
        assertEquals(statusCode, actualStatusCode);
    }

    @Then("the company response body should be {string}")
    public void theCompanyResponseBodyShouldBe(String expectedBody) {
        assertEquals(expectedBody, response.getBody());
    }

    @When("in company I send a GET request to {string}")
    public void inCompanyISendAGetRequestTo(String endpoint) {
        String url = createUrl(endpoint);
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response should contain a list of companies")
    public void theResponseShouldContainAListOfCompanies() {
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().contains("content"));
    }

    private String createUrl(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    private String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}