package utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class BrowserActions {
    static WebDriver driver;

    public enum ConfirmAlertType {
        ACCEPT, DISMISS;
    }

    @Step("Navigate to URL: [{url}]")
    public static void navigateToUrl(WebDriver driver, String url) {
        try {
            Logger.logStep("[Browser Action] Navigate to URL [" + url + "]");
            driver.get(url);
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Step("Close All Opened Browser Windows.....")
    public static void closeAllOpenedBrowserWindows(WebDriver driver) {
        Logger.logStep("[Browser Action] Close all Opened Browser Windows");
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException rootCauseException) {
                Logger.logMessage(rootCauseException.getMessage());
            } finally {
                driver = null;
            }
        } else {
            Logger.logMessage("Windows are already closed and the driver object is null.");
        }
    }

    @Step("Get Page title")
    public static String getPageTitle(WebDriver driver) {
        Logger.logStep("[Browser Action] get Page Title: " + "[" + driver.getTitle() + "]");
        return driver.getTitle();
    }
    @Step("Get Current URL ")
    public static String getCurrentPageUrlTitle(WebDriver driver) {
        Logger.logStep("[Browser Action] get Current URL: " + "[" + driver.getCurrentUrl() + "]");
        return driver.getCurrentUrl();
    }


    @Step("Maximize the Browser Window")
    public static void maximizeWindow(WebDriver driver) {
        try {
            Logger.logStep("[Browser Action] Maximize the Browser Window");
            driver.manage().window().maximize();
        } catch (Exception e) {
            Logger.logMessage(e.getMessage());
        }
    }

    @Step("Minimize the Browser Window")
    public static void minimizeWindow(WebDriver driver) {
        try {
            Logger.logStep("[Browser Action] Maximize the Browser Window");
            driver.manage().window().minimize();
        } catch (Exception e) {
            Logger.logMessage(e.getMessage());
        }
    }


    public static void confirmAlert(WebDriver driver, ConfirmAlertType confirmAlerType) {
        Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        switch (confirmAlerType) {
            case ACCEPT:
                alert.accept();
                break;
            case DISMISS:
                Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
                alert.dismiss();
                break;
        }
    }


}
