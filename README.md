### The main Frameworks included in the project:

* Selenium Webdriver
* TestNG
* Allure Report
* Extent Reports

### Project Design:

* Page Object Model (POM) design pattern
* Fluent design approach

### How to run the project main test cases locally:

* A properties file ***"project.properties"*** can be found it *src/main/resources* file path including all the
  configurations needed in the execution
* Execute All testSuites using Command-line opening a command-line terminal on the project root path and
  type `mvn clean test`
* Execute specific test Suite using Command-line opening a command-line terminal on the project root path and
  type`mvn clean test -DsuiteXmlFile=yourSuite.xml`
* After executing, Generate the ***Allure Report*** by opening a command-line terminal on the project root path and
  type `mvn allure:serve`
* Find the Extent Report ***ExtentReports.html*** in the project root path for the latest execution

### Test Cases - Tasks

* Task 1 -> Get Page title
* Task 2 -> 


