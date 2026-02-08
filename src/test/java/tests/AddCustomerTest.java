package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;
import pages.HomePage;
import utils.DriverManager;
import utils.Logs;
import utils.TestDataGenerator;
import utils.ScenarioContext;

public class AddCustomerTest extends BaseTest {
    
    private String customerId;

    @Test(priority = 1, groups = {"customerCreation"}, description = "Verify user can add a new customer successfully")
    public void testAddCustomer() {

        SoftAssert softAssert = new SoftAssert();

        Logs.info("=== Test Case 1: Add Customer ===");
        
        // Generate test data
        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String email = TestDataGenerator.generateEmail();
        String address = TestDataGenerator.generateAddress();
        String mobile = TestDataGenerator.generateMobileNumber();

        Logs.info("Test Data Generated:");
        Logs.info("First Name: " + firstName);
        Logs.info("Last Name: " + lastName);
        Logs.info("Email: " + email);
        Logs.info("Address: " + address);
        Logs.info("Mobile: " + mobile);


        AddCustomerPage addCustomerPage = new HomePage(DriverManager.getDriver())
                .navigateToAddCustomer()
                .selectBackgroundCheck("done")
                .fillCustomerDetails(firstName, lastName, email, address, mobile);

        addCustomerPage.clickSubmit();

        customerId = addCustomerPage.getCustomerId();

        ScenarioContext.setContext("customerId", customerId);

        softAssert.assertNotNull(customerId, "Customer ID should not be null");
        softAssert.assertFalse(customerId.isEmpty(), "Customer ID should not be empty");

        Logs.info("Step 5: Customer ID captured: " + customerId);
        Logs.info("=== Test Case 1 Completed Successfully ===\n");
    }
}
