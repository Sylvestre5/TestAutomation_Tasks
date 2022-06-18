package com.tasks.gui.testCases;

import com.tasks.gui.pages.W3school_Page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.browser.BrowserActions;
import utilities.browser.BrowserFactory;

public class W3schools_Test {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Test
    public void task_007_verifyCountryInTable() {
        new W3school_Page(driver.get()).navigateTo_HomePage()
                .getCountryName("Austria");
    }

    @BeforeMethod
    public void setUp_BeforeMethods() {
        driver.set(BrowserFactory.getBrowser());

    }

    @AfterMethod()
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver.get());
    }
}
