Feature: Retrieve API Version
  Scenario: Get the API version
    When I request the endpoint "/version"
    Then the response should be "1.0"
    And the status code is 200