package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;
import pages.AddTariffPlanPage;
import pages.HomePage;
import utils.DriverManager;
import utils.Logs;
import utils.TestDataGenerator;

/**
 * End-to-End Test refactored to match project patterns
 */
public class EndToEndTest extends BaseTest {

    private String customerId;

    @Test(priority = 1, groups = {"e2e"}, description = "E2E: Add Customer and Assign Tariff Plan")
    public void testCompleteCustomerJourney() {

        SoftAssert softAssert = new SoftAssert();

        Logs.info("=== End-to-End Test: Complete Customer Journey ===");
        // ========== PART 1: ADD CUSTOMER ==========
        Logs.info("--- Part 1: Add Customer ---");

        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String email = TestDataGenerator.generateEmail();
        String address = TestDataGenerator.generateAddress();
        String mobile = TestDataGenerator.generateMobileNumber();

        Logs.info("Test Data Generated: " + firstName + " " + lastName);

        AddCustomerPage addCustomerPage = new HomePage(DriverManager.getDriver())
                .navigateToAddCustomer();

        addCustomerPage.selectBackgroundCheck("done")
                .fillCustomerDetails(firstName, lastName, email, address, mobile)
                .clickSubmit(); // This returns the page object

        customerId = addCustomerPage.getCustomerId();

        softAssert.assertNotNull(customerId, "Customer ID should not be null");
        softAssert.assertFalse(customerId.isEmpty() || customerId.contains("@"), "Customer ID is invalid!");
        Logs.info("✓ Customer added successfully with ID: " + customerId);


        Logs.info("--- Part 2: Add Tariff Plan to Customer ---");

        AddTariffPlanPage addTariffPlanPage = new HomePage(DriverManager.getDriver())
                .navigateToAddTariffPlan()
                .enterCustomerId(customerId)
                .clickSubmit()
                .selectTariffPlanRadioButton() // Using specific plan as per test pattern
                .clickAddTariffPlanToCustomer();

        softAssert.assertTrue(addTariffPlanPage.isTariffPlanAssignedSuccessfully(),
                "Tariff plan should be assigned successfully");

        Logs.info("✓ Tariff Plan assigned successfully for Customer ID: " + customerId);
        Logs.info("=== End-to-End Test Completed Successfully ===");
    }
}