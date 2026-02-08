# Complete Installation & Execution Guide

## 📋 Table of Contents
1. [Prerequisites Installation](#prerequisites-installation)
2. [Project Setup](#project-setup)
3. [Running Tests](#running-tests)
4. [Understanding Results](#understanding-results)
5. [Common Issues](#common-issues)

---

## 1️⃣ Prerequisites Installation

### Java JDK Installation

**Windows:**
1. Download JDK 11+ from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Run installer and follow prompts
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Environment Variables → New (System Variables)
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11` (your JDK path)
4. Add to PATH: `%JAVA_HOME%\bin`
5. Verify: Open CMD and type `java -version`

**Linux/Mac:**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-11-jdk

# Mac (using Homebrew)
brew install openjdk@11

# Verify
java -version
```

### Maven Installation

**Windows:**
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Set MAVEN_HOME environment variable:
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
4. Add to PATH: `%MAVEN_HOME%\bin`
5. Verify: Open CMD and type `mvn -version`

**Linux:**
```bash
sudo apt update
sudo apt install maven
mvn -version
```

**Mac:**
```bash
brew install maven
mvn -version
```

### Chrome Browser
- Download and install from [Google Chrome](https://www.google.com/chrome/)

---

## 2️⃣ Project Setup

### Step 1: Navigate to Project Directory

**Windows:**
```batch
cd F:\ITI_ITP\TestingV4\NEZAM\GenAi\Claude_access\NBETask
```

**Linux/Mac:**
```bash
cd /path/to/NBETask
```

### Step 2: Install Project Dependencies

```bash
mvn clean install
```

This command will:
- Download all required dependencies (Selenium, TestNG, etc.)
- Compile the project
- Run initial setup

**Expected Output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: 45.234 s
```

---

## 3️⃣ Running Tests

### Method 1: Interactive Test Runner (Recommended)

**Windows:**
```batch
run-tests.bat
```

**Linux/Mac:**
```bash
chmod +x run-tests.sh
./run-tests.sh
```

Menu will appear:
```
1. End-to-End Test (Recommended)
2. Isolated Tests (TC1 and TC2 separate)
3. Test Case 1 Only (Add Customer)
4. Test Case 2 Only (Add Tariff Plan)
5. Clean and Install Dependencies
6. Exit
```

### Method 2: Direct Maven Commands

**Run End-to-End Test:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

**Run Isolated Tests:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng-isolated.xml
```

**Run Specific Test Class:**
```bash
# Test Case 1 only
mvn test -Dtest=AddCustomerTest

# Test Case 2 only
mvn test -Dtest=AddTariffPlanTest

# End-to-End Test
mvn test -Dtest=EndToEndTest
```

### Method 3: Using IDE

**IntelliJ IDEA:**
1. Open project: File → Open → Select NBETask folder
2. Wait for Maven to import dependencies
3. Navigate to `src/test/resources/testng.xml`
4. Right-click → Run 'testng.xml'

**Eclipse:**
1. File → Import → Existing Maven Project
2. Select NBETask folder
3. Right-click `testng.xml` → Run As → TestNG Suite

---

## 4️⃣ Understanding Results

### Console Output

During execution, you'll see detailed logs:

```
=== End-to-End Test: Complete Customer Journey ===

--- Part 1: Add Customer ---
Test Data Generated:
First Name: Ahmed
Last Name: Hassan
Email: test8a4f9c2b@example.com
Address: 245 Tahrir Street, Cairo
Mobile: 01012345678
Navigated to Add Customer page
✓ Customer added successfully with ID: 123456

--- Part 2: Add Tariff Plan to Customer ---
Navigated to Add Tariff Plan to Customer page
Assigned Tariff Plan 3 to Customer ID: 123456
✓ Tariff Plan assigned successfully: Platinum
✓ Customer ID verified: 123456

=== End-to-End Test Completed Successfully ===
```

### HTML Reports

After execution, open reports:

**Windows:**
```
target\surefire-reports\index.html
target\surefire-reports\emailable-report.html
```

**Linux/Mac:**
```
target/surefire-reports/index.html
target/surefire-reports/emailable-report.html
```

Reports show:
- ✅ Passed tests (green)
- ❌ Failed tests (red)
- ⏭️ Skipped tests (yellow)
- Execution time
- Stack traces for failures

---

## 5️⃣ Common Issues & Solutions

### Issue 1: Maven Command Not Found

**Error:**
```
'mvn' is not recognized as an internal or external command
```

**Solution:**
- Verify Maven installation: Restart CMD/Terminal
- Check PATH variable includes Maven bin folder
- Reinstall Maven following installation steps above

### Issue 2: Java Version Error

**Error:**
```
Failed to execute goal... source release 11 requires target release 11
```

**Solution:**
- Install JDK 11 or higher
- Verify with `java -version`
- Set JAVA_HOME correctly

### Issue 3: WebDriver Error

**Error:**
```
The path to the driver executable must be set
```

**Solution:**
- Framework uses WebDriverManager (auto-downloads drivers)
- Ensure internet connection on first run
- WebDriverManager caches drivers for future use

### Issue 4: Element Not Found

**Error:**
```
NoSuchElementException: Unable to locate element
```

**Solution:**
- Guru99 demo site may be down or changed
- Check internet connection
- Increase timeout in `config.properties`:
  ```properties
  explicitWait=20
  ```

### Issue 5: Port Already in Use

**Error:**
```
Address already in use
```

**Solution:**
- Close other browser instances
- Kill chromedriver processes:
  - Windows: Task Manager → End chromedriver.exe
  - Linux/Mac: `pkill chromedriver`

### Issue 6: Tests Run But Browser Doesn't Open

**Solution:**
- Check if Chrome is installed
- Try different browser in `config.properties`:
  ```properties
  browser=firefox
  ```

---

## 🎯 Test Execution Workflow

```
1. User runs test suite
   ↓
2. Framework initializes WebDriver (Chrome)
   ↓
3. Navigates to Guru99 Telecom site
   ↓
4. Executes Test Case 1:
   - Opens Add Customer page
   - Fills form with random data
   - Captures Customer ID
   ↓
5. Executes Test Case 2:
   - Opens Add Tariff Plan page
   - Enters Customer ID from TC1
   - Selects and assigns tariff plan
   ↓
6. Verifies results
   ↓
7. Generates reports
   ↓
8. Closes browser
```

---

## 📊 Expected Test Results

### Successful Execution

```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] BUILD SUCCESS
[INFO] Total time: 1:23.456 s
```

### View Reports

HTML Report shows:
- **Test Name:** testCompleteCustomerJourney
- **Status:** PASSED ✅
- **Duration:** ~45 seconds
- **Details:** Customer created and tariff plan assigned

---

## 🔧 Configuration Options

Edit `src/test/resources/config.properties`:

```properties
# Change browser
browser=chrome          # Options: chrome, firefox, edge

# Modify URL
baseUrl=https://demo.guru99.com/telecom/index.html

# Adjust timeouts (in seconds)
implicitWait=10
explicitWait=15
pageLoadTimeout=30
```

---

## 📁 Project Files Overview

| File/Folder | Purpose |
|------------|---------|
| `pom.xml` | Maven configuration & dependencies |
| `src/main/java/pages/` | Page Object classes |
| `src/main/java/utils/` | Helper utilities |
| `src/test/java/tests/` | Test classes |
| `src/test/resources/` | Configuration files |
| `run-tests.bat` | Windows test runner |
| `run-tests.sh` | Linux/Mac test runner |
| `README.md` | Full documentation |
| `QUICKSTART.md` | Quick start guide |

---

## 🚀 Next Steps

After successful test execution:

1. ✅ Review HTML reports
2. 📝 Customize test data in `TestDataGenerator.java`
3. 🎯 Add more test scenarios
4. 🔄 Integrate with CI/CD (Jenkins, GitHub Actions)
5. 📊 Add screenshot capture on failures
6. 📧 Configure email reports

---

## ✅ Checklist

Before running tests, ensure:

- [ ] Java JDK 11+ installed (`java -version`)
- [ ] Maven 3.6+ installed (`mvn -version`)
- [ ] Chrome browser installed
- [ ] Project dependencies installed (`mvn clean install`)
- [ ] Internet connection active
- [ ] Guru99 demo site accessible

---

## 📞 Support

If you encounter issues:

1. Check this guide's troubleshooting section
2. Review console error messages
3. Verify all prerequisites installed correctly
4. Check `target/surefire-reports` for detailed logs

---

**Good luck with your automation testing! 🎉**
