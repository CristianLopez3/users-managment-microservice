Feature: Department API

  Scenario: Save a new department
    Given I have a department with the next details
      | name        | Human Resources   |
      | description | Handles HR tasks  |
      | companyId   | 1                 |
    When I send a post request to "/api/v1/departments"
    Then The response status should be 201
    And The department response body should be "department saved successfully"

  Scenario: Update an existing department
    Given I have an existing department with ID 1
    And I have a valid updated department payload
      | name        | Human Resources      |
      | description | Handles all HR tasks |
      | companyId   | 1                    |
    When I send a PUT request to "/api/v1/departments/1"
    Then The response status should be 200
    And The department response body should match json
    """
    {
      "name": "Human Resources",
      "description": "Handles all HR tasks",
      "companyId": 1
    }
    """