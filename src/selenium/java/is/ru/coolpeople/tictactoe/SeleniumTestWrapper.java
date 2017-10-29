package is.ru.coolpeople.tictactoe;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class SeleniumTestWrapper {
    static ChromeDriver driver;
    static String baseUrl;
    static String port;

    @BeforeClass
    public static void openBrowser() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("google-chrome-stable");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");

        final DesiredCapabilities dc = new DesiredCapabilities();
        dc.setJavascriptEnabled(true);
        dc.setCapability(
                ChromeOptions.CAPABILITY, chromeOptions
        );

        driver = new ChromeDriver(dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        port = System.getenv("PORT");
        if (port == null) {
            port = "4567";
        }
        baseUrl = "http://localhost:" + port;
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
