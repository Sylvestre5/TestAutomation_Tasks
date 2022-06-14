package testCases;

import gui.tasks.Google_Page;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserActions;
import utilities.BrowserFactory;

public class Google_test {
    private WebDriver driver;

    @BeforeMethod
    public void setUpBeforeMethod() {
        driver = BrowserFactory.getBrowser();

    }

    @AfterMethod
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
    public void task_002_CheckGoogleLogo_isDisplayed() {
        new Google_Page(driver).navigateTo_HomePage();
        Assert.assertTrue(Google_Page.isGoogleLogoDisplayed());

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_003")
    @Issue("Bug_003")
    public void task_003_search_getResultByText() {
        new Google_Page(driver).navigateTo_HomePage()
                .openSearchResultsByText_Page("selenium");


    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.google.com/ncr")
    @TmsLink("Tc_004")
    @Issue("Bug_004")
    public void task_004_search_getFirstResult() {
        String searchKeyword = "selenium ";
        int index = 3;
        new Google_Page(driver).navigateTo_HomePage()
                .openSearchResultsByIndex_Page(searchKeyword, index);


    }


}
