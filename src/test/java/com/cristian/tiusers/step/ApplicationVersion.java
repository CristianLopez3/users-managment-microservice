package com.cristian.tiusers.step;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ScenarioScope
public class ApplicationVersion {


    private TestRestTemplate restTemplate;
    private ResponseEntity<String> response;

    @Inject
    public ApplicationVersion(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @When("I request the endpoint {string}")
    public void iRequestTheEndpoint(String path) {
        response  = this.restTemplate.getForEntity("http://localhost:8083" + path, String.class);
    }

    @Then("the response should be {string}")
    public void theResponseShouldBe(String version) {
        assertEquals(version, response.getBody());
    }

    @And("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        assertEquals(HttpStatusCode.valueOf(statusCode), response.getStatusCode());
    }
}