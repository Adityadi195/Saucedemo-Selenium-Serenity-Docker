package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import starter.Pages.LoginPage;
import starter.Hooks;

public class LoginSteps {
    public WebDriver webDriver;

    public LoginSteps(){
        super();
        this.webDriver = Hooks.webDriver;
    }

    @Given("User already on login page")
    public void verifyLandingPage()  {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.verifyLandingPage());
    }

    @When("User input {string} as userName and input {string} as password")
    public void inputCredential(String userName, String password)  {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginInput(userName, password);
        loginPage.clickLogin();
    }

    @Then("User already on sales page")
    public void verifyDashboard() {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.verifyDshboard());
    }

    @Then("User see {string} error text on login page")
    public void verifyErrorText(String errorText)  {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(errorText, loginPage.textError());
    }
}
