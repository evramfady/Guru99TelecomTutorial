#!/bin/bash

echo "========================================"
echo "NBE Telecom Automation Test Runner"
echo "========================================"
echo ""

show_menu() {
    echo "Select test suite to run:"
    echo "1. End-to-End Test (Recommended)"
    echo "2. Isolated Tests (TC1 and TC2 separate)"
    echo "3. Test Case 1 Only (Add Customer)"
    echo "4. Test Case 2 Only (Add Tariff Plan)"
    echo "5. Clean and Install Dependencies"
    echo "6. Exit"
    echo ""
}

run_e2e() {
    echo ""
    echo "Running End-to-End Tests..."
    mvn test -DsuiteXmlFile=src/test/resources/testng.xml
    show_report
}

run_isolated() {
    echo ""
    echo "Running Isolated Tests..."
    mvn test -DsuiteXmlFile=src/test/resources/testng-isolated.xml
    show_report
}

run_tc1() {
    echo ""
    echo "Running Test Case 1: Add Customer..."
    mvn test -Dtest=AddCustomerTest
    show_report
}

run_tc2() {
    echo ""
    echo "Running Test Case 2: Add Tariff Plan..."
    mvn test -Dtest=AddTariffPlanTest
    show_report
}

clean_install() {
    echo ""
    echo "Cleaning and installing dependencies..."
    mvn clean install -DskipTests
    echo ""
    echo "Dependencies installed successfully!"
    read -p "Press Enter to continue..."
}

show_report() {
    echo ""
    echo "========================================"
    echo "Test Execution Completed!"
    echo "========================================"
    echo ""
    echo "Generating Allure Report..."
    mvn allure:report
    echo ""
    echo "Reports location:"
    echo "- target/allure-report/index.html (Allure Report)"
    echo "- target/surefire-reports/index.html (Surefire Report)"
    echo "- target/surefire-reports/emailable-report.html (Emailable Report)"
    echo ""
    echo "Opening Allure Report in browser..."
    sleep 2

    # Determine OS and open report accordingly
    if [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Linux
        if command -v xdg-open &> /dev/null; then
            xdg-open "target/allure-report/index.html" &
        fi
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        open "target/allure-report/index.html" &
    elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
        # Windows (Git Bash, Cygwin)
        start "" "target/allure-report/index.html"
    fi

    if [ ! -f "target/allure-report/index.html" ]; then
        echo "Warning: Allure report not found at target/allure-report/index.html"
    fi
    echo ""
    read -p "Press Enter to continue..."
}

while true; do
    show_menu
    read -p "Enter your choice (1-6): " choice
    
    case $choice in
        1) run_e2e ;;
        2) run_isolated ;;
        3) run_tc1 ;;
        4) run_tc2 ;;
        5) clean_install ;;
        6) 
            echo ""
            echo "Goodbye!"
            exit 0
            ;;
        *)
            echo "Invalid choice. Please select 1-6."
            ;;
    esac
done
