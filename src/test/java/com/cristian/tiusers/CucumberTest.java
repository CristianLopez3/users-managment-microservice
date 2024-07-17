package com.cristian.tiusers;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"com.cristian.tiusers.steps", "com.cristian.tiusers"}
)
public class CucumberTest {
}