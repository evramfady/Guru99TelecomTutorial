@echo off
echo ========================================
echo NBE Telecom Automation Test Runner
echo ========================================
echo.

:menu
echo Select test suite to run:
echo 1. End-to-End Test (Recommended)
echo 2. Isolated Tests (TC1 and TC2 separate)
echo 3. Test Case 1 Only (Add Customer)
echo 4. Test Case 2 Only (Add Tariff Plan)
echo 5. Clean and Install Dependencies
echo 6. Exit
echo.
set /p choice="Enter your choice (1-6): "

if "%choice%"=="1" goto e2e
if "%choice%"=="2" goto isolated
if "%choice%"=="3" goto tc1
if "%choice%"=="4" goto tc2
if "%choice%"=="5" goto clean
if "%choice%"=="6" goto end
goto menu

:e2e
echo.
echo Running End-to-End Tests...
call mvn test -DsuiteXmlFile=src/test/resources/testng.xml
goto report

:isolated
echo.
echo Running Isolated Tests...
call mvn test -DsuiteXmlFile=src/test/resources/testng-isolated.xml
goto report

:tc1
echo.
echo Running Test Case 1: Add Customer...
call mvn test -Dtest=AddCustomerTest
goto report

:tc2
echo.
echo Running Test Case 2: Add Tariff Plan...
call mvn test -Dtest=AddTariffPlanTest
goto report

:clean
echo.
echo Cleaning and installing dependencies...
call mvn clean install -DskipTests
echo.
echo Dependencies installed successfully!
pause
goto menu

:report
echo.
echo ========================================
echo Test Execution Completed!
echo ========================================
echo.
echo Generating Allure Report...
call mvn allure:report
echo.
echo Reports location:
echo - target\allure-report\index.html (Allure Report)
echo - target\surefire-reports\index.html (Surefire Report)
echo - target\surefire-reports\emailable-report.html (Emailable Report)
echo.
echo Opening Allure Report in browser...
timeout /t 2
if exist "target\allure-report\index.html" (
    start "" "target\allure-report\index.html"
) else (
    echo Warning: Allure report not found at target\allure-report\index.html
)
echo.
pause
goto menu

:end
echo.
echo Goodbye!
exit
