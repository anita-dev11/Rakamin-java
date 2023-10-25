Feature: Login Page Aplikasi Swag Labs

  Scenario: Success Login
    Given Halaman login Swag Labs
    When Input username
    And Password
    And Click login button
    Then User on dashboard page

  Scenario: Failed Login
    Given Halaman login Swag Labs
    When Input username
    And Input Invalid password
    And Click login button
    Then User get error message