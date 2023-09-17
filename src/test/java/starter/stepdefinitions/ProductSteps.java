package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import starter.Pages.ProductPage;
import starter.Hooks;

public class ProductSteps {

    public WebDriver webDriver;

    public ProductSteps() {
        super();
        this.webDriver = Hooks.webDriver;
    }

    @When("User sort product list by {string}")
    public void userSortProductListBy(String filter) {
        ProductPage productPage = new ProductPage(webDriver);
        productPage.selectProductFilter(filter);
    }

    @And("User select item product {string} and {string}")
    public void userSelectItemProductAnd(String onesie, String allthings) {
        ProductPage productPage = new ProductPage(webDriver);
        productPage.addProduct(onesie);
        productPage.addProduct(allthings);
    }

    @And("User select item product {string}")
    public void userSelectItemProduct(String allthings)  {
        ProductPage productPage = new ProductPage(webDriver);
        productPage.addProduct(allthings);
    }

    @And("User click cart button")
    public void userClickCartButton() {
        ProductPage productPage = new ProductPage(webDriver);
        productPage.basket.click();
    }
}
