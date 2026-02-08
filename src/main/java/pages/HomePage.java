package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import java.io.IOException;


public class HomePage {

    private final WebDriver driver;
    private final By addCustomerLink = By.cssSelector("a[href='addcustomer.php']");
    private final By addTariffPlanToCustomerLink = By.cssSelector("a[href='assigntariffplantocustomer.php']");

    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public AddCustomerPage navigateToAddCustomer(){
        BasePage.clickElement(driver,addCustomerLink);
        return new AddCustomerPage(driver);
    }

    public AddTariffPlanPage navigateToAddTariffPlan() {
        BasePage.clickElement(driver,addTariffPlanToCustomerLink);
        return new AddTariffPlanPage(driver);
    }
}
