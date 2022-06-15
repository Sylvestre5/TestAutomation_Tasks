package testCases;

import gui.tasks.Google_Page;
import gui.tasks.SearchResults_Page;
import gui.tasks.W3school_Page;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.actions.Helper;
import utilities.browser.BrowserActions;
import utilities.browser.BrowserFactory;
import utilities.dataDriven.ExcelFileManager;
import utilities.dataDriven.JSONFileManager;

import java.io.File;
import java.io.IOException;


public class Google_Test {
    private WebDriver driver;

    JSONFileManager jsonFileManager;

    ExcelFileManager excelFileManager;


    @BeforeMethod
    public void setUpBeforeMethod() {
        driver = BrowserFactory.getBrowser();
        jsonFileManager = new JSONFileManager(Helper
                .getProperty("project.properties", "googleJson"));
        excelFileManager = new ExcelFileManager(new File(Helper
                .getProperty("project.properties", "googleExcel")));
        excelFileManager.switchToSheet("google");

    }

    @AfterMethod(enabled = false)
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_001")
    @Issue("Bug_002")
    public void task_001_CheckPageTitle() {
        String expectedResult_pageTitle = "Google";
        new Google_Page(driver).navigateTo_HomePage();
        Google_Page.getCurrentPage_Url();
        Assert.assertTrue(Google_Page.getTitle_Page().contains(expectedResult_pageTitle));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_002")
    @Issue("Bug_002")
    public void task_002_CheckGoogleLogo_isDisplayed() throws IOException {
        new Google_Page(driver).navigateTo_HomePage()
                .isGoogleLogoDisplayed();
        Google_Page.getScreenshot(driver, "tesSc");

//        Assert.assertTrue(GooglePage.isGoogleLogoDisplayed());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc")
    @Issue("Bug")
    public void task_search_getResultByText() {
        String searchKeyword = "Selenium WebDriver";
        new Google_Page(driver).navigateTo_HomePage()
                .searchByText(searchKeyword);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_003")
    @Issue("Bug_003")
    public void task_003_search_getFirstResult() {
        String searchKeyword = jsonFileManager.getTestData("query");
        int indexInList = Integer.parseInt(jsonFileManager.getTestData("indexList"));
        String indexInPage = jsonFileManager.getTestData("indexPage");
        String expectedResult_searchResult = jsonFileManager.getTestData("expectedResult_searchResult");

        new Google_Page(driver).navigateTo_HomePage()
                .searchByTextAndIndex_fromList(searchKeyword, indexInList);
        String actualResult_searchResult = SearchResults_Page.getTextSearchResults(indexInPage);

        Assert.assertEquals(actualResult_searchResult, expectedResult_searchResult);
        System.out.println("Actual Result: " + actualResult_searchResult + " == " + "Expected Result: "
                + expectedResult_searchResult);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_004")
    @Issue("Bug_004")
    public void task_004_search_getFourthResult() {
        String searchKeyword = "TestNG";
        String indexInPage = "4";
        String expectedResult_searchResult = "TestNG Tutorial";

        new Google_Page(driver).navigateTo_HomePage()
                .searchByText(searchKeyword);
        String actualResult_searchResult = SearchResults_Page.getTextSearchResults(indexInPage);

        Assert.assertEquals(actualResult_searchResult, expectedResult_searchResult);
        System.out.println("Actual Result: " + actualResult_searchResult + " == " + "Expected Result: "
                + expectedResult_searchResult);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_005")
    @Issue("Bug_005")
    public void task_005_search_openSecondResult() {
        String searchKeyword = excelFileManager.getCellData("query", 2);
        String indexInPage = excelFileManager.getCellData("indexInPage", 2);
        String expectedResult_searchResult = excelFileManager.getCellData("expectedResult_searchResult", 2);

        String actualResult_currentUrl = new Google_Page(driver).navigateTo_HomePage()
                .searchByText(searchKeyword)
                .navigateTo_cucumberSearchResult(indexInPage)
                .getCurrentPage_Url();
        Assert.assertEquals(actualResult_currentUrl, expectedResult_searchResult);
        System.out.println("Actual Result: " + actualResult_currentUrl + " == " + "Expected Result: "
                + expectedResult_searchResult);


    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://the-internet.herokuapp.com/checkboxes")
    @TmsLink("Tc_006")
    @Issue("Bug_006")
    public void task_007_verifyCountry() {
        String countryName = "Austria";
        String actualResult_countryName = new W3school_Page(driver).navigateTo_HomePage()
                .getCountryName(countryName);
        Assert.assertTrue(actualResult_countryName.contains("Austria"));


    }

}
