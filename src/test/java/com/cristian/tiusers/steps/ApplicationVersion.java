package com.cristian.tiusers.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ScenarioScope
public class ApplicationVersion {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @When("I request the endpoint {string}")
    public void iRequestTheEndpoint(String path) {
        response = this.restTemplate.getForEntity("http://localhost:" + port + path, String.class);
    }

    @Then("the response should be {string}")
    public void theResponseShouldBe(String version) {
        assertEquals(version, response.getBody());
    }

    @And("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }
}