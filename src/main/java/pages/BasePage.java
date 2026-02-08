package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.Logs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static int ExplicitWaitTime;

    static {
        ExplicitWaitTime = ConfigReader.getExplicitWait();
    }

    protected static int ImplicitWaitTime;

    static {
        ImplicitWaitTime = ConfigReader.getImplicitWait();
    }

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(ImplicitWaitTime));
    }
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    protected static void clickElement(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ExplicitWaitTime));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            scrolling(driver, locator);

            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (Exception e) {
            Logs.warn("Standard click failed for " + locator + ". Trying JavaScript click.");
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected static void enterText(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(ExplicitWaitTime))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    protected void selectByVisibleText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }

    protected static String getElementText(WebDriver driver, By locator) {
            new WebDriverWait(driver, Duration.ofSeconds(ExplicitWaitTime))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).getText();
    }

    protected static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(ExplicitWaitTime))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }

    public boolean VerifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
