package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import starter.Pages.YourInformationPage;
import starter.Hooks;
import static org.junit.Assert.assertEquals;
public class DataBuyerSteps {
    public WebDriver webDriver;

    public DataBuyerSteps(){
        super();
        this.webDriver = Hooks.webDriver;
    }

    @And("User click button continue")
    public void userClickButtonContinue() {
        YourInformationPage informationPage = new YourInformationPage(webDriver);
        informationPage.continueBtn.click();
    }

    @And("User input {string} as firstName and input {string} as lastName and postalCode {string}")
    public void userInputAsFirstNameAndInputAsLastNameAndPostalCode(String firstName, String lastName, String postcode) {
        YourInformationPage informationPage = new YourInformationPage(webDriver);
        informationPage.inputDataBuyer(firstName, lastName, postcode);
    }

    @Then("User able to see  confirmation message as {string}")
    public void theUserAbleToSeeConfirmationMessageAs(String expectedMessage) {
        YourInformationPage informationMessage = new YourInformationPage(webDriver);
        assertEquals(expectedMessage,informationMessage.confirmationMessage.getText());
    }

    @Then("User see {string} error popUp on your information page")
    public void userSeeErrorPopUpOnYourInformationPage(String errorPopUp ) {
        YourInformationPage infoPage = new YourInformationPage(webDriver);
        Assert.assertEquals(errorPopUp, infoPage.errortext());
    }

    @And("User click continue to your overview page")
    public void userClickContinueToYourOverviewPage() {
        YourInformationPage infoPage = new YourInformationPage(webDriver);
        infoPage.continueBtn.click();
    }

    @And("User enters details firstName {string} lastName {string} and postalCode {string}")
    public void theUserEntersDetailsFirstnameLastnameAndPostalcode(String firstName, String lastName, String postcode) {
        YourInformationPage informationPage = new YourInformationPage(webDriver);
        informationPage.inputDataBuyer(firstName, lastName, postcode);
    }

    @Then("User already on checkout page")
    public void userAlreadyOnCheckoutPage() {
        YourInformationPage pageInfo = new YourInformationPage(webDriver);
        Assert.assertTrue(pageInfo.verifyDshboardInfo());
    }
}
