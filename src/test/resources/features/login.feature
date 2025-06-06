Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I open the login page
    When I enter valid username and password
    And I click the login button
    Then I should see the homepage

  Scenario: Unsuccessful login with invalid credentials
    Given I open the login page
    When I enter invalid username or password
    And I click the login button
    Then I should see the alert message