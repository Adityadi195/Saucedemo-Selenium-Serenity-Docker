package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import starter.Pages.CartPage;
import starter.Pages.LoginPage;
import starter.Pages.YourInformationPage;
import starter.Hooks;

public class CartSteps {
    public WebDriver webDriver;

    public CartSteps() {
        super();
        this.webDriver = Hooks.webDriver;
    }

    @And("User click checkout button")
    public void userClickCheckoutButton() {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.checkout.click();
    }

    @And("User delete product {string}")
    public void userDeleteProduct(String deleteProduct) {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.deleteProduct(deleteProduct);
    }

    //NEGATIVE
    @Given("User on login page")
    public void userOnLoginPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.verifyLandingPage());
    }

    @When("User input {string} as UserName and input {string} as Password")
    public void userInputAsUserNameAndInputAsPassword(String userName, String passWord) throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginInput(userName, passWord);
        loginPage.clickLogin();
    }

    @Then("User already on product page")
    public void userAlreadyOnProductPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.verifyDshboard());
    }

    @Then("User already on checkout information")
    public void userAlreadyOnCheckoutInformation()  {
        YourInformationPage pageInfo = new YourInformationPage(webDriver);
        Assert.assertTrue(pageInfo.verifyDshboardInfo());
    }

    @Then("User already on cart page")
    public void userAlreadyOnCartPage() {
        CartPage cartPage = new CartPage(webDriver);
        Assert.assertTrue(cartPage.verifyLinkCart());
    }
}