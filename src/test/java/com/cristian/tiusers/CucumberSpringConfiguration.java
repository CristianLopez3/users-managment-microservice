package com.cristian.tiusers;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@ContextConfiguration(classes = TiUsersApplication.class)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {
}