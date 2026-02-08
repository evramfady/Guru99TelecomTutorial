# Quick Start Guide - NBE Telecom Automation

## Step 1: Prerequisites Check
Ensure you have installed:
- ✅ Java JDK 11+ (`java -version`)
- ✅ Maven 3.6+ (`mvn -version`)
- ✅ Chrome Browser

## Step 2: Setup Project

### Windows:
```batch
cd F:\ITI_ITP\TestingV4\NEZAM\GenAi\Claude_access\NBETask
mvn clean install
```

### Linux/Mac:
```bash
cd /path/to/NBETask
mvn clean install
```

## Step 3: Run Tests

### Option A: Using Test Runner (Easiest)
**Windows:**
```batch
run-tests.bat
```

**Linux/Mac:**
```bash
./run-tests.sh
```

### Option B: Using Maven Commands

**Run End-to-End Test:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

**Run Isolated Tests:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng-isolated.xml
```

**Run Specific Test:**
```bash
mvn test -Dtest=EndToEndTest
```

### Option C: Using IDE
1. Open project in IntelliJ IDEA or Eclipse
2. Right-click on `src/test/resources/testng.xml`
3. Select "Run As" → "TestNG Suite"

## Step 4: View Results

After test execution:
1. Check console output for step-by-step logs
2. Open HTML report:
   - Windows: `target\surefire-reports\index.html`
   - Linux/Mac: `target/surefire-reports/index.html`

## Test Scenarios

### Test Case 1: Add Customer
- Navigates to Add Customer page
- Selects "Done" for background check
- Fills customer details with random data
- Captures generated Customer ID

### Test Case 2: Add Tariff Plan
- Uses Customer ID from Test Case 1
- Navigates to Add Tariff Plan page
- Assigns a tariff plan to the customer
- Verifies successful assignment

## Expected Output

```
=== End-to-End Test: Complete Customer Journey ===

--- Part 1: Add Customer ---
Test Data Generated:
First Name: Ahmed
Last Name: Hassan
Email: test8a4f9c2b@example.com
Address: 245 Tahrir Street, Cairo
Mobile: 01012345678
✓ Customer added successfully with ID: 123456

--- Part 2: Add Tariff Plan to Customer ---
✓ Tariff Plan assigned successfully: Platinum
✓ Customer ID verified: 123456

=== End-to-End Test Completed Successfully ===
```

## Troubleshooting

**Issue: Maven command not found**
- Install Maven and add to PATH

**Issue: Java version error**
- Install JDK 11 or higher

**Issue: Browser driver error**
- Internet connection required for first run
- WebDriverManager will auto-download drivers

**Issue: Tests failing**
- Check internet connection
- Verify Guru99 demo site is accessible
- Try increasing timeout in config.properties

## Configuration

Edit `src/test/resources/config.properties`:

```properties
# Change browser
browser=chrome  # Options: chrome, firefox, edge

# Change URL (if needed)
baseUrl=https://demo.guru99.com/telecom/index.html

# Adjust timeouts
implicitWait=10
explicitWait=15
```

## Project Structure Summary

```
NBETask/
├── src/main/java/pages/      # Page Objects
├── src/main/java/utils/      # Utilities
├── src/test/java/tests/      # Test Classes
├── src/test/resources/       # Config & TestNG XML
├── pom.xml                   # Maven dependencies
└── README.md                 # Full documentation
```

## Next Steps

1. ✅ Run tests successfully
2. 📊 Review HTML reports
3. 🔧 Customize test data in TestDataGenerator
4. 🎯 Add more test scenarios
5. 🚀 Integrate with CI/CD pipeline

## Support

For issues or questions:
- Check README.md for detailed documentation
- Review console logs for error details
- Verify all prerequisites are installed

---
**Happy Testing! 🎉**
