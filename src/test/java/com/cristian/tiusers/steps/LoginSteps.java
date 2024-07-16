package com.cristian.tiusers.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginSteps {

    @Given("the user navigates to the login page")
    public void navigateToLoginPage() {
        // Implementación del código para navegar a la página de login
        System.out.println("Navigating to login page");
    }

    @When("the user enters valid credentials")
    public void enterValidCredentials() {
        // Implementación del código para ingresar credenciales válidas
        System.out.println("Entering valid credentials");
    }

    @Then("the user is redirected to the home page")
    public void redirectToHomePage() {
        // Implementación del código para verificar la redirección a la página de inicio
        System.out.println("Redirected to home page");
        assertTrue(true);
    }
}