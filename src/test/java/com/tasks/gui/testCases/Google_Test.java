package com.tasks.gui.testCases;

import com.tasks.gui.pages.Google_Page;
import com.tasks.gui.pages.SearchResults_Page;
import com.tasks.gui.pages.W3school_Page;
import io.qameta.allure.*;
import org.openqa.selenium.By;
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

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final ThreadLocal<JSONFileManager> jsonFileManager = new ThreadLocal<>();
    private final ThreadLocal<ExcelFileManager> excelFileManager = new ThreadLocal<>();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc")
    @Issue("Bug")
    public void SearchAndGetResultByText() {
        String searchKeyword = "Selenium WebDriver";

        new Google_Page(driver.get()).navigateTo_HomePage()
                .searchByTextAndIndexList(searchKeyword);
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_001")
    public void checkPageTitle() {
        String expectedResult_pageTitle = "Google";

        new Google_Page(driver.get()).navigateTo_HomePage();
        Google_Page.getCurrentPage_Url();
        Assert.assertTrue(Google_Page.getTitle_Page().equals(expectedResult_pageTitle));
        Assert.assertEquals(Google_Page.getTitle_Page(), expectedResult_pageTitle);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_002")
    public void checkGoogleLogoIsDisplayed() throws IOException {

        new Google_Page(driver.get()).navigateTo_HomePage()
                .takeFullPage_screenShot(driver.get(), "FullPage_Screenshot" + Helper.getCurrentTime("dd/MM/yyyy-HH:mm:ss"));
        Assert.assertTrue(Google_Page.isGoogleLogoDisplayed("googleLogo"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_003")
    public void searchAndGetFirstResult() {
        String searchKeyword = jsonFileManager.get().getTestData("query");
        String indexInList = jsonFileManager.get().getTestData("indexList");
        String indexInPage = jsonFileManager.get().getTestData("indexPage");
        String expectedResult_searchResult = jsonFileManager.get().getTestData("expectedResult_searchResult");

        new Google_Page(driver.get()).navigateTo_HomePage()
                .searchByTextAndIndexList(searchKeyword, indexInList);
        String actualResult_searchResult =
                SearchResults_Page.getTextSearchResults(indexInPage);

        Assert.assertEquals(actualResult_searchResult, expectedResult_searchResult);
        System.out.println("Actual Result: " + actualResult_searchResult + " == " + "Expected Result: "
                + expectedResult_searchResult);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_004")
    @Issue("Bug_004")
    public void searchForFourthResult() {
        // driver.set(BrowserFactory.getBrowser(BrowserFactory.ExecutionType.LOCAL, BrowserFactory.OperatingSystemType.WINDOWS, BrowserFactory.BrowserType.MOZILLA_FIREFOX));
        String searchKeyword = "TestNG";
        String indexInPage = "4";
        String expectedResult_searchResult = "TestNG Tutorial";

        new Google_Page(driver.get()).navigateTo_HomePage()
                .searchByTextAndIndexList(searchKeyword);
        String actualResult_searchResult =
                SearchResults_Page.getTextSearchResults(indexInPage);
        Assert.assertEquals(actualResult_searchResult, expectedResult_searchResult);
        System.out.println("Actual Result: " + actualResult_searchResult + " == " + "Expected Result: "
                + expectedResult_searchResult);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_005")
    @Issue("Bug_005")
    public void searchForSecondResultAndOpen() {
        String searchKeyword = excelFileManager.get().getCellData("query", 2);
        String indexInPage = excelFileManager.get().getCellData("indexInPage", 2);
        String expectedResult_searchResult = excelFileManager.get().getCellData("expectedResult_searchResult", 2);
        //driver.getDriver(); get selenium webdriver native
        String actualResult_currentUrl =
                new Google_Page(driver.get()).navigateTo_HomePage()
                        .searchByTextAndIndexList(searchKeyword)
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
    public void verifyCountryIsEqual() {
        String countryName = "Austria";

        String actualResult_countryName =
                new W3school_Page(driver.get()).navigateTo_HomePage()
                        .getCountryName(countryName);
        Assert.assertTrue(actualResult_countryName.contains("Austria"));
    }

    @Test
    public void verifySearchResults() {
        new Google_Page(driver.get()).navigateTo_HomePage()
                .searchByTextAndIndexList("Selenium WebDriver");
        By searchResult_txt = By.xpath("//div[@id='result-stats']");
        var getSearchResults = driver.get().findElement(searchResult_txt).getText();
        System.out.println("Search results --> " + getSearchResults);
        Assert.assertNotEquals(getSearchResults, "");
    }

    @Test(enabled = false)
    public void searchBy_text_and_index_list() {
        String searchKeyword = "Selenium";
        int indexInList = 2;
        new Google_Page(driver.get()).navigateTo_HomePage()
                .searchByTestAndIndexList_autoSuggest(searchKeyword, indexInList);

    }


    @BeforeMethod
    public void setUp_BeforeMethods() {
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


}
