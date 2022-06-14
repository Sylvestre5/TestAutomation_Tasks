package testCases;

import gui.tasks.CheckBoxPage;
import gui.tasks.GooglePage;
import gui.tasks.SearchResultsPage;
import gui.tasks.W3schoolPage;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserActions;
import utilities.BrowserFactory;

import java.io.IOException;


public class Google_test {
    private WebDriver driver;

    @BeforeMethod
    public void setUpBeforeMethod() {
        driver = BrowserFactory.getBrowser();

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
        new GooglePage(driver).navigateTo_HomePage();
        GooglePage.getCurrentPage_Url();
        Assert.assertTrue(GooglePage.getTitle_Page().contains(expectedResult_pageTitle));


    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_002")
    @Issue("Bug_002")
    public void task_002_CheckGoogleLogo_isDisplayed() throws IOException {
        new GooglePage(driver).navigateTo_HomePage()
                .isGoogleLogoDisplayed();
        GooglePage.getScreenshot(driver,"tesSc");

//        Assert.assertTrue(GooglePage.isGoogleLogoDisplayed());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc")
    @Issue("Bug")
    public void task_search_getResultByText() {
        String searchKeyword = "Selenium WebDriver";
        new GooglePage(driver).navigateTo_HomePage()
                .searchByText(searchKeyword);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_003")
    @Issue("Bug_003")
    public void task_003_search_getFirstResult() {
        String searchKeyword = "Selenium WebDriver";
        int indexInList = 1;
        String indexInPage = "1";
        String expectedResult_searchResult = "WebDriver - Selenium";

        new GooglePage(driver).navigateTo_HomePage()
                .searchByTextAndIndex_fromList(searchKeyword, indexInList);
        String actualResult_searchResult = SearchResultsPage.getTextSearchResults(indexInPage);

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

        new GooglePage(driver).navigateTo_HomePage()
                .searchByText(searchKeyword);
        String actualResult_searchResult = SearchResultsPage.getTextSearchResults(indexInPage);

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
        String searchKeyword = "Cucumber IO";
        String indexInPage = "2";
        String expectedResult_searchResult = "https://www.linkedin.com";

        String actualResult_currentUrl = new GooglePage(driver).navigateTo_HomePage()
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
    public void task_006_verifyCheckBoxes_checked() {
        int indexInCheckBoxes = 1;
        new CheckBoxPage(driver).navigateTo_HomePage()
                .selectCheckBox(indexInCheckBoxes);
        Assert.assertTrue(driver.findElement(CheckBoxPage.isCheckBoxes).isSelected());
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://the-internet.herokuapp.com/checkboxes")
    @TmsLink("Tc_006")
    @Issue("Bug_006")
    public void task_007_verifyCountry() {
        String countryName = "Austria";
        String actualResult_countryName = new W3schoolPage(driver).navigateTo_HomePage()
                .getCountryName(countryName);
        Assert.assertTrue(actualResult_countryName.contains("Austria"));


    }

}
