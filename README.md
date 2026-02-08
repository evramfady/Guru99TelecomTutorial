# NBE Telecom Automation Framework

## Overview
This is a Selenium WebDriver automation framework using Java, TestNG, and Page Object Model (POM) design pattern for testing the Guru99 Telecom demo application.

## Project Structure
```
NBETask/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── BasePage.java
│   │       │   ├── HomePage.java
│   │       │   ├── AddCustomerPage.java
│   │       │   └── AddTariffPlanPage.java
│   │       └── utils/
│   │           ├── DriverManager.java
│   │           ├── ConfigReader.java
│   │           └── TestDataGenerator.java
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── BaseTest.java
│       │       ├── AddCustomerTest.java
│       │       ├── AddTariffPlanTest.java
│       │       └── EndToEndTest.java
│       └── resources/
│           ├── config.properties
│           ├── testng.xml
│           └── testng-isolated.xml
└── pom.xml
```

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser installed

## Test Cases Covered

### Test Case 1: Add Customer
**Steps:**
1. Open Left side menu and click "Add Customer"
2. Select "Done" for Background Check
3. Fill all fields (first name, last name, email, address, mobile)
4. Click Submit
5. Capture and store the generated Customer ID

### Test Case 2: Add Tariff Plan to Customer
**Steps:**
1. Open Left side menu and click "Add Tariff Plan to Customer"
2. Enter the Customer ID captured earlier
3. Submit
4. Select one of the tariff plans (radio button)
5. Submit
6. Check if Tariff Plan assigned

## Setup Instructions

### 1. Clone or Extract the Project
Place the project in your desired location: `F:\ITI_ITP\TestingV4\NEZAM\GenAi\Claude_access\NBETask`

### 2. Install Dependencies
```bash
cd NBETask
mvn clean install
```

### 3. Update Configuration (Optional)
Edit `src/test/resources/config.properties` to change:
- Browser type (chrome/firefox/edge)
- Base URL
- Timeout values

## Running Tests

### Option 1: Run All Tests (End-to-End)
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Option 2: Run Isolated Tests
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng-isolated.xml
```

### Option 3: Run from IDE
1. Right-click on `testng.xml` or `testng-isolated.xml`
2. Select "Run As" → "TestNG Suite"

### Option 4: Run Specific Test Class
```bash
mvn test -Dtest=EndToEndTest
mvn test -Dtest=AddCustomerTest
mvn test -Dtest=AddTariffPlanTest
```

## Framework Features

### Page Object Model (POM)
- Separates page elements and actions from test logic
- Improves code maintainability and reusability
- Each page has its own class with locators and methods

### Test Isolation
- Each test class extends BaseTest
- Independent WebDriver instances using ThreadLocal
- Tests can run in parallel or sequentially

### Data Generation
- TestDataGenerator utility creates random test data
- Prevents data conflicts in repeated test runs
- Generates realistic Egyptian data (names, addresses, mobile numbers)

### Configuration Management
- Centralized configuration in properties file
- Easy to switch browsers and environments
- Configurable timeouts and URLs

### Reporting
- TestNG HTML reports generated in `target/surefire-reports/`
- Console output with detailed step information
- Assertions with meaningful error messages

## Key Classes

### Pages Package
- **BasePage**: Common methods for all pages (click, enter text, wait, etc.)
- **HomePage**: Navigation menu interactions
- **AddCustomerPage**: Customer creation functionality
- **AddTariffPlanPage**: Tariff plan assignment functionality

### Utils Package
- **DriverManager**: WebDriver lifecycle management
- **ConfigReader**: Configuration properties reader
- **TestDataGenerator**: Random test data generation

### Tests Package
- **BaseTest**: Test setup and teardown
- **AddCustomerTest**: Test Case 1 implementation
- **AddTariffPlanTest**: Test Case 2 implementation
- **EndToEndTest**: Combined E2E test flow

## Browser Support
- Chrome (default)
- Firefox
- Edge

To change browser, update `config.properties`:
```properties
browser=firefox
```

## Troubleshooting

### Issue: Browser driver not found
**Solution**: The framework uses WebDriverManager which automatically downloads drivers. Ensure you have internet connection on first run.

### Issue: Tests fail due to timeout
**Solution**: Increase timeout values in `config.properties` or check your internet connection.

### Issue: Element not found
**Solution**: The Guru99 demo site may change. Update locators in page classes if needed.

## Best Practices Implemented
1. ✅ Page Object Model design pattern
2. ✅ Separation of concerns (pages, tests, utilities)
3. ✅ Reusable components and methods
4. ✅ Explicit waits for element interactions
5. ✅ Meaningful assertions and error messages
6. ✅ Test data generation to avoid hardcoding
7. ✅ ThreadLocal for parallel execution support
8. ✅ Configuration externalization
9. ✅ Comprehensive logging and reporting
10. ✅ Clean code principles

## Author
QA Automation Engineer - NBE Task

## Test Execution Results
After running tests, check:
- Console output for detailed logs
- `target/surefire-reports/index.html` for HTML report
- `target/surefire-reports/emailable-report.html` for email-friendly report
