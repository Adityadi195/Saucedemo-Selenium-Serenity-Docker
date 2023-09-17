package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import starter.Pages.CheckoutOverviewPage;
import starter.Hooks;
import static org.junit.Assert.assertEquals;
public class Invoice {

    public WebDriver webDriver;

    public Invoice() {
        super();
        this.webDriver = Hooks.webDriver;
    }

    @And("User click finish button")
    public void theUserClickFinishButton()  {
        CheckoutOverviewPage clickFinish = new CheckoutOverviewPage(webDriver);
        clickFinish.finishBtn.click();
    }

    @And("User verify product {string}")
    public void userVerifyProduct(String verifyProd)  {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        overviewPage.verifyInvoice(verifyProd);
    }

    @Then("User already on overview page")
    public void userAlreadyOnOverviewPage() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);
        Assert.assertTrue(checkoutOverviewPage.verifyTitleOverview());
    }

    //verifikasi jumlah harga yang telah di hitung dari beberapa harga barang
    @And("User verify inventory item price {string}")
    public void userVerifyInventoryItemPrice(String expectedInventoryItemPrice) {
        CheckoutOverviewPage informationPage = new CheckoutOverviewPage(webDriver);
        System.out.println(informationPage.getPriceBar());
        assertEquals(expectedInventoryItemPrice, informationPage.getPriceBar());
    }

    //verifikasi dari hasil harga yang telah di hitung yang di tampilkan di halaman invoice
    @And("User verify price total {string}")
    public void userVerifyPriceTotal(String expectedItemPrice) {
        CheckoutOverviewPage informationPage = new CheckoutOverviewPage(webDriver);
        System.out.println(informationPage.getItemTotal());
        assertEquals(expectedItemPrice, informationPage.getItemTotal());
    }

    //verifikasi hitung total pajak
    @And("User verify tax {string}")
    public void userVerifyTax(String taxCalculate) {
        CheckoutOverviewPage informationPage = new CheckoutOverviewPage(webDriver);
        System.out.println(informationPage.getTotalTax());
        assertEquals(taxCalculate, informationPage.getTotalTax());
    }

    //verifikasi total harga yang telah di tambah dengan jumlah pajak
    @Then("User verify that {string}")
    public void userVerifyThat(String expectedPrice)  {
        CheckoutOverviewPage informationPage = new CheckoutOverviewPage(webDriver);
        System.out.println(informationPage.getTotalResult());
        assertEquals(expectedPrice, informationPage.getTotalResult());
    }
}
