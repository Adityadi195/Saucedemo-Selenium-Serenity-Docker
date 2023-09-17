@Login
Feature: Login
  As a user I want to login website sauce demo

  Scenario: Normal login
    Given User already on login page
    When User input "standard_user" as userName and input "secret_sauce" as password
    Then User already on sales page

  Scenario Outline: Invalid login
    Given User already on login page
    When User input "<userName>" as userName and input "<password>" as password
    Then User see "<errorText>" error text on login page
    Examples:
      | userName      | password     | errorText                                                                 |
      | standard_user |              | Epic sadface: Password is required                                        |
      |               | secret_sauce | Epic sadface: Username is required                                        |
      | Aditya        | dwi          | Epic sadface: Username and password do not match any user in this service |
      |               |              | Epic sadface: Username is required                                        |