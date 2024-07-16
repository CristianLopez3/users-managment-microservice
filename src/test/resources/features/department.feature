Feature: Department Management API

  Scenario: Save a new department
    Given I have a valid department payload
      """
      {
        "name": "Human Resources",
        "description": "Handles HR tasks",
        "companyId": 1
      }
      """
    When I send a POST request to "/api/v1/departments"
    Then the response status should be 201
    And the response body should be "department saved successfully"

  Scenario: Update an existing department
    Given I have an existing department with ID 1
    And I have a valid updated department payload
      """
      {
        "name": "Human Resources",
        "description": "Handles all HR tasks",
        "companyId": 1
      }
      """
    When I send a PUT request to "/api/v1/departments/1"
    Then the response status should be 200
    And the response body should be "department updated successfully"