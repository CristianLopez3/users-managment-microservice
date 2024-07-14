package stepDefinitions;

import com.cristian.tiusers.service.DepartmentService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MainSteps {

    private DepartmentService departmentService;

    public MainSteps(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @Given("I have department details")
    public void iHaveDepartmentDetails() {
        System.out.println("I have department details");
    }

    @Then("the department is saved successfully")
    public void the_department_is_saved_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
