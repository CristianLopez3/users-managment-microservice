package com.cristian.tiusers;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "com.cristian.tiusers.stepdefinitions",
        monochrome = true
)
@SpringBootTest(classes = TestNGCucumberRunner.class)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {


}
