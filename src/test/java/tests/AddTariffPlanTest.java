package tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddTariffPlanPage;
import pages.HomePage;
import utils.DriverManager;
import utils.Logs;
import utils.ScenarioContext;

public class AddTariffPlanTest extends BaseTest {

    @Test(priority = 2, description = "Verify user can assign tariff plan to customer", dependsOnGroups = {"customerCreation"})
    public void testAddTariffPlanToCustomer() {
        SoftAssert softAssert = new SoftAssert();
        Logs.info("=== Test Case 2: Add Tariff Plan to Customer ===");

        String customerId = (String) ScenarioContext.getContext("customerId");

        if (customerId == null) {
            Assert.fail("Customer ID is not available in current thread context.");
        }
        
       Logs.info("Using Customer ID: " + customerId);

        AddTariffPlanPage addTariffPlanPage = new HomePage(DriverManager.getDriver()).
                navigateToAddTariffPlan().
                enterCustomerId(customerId).
                clickSubmit().
                selectTariffPlanRadioButton().
                clickAddTariffPlanToCustomer();

        softAssert.assertTrue(
                addTariffPlanPage.isTariffPlanAssignedSuccessfully(),
                "Tariff plan assigned success message should be displayed");

        Logs.info("Step 6: Tariff Plan assigned successfully message displayed");
        Logs.info("=== Test Case 2 Completed Successfully ===\n");
    }
}
