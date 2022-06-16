package com.tasks.gui.testCases;

import com.tasks.gui.pages.Google_Page;
import com.tasks.gui.pages.SearchResults_Page;
import com.tasks.gui.pages.W3school_Page;
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
    ;
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final ThreadLocal<JSONFileManager> jsonFileManager = new ThreadLocal<>();

    private final ThreadLocal<ExcelFileManager> excelFileManager = new ThreadLocal<>();


    @BeforeMethod
    public void setUpBeforeMethod() {
        driver.set(BrowserFactory.getBrowser());
        jsonFileManager.set(new JSONFileManager(Helper
                .getProperty("project.properties", "googleJson")));
        excelFileManager.set(new ExcelFileManager(new File(Helper
                .getProperty("project.properties", "googleExcel"))));
        excelFileManager.get().switchToSheet("google");

    }

    @AfterMethod()
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver.get());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_001")
    @Issue("Bug_002")
    public void task_001_CheckPageTitle() {
        String expectedResult_pageTitle = "Google";
        new Google_Page(driver.get()).navigateTo_HomePage();
        Google_Page.getCurrentPage_Url();
        Assert.assertTrue(Google_Page.getTitle_Page().contains(expectedResult_pageTitle));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_002")
    @Issue("Bug_002")
    public void task_002_CheckGoogleLogo_isDisplayed() throws IOException {
        new Google_Page(driver.get()).navigateTo_HomePage()
                .isGoogleLogoDisplayed();
        Google_Page.takeFullPage_screenShot(driver.get(), "FullPage_Screenshot");
        Google_Page.takeWebElement_screenshot("googleLogo");


//        Assert.assertTrue(GooglePage.isGoogleLogoDisplayed());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc")
    @Issue("Bug")
    public void task_search_getResultByText() {
        String searchKeyword = "Selenium WebDriver";
        new Google_Page(driver.get()).navigateTo_HomePage()
                .searchByText(searchKeyword);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_003")
    @Issue("Bug_003")
    public void task_003_search_getFirstResult() {
        String searchKeyword = jsonFileManager.get().getTestData("query");
        int indexInList = Integer.parseInt(jsonFileManager.get().getTestData("indexList"));
        String indexInPage = jsonFileManager.get().getTestData("indexPage");
        String expectedResult_searchResult = jsonFileManager.get().getTestData("expectedResult_searchResult");

        new Google_Page(driver.get()).navigateTo_HomePage()
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

        new Google_Page(driver.get()).navigateTo_HomePage()
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
        String searchKeyword = excelFileManager.get().getCellData("query", 2);
        String indexInPage = excelFileManager.get().getCellData("indexInPage", 2);
        String expectedResult_searchResult = excelFileManager.get().getCellData("expectedResult_searchResult", 2);

        String actualResult_currentUrl = new Google_Page(driver.get()).navigateTo_HomePage()
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
        String actualResult_countryName = new W3school_Page(driver.get()).navigateTo_HomePage()
                .getCountryName(countryName);
        Assert.assertTrue(actualResult_countryName.contains("Austria"));


    }

}
