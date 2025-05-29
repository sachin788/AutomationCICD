
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <uname/email> and password <password>
    Then "Incorrect email or password." message is displayed on ConfirmationPage.

  Examples: 
      | uname/email         |       password       |
      | lzzy70688@gmail.com |      Asdfgh@1234     |