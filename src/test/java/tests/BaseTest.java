package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverManager;
import utils.Logs;

import java.io.IOException;
import java.time.Duration;


public class BaseTest {

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws IOException {

        DriverManager.SetUpDriver(browser);
        Logs.info(browser + " driver is opened");

        DriverManager.getDriver().get(ConfigReader.getProperty("BASE_URL"));
        DriverManager.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(15));

        Logs.info("Test setup completed. Browser: " + browser);

        navigateToHome();
    }

    // Enhancement: Separate navigation to allow resetting state between tests
    public void navigateToHome() throws IOException {
        String url = ConfigReader.getBaseUrl();

        if (url == null || url.isEmpty()) {
            throw new RuntimeException("BASE_URL is null! Check your config.properties file.");
        }
        DriverManager.getDriver().get(url);
        Logs.info("Navigated to: " + url);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        Logs.info("Test teardown completed.");
    }
}
