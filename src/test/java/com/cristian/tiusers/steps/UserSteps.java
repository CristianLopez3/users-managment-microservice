package com.cristian.tiusers.steps;

import com.cristian.tiusers.dto.UserDto;
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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
public class UserSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private final TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto userDto;
    private ResponseEntity<String> response;
    private final Logger logger = LoggerFactory.getLogger(UserSteps.class);

    @Given("I have a user with the next details")
    public void iHaveAUserWithTheNextDetails(Map<String, String> userDetails) {
        userDto = new UserDto(
                userDetails.get("name"),
                userDetails.get("lastname"),
                userDetails.get("address"),
                userDetails.get("position"),
                userDetails.get("telephone"),
                userDetails.get("residenceCity"),
                Boolean.parseBoolean(userDetails.get("state")),
                Long.valueOf(userDetails.get("companyId")),
                Long.valueOf(userDetails.get("departmentId"))
        );
    }

    @When("I send a POST request to {string}")
    public void iSendAPOSTRequestTo(String endpoint) {
        var url = createUrl(port, endpoint);
        var userJson = convertToJson(userDto);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(userJson, headers);
        response = restTemplate.postForEntity(url, request, String.class);
    }

    @Then("The user response body should be {string}")
    public void theUserResponseBodyShouldBe(String expectedBody) {
        var actualBody = response.getBody();
        assertEquals(expectedBody, actualBody);
    }

    @Given("in users I send a GET request to {string}")
    public void inUsersISendAGetRequestTo(String endpoint){
        var url = createUrl(port, endpoint);

        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the response status in users should be {int}")
    public void theResponseStatusInUsersShouldBe(int statusCode) {
        var actualStatusCode = response.getStatusCode().value();
        assertEquals(statusCode, actualStatusCode);
    }

    @Given("the response body should contains a list of users")
    public void theResponseBodyShouldContainAListOfUsers() {
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().contains("content"));
    }

    private String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    private String createUrl(int port, String endpoint) {
        return "http://localhost:" + port + endpoint;
    }
}