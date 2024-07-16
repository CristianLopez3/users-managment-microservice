package com.cristian.tiusers.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

public class DepartmentSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl = "/api/v1/departments";
    private ResponseEntity<String> response;
    private String departmentPayload;
    private String updatedDepartmentPayload;

    @Given("I have a valid department payload")
    public void i_have_a_valid_department_payload(String payload) {
        departmentPayload = payload;
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String url) {
        response = restTemplate.postForEntity(url, departmentPayload, String.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCodeValue());
    }

    @Then("the response body should be {string}")
    public void the_response_body_should_be(String responseBody) {
        assertEquals(responseBody, response.getBody());
    }

    @Given("I have an existing department with ID 1")
    public void i_have_an_existing_department_with_id_1() {
        // Crear un departamento con ID 1 si no existe. Esto depende de la configuración de tu base de datos de prueba.
        // Puedes usar un método de utilidad para inicializar el estado de la base de datos.
        String initialPayload = "{ \"name\": \"Initial Department\", \"description\": \"Initial description\", \"companyId\": 1 }";
        restTemplate.postForEntity(baseUrl, initialPayload, String.class);
    }

    @Given("I have a valid updated department payload")
    public void i_have_a_valid_updated_department_payload(String payload) {
        updatedDepartmentPayload = payload;
    }

    @When("I send a PUT request to {string}")
    public void i_send_a_put_request_to(String url) {
        HttpEntity<String> entity = new HttpEntity<>(updatedDepartmentPayload);
        response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }

}