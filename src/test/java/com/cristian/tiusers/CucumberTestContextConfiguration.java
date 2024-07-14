package com.cristian.tiusers;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberTestContextConfiguration.class)
public class CucumberTestContextConfiguration {
}