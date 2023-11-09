
## Playwright with Java Automation Framework 
This readme file provides comprehensive information on setting up and using the Playwright automation framework with Java. Whether you are a seasoned automation engineer or just getting started, this guide will walk you through the steps to create and execute Web application tests using Playwright and Java.
This project uses Page Object Model (POM), TestNG for test execution, Data-Driven testing, and Keyword-Driven testing. In This Framework building used Playwright for Java to interact with your web application. Add Playwright dependencies to your project's pom.xml and create a directory structure for your framework using pom. TestNG to define and execute your tests and Create a TestNG suite XML file to specify test classes, suites, and test configurations. For data-driven testing, you can create a separate data provider class that reads test data from a file (e.g., Excel). Let's call it TestDataUtil.java for Running tests. Then, in your test class, use the @DataProvider annotation to specify the data provider method and pass data to your test method. Create a set of keyword-driven test scripts using Java methods and annotations then in keyword-driven test methods, use Playwright to interact with the application under test, and for Reporting TestNG's built-in reporting such as HTML or ExtentReports. This framework can be used for any web application to create web tests.

## Installation
Java-jdk 
Eclipse
Maven
Create  Maven project, Go to your project directory and add required libraries/dependencies in the pom.xml file.

## Tools & Libraries 
Below are the Tools and libraries that are used in this framework:
Playwright as automating tool.
Testng as a testing framework.
Testng for running tests in a certain order.
ExtentReports Library for reporting. 
Eclipse as an IDE.
SelectorHub for locating elements in the web application.

## Running Tests
To run the Tests have 2 ways:
1. Running Tests individually.
2. Creating testng.xml file and running through testng.xml file

## Folder Structure
Web_Playwright_Java/src/main/java/base: This is main package for our framework, you find all the webaction methods to interact with web application and common reusable webactions actions.

Web_Playwright_Java/src/main/java/extentlisteners: This package contains lsitners and report manager for reporting, including screenshot.

Web_Playwright_Java/src/main/java/pages: Here, we have the different Java clasess representing each page of the web application. In each Java class particular page  is created that contains different actions that are performed in that page.

Web_Playwright_Java/src/main/java/utilities: This package contains
mailing part of outlook and contains date n time wise screenshots in .png format that are captured when the test is failed during the execution.

Web_Playwright_Java/src/main/resources: This package contains
Log4j properties for maintaing logs and OR properties for locators.

Web_Playwright_Java/src/test/java/base: This package contains
browser launching part.

Web_Playwright_Java/src/test/java/testcases: Here we write all the test cases of this project.

Web_Playwright_Java/src/test/java/utilities: This package contains all the excelreader and datautil coding part.

Web_Playwright_Java/src/test/resources/testdata: This package contains the other non-coding related files like excel file for testdata.

Web_Playwright_Java/reports: This folder contains the extent/ html report when the tests are run and log file which tracks & records all the execution details.

Web_Playwright_Java/test_output/testng.xml: Here you can find all the suite tests for different scenarios.

## Reporting: 
 With the ExtentReport library, we can create interactive and detailed reports for our web test results. We can add steps, logs, screenshots, story, description, testcase or any other relevant information we decide is essential to create an informative and stunning report.







