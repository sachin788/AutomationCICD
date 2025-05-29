
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page
  

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <uname/email> and password <password>
    When I add the product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | uname/email        |      password       |  productName  |
      | lzzy7068@gmail.com |      Asdfgh@1234    |  ZARA COAT 3  |
