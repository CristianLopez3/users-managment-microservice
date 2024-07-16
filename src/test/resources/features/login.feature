Feature: User login

  Scenario: Successful login
    Given the user navigates to the login page
    When the user enters valid credentials
    Then the user is redirected to the home page