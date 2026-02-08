package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import java.io.IOException;
import java.time.Duration;


public class AddCustomerPage{

    private final WebDriver driver;
    private final By backgroundCheckDoneRadio = By.id("done");
    private final By backgroundCheckPendingRadio = By.id("pending");
    private final By firstNameInput = By.id("fname");
    private final By lastNameInput = By.id("lname");
    private final By emailInput = By.id("email");
    private final By addressTextarea = By.name("addr");
    private final By mobileInput = By.id("telephoneno");
    private final By submitButton = By.xpath("//input[@type='submit' and @value='Submit']");
    private final By ResetButton = By.name("reset");

    private final By customerIdText = By.xpath("//td[@align='center']/h3");


    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public AddCustomerPage selectBackgroundCheck(String status) {
        if (status.equalsIgnoreCase("done")) {
            BasePage.clickElement(driver, backgroundCheckDoneRadio);
        } else {
            BasePage.clickElement(driver,backgroundCheckPendingRadio);
        }
        return this;
    }

    public AddCustomerPage fillCustomerDetails(String firstName, String lastName, 
                                                String email, String address, String mobile) {
        BasePage.scrolling(driver, firstNameInput);
        BasePage.enterText(driver,firstNameInput, firstName);
        BasePage.scrolling(driver, lastNameInput);
        BasePage.enterText(driver,lastNameInput, lastName);
        BasePage.scrolling(driver, emailInput);
        BasePage.enterText(driver,emailInput, email);
        BasePage.scrolling(driver, addressTextarea);
        BasePage.enterText(driver,addressTextarea, address);
        BasePage.scrolling(driver, mobileInput);
        BasePage.enterText(driver,mobileInput, mobile);
        return this;
    }

    public AddCustomerPage clickSubmit() {
        BasePage.clickElement(driver, submitButton);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            System.out.println("Customer creation failed because: " + text);
        } catch (Exception e) {
            // No alert? Good, we probably moved to the next page
        }
        return this;
    }

    public String getCustomerId() {
        return BasePage.getElementText(driver, customerIdText);
    }

    public String addCustomer(String firstName, String lastName, 
                             String email, String address, String mobile)
    {
        selectBackgroundCheck("done");
        fillCustomerDetails(firstName, lastName, email, address, mobile);
        clickSubmit();
        return getCustomerId();
    }
}
