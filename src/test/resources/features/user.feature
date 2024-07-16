Feature: User API

  Scenario: Save a new User
    Given I have a user with the next details
      | name          | John           |
      | lastname      | Doe            |
      | address       | Cra 7 #34 sur  |
      | position      | Talent Manager |
      | telephone     | 3232342123     |
      | residenceCity | Medellin       |
      | state         | true           |
      | companyId     | 1              |
      | departmentId  | 1              |
    When I send a POST request to "/api/v1/users"
    Then The user response body should be "user saved successfully"

  Scenario:  Get users by company
    When in users I send a GET request to "/api/v1/users?companyId=1"
    Then the response status in users should be 200
    And the response body should contains a list of users

