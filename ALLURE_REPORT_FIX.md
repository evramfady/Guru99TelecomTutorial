# Allure Report Automatic Opening Fix

## Summary
Fixed the Allure report issue so that the report automatically opens in your default browser after every test run.

## Changes Made

### 1. **pom.xml** 
- Updated the Allure Maven plugin configuration to:
  - Use the `${allureMaven}` version property for consistency (2.12.0)
  - Added explicit `reportDirectory` configuration pointing to `${project.build.directory}/allure-report`
  - This ensures reports are generated in the correct location

### 2. **run-tests.bat** (Windows Batch Script)
- Modified the `:report` section to:
  - Automatically execute `mvn allure:report` after tests complete
  - Wait 2 seconds for the report to be generated
  - Check if the report file exists
  - Automatically open `target\allure-report\index.html` in the default browser using `start` command
  - Display all available report locations for reference

### 3. **run-tests.sh** (Linux/Mac Shell Script)
- Modified the `show_report()` function to:
  - Automatically execute `mvn allure:report` after tests complete
  - Wait 2 seconds for the report to be generated
  - Detect the operating system (Linux, macOS, or Windows Git Bash)
  - Automatically open the report using the appropriate command:
    - **Linux**: `xdg-open` command
    - **macOS**: `open` command
    - **Windows (Git Bash)**: `start` command
  - Display all available report locations for reference

## How It Works Now

1. When you run any test option (1-4) in either script, tests execute as normal
2. After tests complete, the scripts automatically:
   - Generate the Allure report using `mvn allure:report`
   - Open the generated report in your default browser
   - Display a summary of all available report locations
   - Wait for you to press Enter before returning to the menu

## Report Locations

After each test run, reports are available at:
- **Allure Report**: `target/allure-report/index.html` (newly auto-opened)
- **Surefire Report**: `target/surefire-reports/index.html`
- **Emailable Report**: `target/surefire-reports/emailable-report.html`

## Troubleshooting

If the Allure report doesn't open automatically:
1. Check that `target/allure-report/index.html` exists after running tests
2. Ensure you have Maven installed and in your system PATH
3. On Linux, ensure `xdg-open` is available
4. You can manually navigate to the report location and open `index.html` in your browser

## Testing

Run the tests using:
- **Windows**: `run-tests.bat`
- **Linux/Mac**: `bash run-tests.sh`

The Allure report will now automatically open after each test execution!

