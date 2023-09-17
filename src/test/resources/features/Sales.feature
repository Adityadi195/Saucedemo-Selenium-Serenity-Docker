@Sales
Feature: Sales
  As a user I want select and buy item on website SauceDemo

  Scenario: User checkout product on SauceDemo
    Given User already on login page
    When User input "standard_user" as userName and input "secret_sauce" as password
    Then User already on sales page
    When User sort product list by "Name (Z to A)"
    And User select item product "Test.allTheThings() T-Shirt (Red)" and "Sauce Labs Onesie"
    And User click cart button
    Then User already on cart page
    When User delete product "Test.allTheThings() T-Shirt (Red)"
    And User click checkout button
    Then User already on checkout page
    When User enters details firstName "Aditya" lastName "Dwi" and postalCode "1234"
    And User click button continue
    Then User already on overview page
    And User verify product "Sauce Labs Onesie"
    And User verify inventory item price "$7.99"
    And User verify price total "Item total: $7.99"
    And User verify tax "Tax: $0.64"
    And User verify that "Total: $8.63"
    When User click finish button
    Then User able to see  confirmation message as "Thank you for your order!"

  Scenario Outline: User cannot input data checkout with invalid data
    Given User on login page
    When User input "standard_user" as UserName and input "secret_sauce" as Password
    Then User already on product page
    When User select item product "Test.allTheThings() T-Shirt (Red)"
    And User click cart button
    And User click checkout button
    Then User already on checkout information
    When User input "<firstName>" as firstName and input "<lastName>" as lastName and postalCode "<postalCode>"
    And User click continue to your overview page
    Then User see "<errorPopUp>" error popUp on your information page

    Examples:
      | firstName | lastName   | postalCode | errorPopUp                     |
      |           |            |            | Error: First Name is required  |
      |           | Dwi Irawan | 53235      | Error: First Name is required  |
      | Aditya    |            | 12212      | Error: Last Name is required   |
      | Aditya    | Dwi Irawan |            | Error: Postal Code is required |