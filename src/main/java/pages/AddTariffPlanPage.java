package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AddTariffPlanPage {

    private final WebDriver driver;
    private final By customerIdInput = By.id("customer_id");
    private final By submitButton = By.name("submit");
    private By tariffPlanRadio(int planNumber) {
        return By.xpath("//input[@type='radio' and @value='" + planNumber + "']");
    }
    private final By tariffPlanRadioButton = By.cssSelector("label[for='sele']");
    private final By tariffPlanAssignedSuccessMessage = By.xpath("//h2[contains(text(),'Congratulation Tariff Plan assigned')]");
    private final By addTariffPlanToCustomerButton = By.cssSelector("input[type='submit']");

    public AddTariffPlanPage(WebDriver driver) {
        this.driver=driver;
    }

    public AddTariffPlanPage enterCustomerId(String customerId) {
        BasePage.enterText(driver,customerIdInput, customerId);
        return this;
    }

    public AddTariffPlanPage clickSubmit() {
        BasePage.clickElement(driver,submitButton);
        return this;
    }
    public AddTariffPlanPage clickAddTariffPlanToCustomer() {
        BasePage.clickElement(driver,addTariffPlanToCustomerButton);
        return this;
    }

    public AddTariffPlanPage selectTariffPlanRadioButton() {
        BasePage.scrolling(driver, tariffPlanRadioButton);
        BasePage.clickElement(driver, tariffPlanRadioButton);
        return this;
    }

    public AddTariffPlanPage selectTariffPlan(int planNumber) {
        BasePage.clickElement(driver, tariffPlanRadio(planNumber));
        return this;
    }

    public boolean isTariffPlanAssignedSuccessfully() {
        return BasePage.isElementDisplayed(driver,tariffPlanAssignedSuccessMessage);
    }

    public AddTariffPlanPage assignTariffPlan(String customerId, int planNumber) {
        enterCustomerId(customerId);
        clickSubmit();
        selectTariffPlan(planNumber);
        clickAddTariffPlanToCustomer();
        return this;
    }
}
