package starter.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;
import java.util.List;

public class CheckoutOverviewPage {
    public static WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='title']")
    public WebElement title;

    @FindBy(xpath = "//button[@id='finish']")
    public WebElement finishBtn;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    public WebElement itemTotal;

    By priceBar = By.cssSelector(".cart_item");

    By inventoryPriceBar = By.cssSelector(".inventory_item_price");

    public void verifyInvoice(String invoiceProduct) {
        driver.findElement(By.xpath("//div[text()='" + invoiceProduct + "']//ancestor::div[@class='cart_item']/div[2]/a/div")).isDisplayed();
    }

    public boolean verifyTitleOverview() {
        return title.isDisplayed();
    }

    //menjumlahkan total harga barang yang telah di checkout
    public String getPriceBar() {
        List<WebElement> cartItems = driver.findElements(priceBar);
        double totalPrice = 0.0;
        for (WebElement cartItem : cartItems) {
            WebElement priceElement = cartItem.findElement(inventoryPriceBar);
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText.replaceAll("[^0-9, .]", ""));
            totalPrice += price;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return String.valueOf("$" + decimalFormat.format(totalPrice));
    }

    //verifikasi harga Total Item yang ada di halaman invoice
    public String getItemTotal() {
        double subtotal = Double.parseDouble(itemTotal.getText().replaceAll("[^0-9, .]", ""));
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return String.valueOf("Item total: $" + decimalFormat.format(subtotal));
    }

    //menghitung harga pajak dari jumlah barang yang telah di hitung dan diverifikasi
    public String getTotalTax() {
        double subtotal = Double.parseDouble(itemTotal.getText().replaceAll("[^0-9, .]", ""));
        double taxrate = 0.08;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double taxAmounts = subtotal * taxrate;
        return String.valueOf("Tax: $" + decimalFormat.format(taxAmounts));
    }

    //menjumlahkan harga barang dan pajak
    public String getTotalResult() {
        double subtotal = Double.parseDouble(itemTotal.getText().replaceAll("[^0-9, .]", ""));
        double tax = Double.parseDouble(getTotalTax().replaceAll("[^0-9, .]", ""));
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double totalResult = roundAvoid(subtotal + tax, 2);
        return String.valueOf("Total: $" + decimalFormat.format(totalResult));
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
