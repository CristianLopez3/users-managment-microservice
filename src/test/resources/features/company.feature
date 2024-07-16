Feature: Company API

  Scenario: Save a new company
    Given I have a company with the following details
      | name          | Example Company   |
      | address       | 123 Example St    |
      | operationCity | Example City      |
    When in company I send a POST request to "/api/v1/companies"
    Then the response status should be 201
    And the company response body should be "Company saved successfully"

  Scenario: Retrieve all companies
    When in company I send a GET request to "/api/v1/companies"
    Then the response status should be 200
    And the response should contain a list of companies
