Feature: Company API

  Scenario: Save a new company
    Given a company with the following details
      | name      | address        | operationCity |
      | Acme Corp | 123 Elm Street | New York      |
    When the client sends a POST request to "/api/v1/companies" with the company details
    Then the response status should be 201
    And the response body should contain "Company saved successfully"

  Scenario: Get all companies
    Given the following companies exist
      | name      | address        | operationCity |
      | Acme Corp | 123 Elm Street | New York      |
      | Beta Inc  | 456 Oak Avenue | Los Angeles   |
    When the client sends a GET request to "/api/v1/companies"
    Then the response status should be 200
    And the response should contain the following companies
      | name      | address        | operationCity |
      | Acme Corp | 123 Elm Street | New York      |
      | Beta Inc  | 456 Oak Avenue | Los Angeles   |
