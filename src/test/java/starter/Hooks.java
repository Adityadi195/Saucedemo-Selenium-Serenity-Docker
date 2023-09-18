package starter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Hooks {

    public static WebDriver webDriver;

    private static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";
    private static final String DEFAULT_HUB_URL = "http://172.21.0.2:4444/wd/hub";
    private static final String HUB_URL_PROPERTY = "urlHub";

    private static RemoteWebDriver initializeWebDriver(boolean isRunningOnHub) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--disable-dev-shm-usage", "--incognito");
        options.setHeadless(true);
        WebDriverManager.chromedriver().setup();

        if (isRunningOnHub) {
            try {
                URL hubUrl = new URL(System.getProperty(HUB_URL_PROPERTY, DEFAULT_HUB_URL));
                return new RemoteWebDriver(hubUrl, options);
            } catch (Exception e) {
                System.out.println("Error : " + e);
                return null;
            }
        } else {
            return new ChromeDriver(options);
        }
    }

    @Before
    public void openBrowser() {
        boolean isRunningOnHub = Boolean.parseBoolean(System.getProperty("running-on-hub", "false"));
        webDriver = initializeWebDriver(isRunningOnHub);
        webDriver.get(SAUCE_DEMO_URL);
    }

    @After
    public void closeBrowser() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
