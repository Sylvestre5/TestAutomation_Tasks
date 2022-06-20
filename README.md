# The main Frameworks included in the project:

* Selenium Webdriver
* TestNG
* Allure Report
* Extent Reports
* Apachi POI

## Project Design:

* Page Object Model (POM) design pattern
* Fluent design approach
* Data Driven 


## Pre-requests:

* Install Java [JDK 17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)


### How to run the project main test cases locally:

* A properties file [project.properties](src/main/resources)  including all the configuration
* * Set the test Data from [TestData](src/test/resources/TestData)
* Execute All testSuites using Command-line opening a command-line terminal on the project root path and
  type `mvn clean test`
* Execute specific test Suite using Command-line opening a command-line terminal on the project root path and
  type`mvn clean test -DsuiteXmlFile=yourSuite*.xml`
* After executing, Generate the ***Allure Report*** by opening a command-line terminal on the project root path and
  type `mvn allure:serve`
* Find the Extent Report [ExtentReports.html](ExtentReports.html) in the project root path for the latest execution and open by any browser

### Test Cases - Tasks

* Open the Tasks from document [View Tasks](https://docs.google.com/document/d/1mY62ZDSiANd9alm0ek_XOrtzgEWAIBqlmeMLud6zeh4/edit?usp=sharing) 
