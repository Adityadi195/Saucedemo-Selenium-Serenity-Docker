package starter.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    public static WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='title']")
    public WebElement productHeader;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement basket;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement selectFilter;

    public void addProduct(String productName){
        driver.findElement(By.xpath("//div[text()='"+productName+"']//ancestor::div[@class='inventory_item']/div[2]/div[2]/button")).click();
    }

    public void selectProductFilter(String sort) {
        Select a = new Select(selectFilter);
        a.selectByVisibleText(sort);
    }
}
