Feature: Department management

  Scenario: Create a new department
    Given I have department details
    When I create the department
    Then the department is saved successfully