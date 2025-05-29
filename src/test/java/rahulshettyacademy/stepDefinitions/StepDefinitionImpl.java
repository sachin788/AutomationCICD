package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import junit.framework.Assert;
import org.testng.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
    @Given("I landed on Ecommerce Page")
    public void _landed_on_Ecommerce_Page() throws IOException {
    	landingPage =launchApplication();
    }
    
    @Given ("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
    	productCatalogue= landingPage.loginApplication(username,password);
    }
    
    @When("^I add the product (.+) from Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
    	List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
    }
    
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {
//    	Thread.sleep(2000);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage=checkoutPage.submitOrder();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) {
    	String confrmMessage= confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confrmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    @Then("^\"([^\"]*)\" message is displayed on ConfirmationPage\\.$")
    public void message_is_displayed_on_confirmation_page(String expectedMessage) {
//        String actualMessage = confirmationPage.getErrorMessage();
        Assert.assertEquals(expectedMessage, landingPage.getErrorMessage());
        driver.close();
    }
    
}
