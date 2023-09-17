package starter;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class Hooks {

    public static RemoteWebDriver initialize(Boolean isRunningOnHub) {
        String url = "https://www.saucedemo.com/";

        RemoteWebDriver webDriver = null;
        URL URL_ADDRESS;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--disable-dev-shm-usage", "--incognito");
        options.setHeadless(true);
        WebDriverManager.chromedriver().setup();

        if (isRunningOnHub) {
            try {
                URL_ADDRESS = new URL(System.getProperty("urlHub", "http://172.21.0.2:4444/wd/hub"));
                webDriver = new RemoteWebDriver(URL_ADDRESS, options);
            } catch (Exception e) {
                System.out.println("Error : " + e);
            }
        } else {
            webDriver = new ChromeDriver(options);
        }
        assert webDriver != null;
        webDriver.get(url);
        return webDriver;
    }

    public static WebDriver webDriver;
    public WebDriver driver;
    public WebDriverWait driverWait;


    @Before
    public void openBrowser() {
        webDriver = initialize(Boolean.parseBoolean(System.getProperty("running-on-hub", "false")));
        driver = webDriver;
        driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }


//    @After
//    public void closeBrowser() throws InterruptedException {
//        Thread.sleep(2000);
//        webDriver.quit();
//    }
}
