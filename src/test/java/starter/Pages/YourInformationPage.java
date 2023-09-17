package starter.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourInformationPage {

    public static WebDriver driver;

    public YourInformationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='title']")
    public WebElement dashboardInformation;

    @FindBy(css = "h3")
    private WebElement errorTextInformation;

    @FindBy(xpath = "//input[@id='first-name']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='postal-code']")
    public WebElement postCodeInput;

    @FindBy(xpath = "//input[@id='continue']")
    public WebElement continueBtn;

    @FindBy(xpath = "//h2[contains(text(),'Thank you for your order!')]")
    public WebElement confirmationMessage;

    public void inputDataBuyer(String frstNameInpt, String lastNameInpt, String postCodeInpt) {
        firstNameInput.sendKeys(frstNameInpt);
        lastNameInput.sendKeys(lastNameInpt);
        postCodeInput.sendKeys(postCodeInpt);
    }

    public String errortext(){
        return errorTextInformation.getText();
    }

    public boolean verifyDshboardInfo(){
        return dashboardInformation.isDisplayed();
    }
}
