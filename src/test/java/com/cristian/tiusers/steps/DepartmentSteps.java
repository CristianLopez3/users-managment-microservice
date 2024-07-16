package com.cristian.tiusers.steps;

import com.cristian.tiusers.dto.DepartmentDto;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;


@RequiredArgsConstructor
public class DepartmentSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private final TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private DepartmentDto departmentDto;
    private ResponseEntity<String> response;
    private final Logger logger = LoggerFactory.getLogger(DepartmentSteps.class);

    @Given("I have a department with the next details")
    public void IHaveADepartmentWithTheNextDetails(Map<String, String> departmentDetails) {
        departmentDto = new DepartmentDto(
                departmentDetails.get("name"),
                departmentDetails.get("description"),
                Long.valueOf(departmentDetails.get("companyId"))
        );
    }

    @When("I send a post request to {string}")
    public void ISendAPostRequestTo(String endpoint) {
        var url = createUrl(port, endpoint);
        var departmentJson = convertToJson(departmentDto);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(departmentJson, headers);
        response = restTemplate.postForEntity(url, request, String.class);
    }

    @Then("The response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        var actualStatus = response.getStatusCode().value();
        assertEquals(statusCode, actualStatus);
    }

    @Then("The department response body should be {string}")
    public void theDepartmentResponseBodyShouldBe(String expectedBody){
        var actualBody = response.getBody();
        assertEquals(expectedBody, actualBody);
    }

    @Given("I have an existing department with ID {int}")
    public void iHaveAnExistingDepartmentWithID(int id) {
        // Puedes hacer una llamada GET para confirmar que el departamento existe.
        // Sin embargo, para simplificar, asumimos que el departamento existe.
    }

    @Given("I have a valid updated department payload")
    public void iHaveAValidUpdatedDepartmentPayload(Map<String, String> departmentDetails) {
        departmentDto = new DepartmentDto(
                departmentDetails.get("name"),
                departmentDetails.get("description"),
                Long.valueOf(departmentDetails.get("companyId"))
        );
    }

    @When("I send a PUT request to {string}")
    public void iSendAPutRequestTo(String endpoint) {
        var url = createUrl(port, endpoint);
        var departmentJson = convertToJson(departmentDto);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(departmentJson, headers);
        restTemplate.put(url, request);
        response = restTemplate.getForEntity(url, String.class); // Para verificar la respuesta
    }

    @Then("The department response body should match json")
    public void theDepartmentResponseBodyShouldMatchJson(String expectedBody) {
        try {
            JsonNode expectedJson = objectMapper.readTree(expectedBody);
            JsonNode actualJson = objectMapper.readTree(response.getBody());

            assertEquals(expectedJson, actualJson);
        } catch (Exception e) {
            logger.error("Failed to parse JSON", e);
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    private String createUrl(int port, String endpoint) {
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