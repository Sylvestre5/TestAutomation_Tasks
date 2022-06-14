package testCases;

import gui.tasks.W3school_Page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.browser.BrowserActions;
import utilities.browser.BrowserFactory;

public class W3schools_Test {
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
    public void task_007_verifyCountryInTable() {
        new W3school_Page(driver).navigateTo_HomePage()
                .getCountryName("Austria");
    }


}
