package utilities.browser;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.actions.Helper;
import utilities.Logger;

import static org.testng.Assert.fail;

public class BrowserActions {
    private static WebDriver driver;

    public enum ConfirmAlertType {
        ACCEPT, DISMISS
    }

    @Step("Navigate to URL: [{url}]")
    public static void navigateToUrl(WebDriver driver, String url) {
        try {
            Logger.logStep("[Browser Action] Navigate to URL [" + url + "]");
            driver.get(url);
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
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

    @Step("Confirm Alert: [{confirmAlertType}]")
    public static void confirmAlert(WebDriver driver, ConfirmAlertType confirmAlerType) {
        Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        switch (confirmAlerType) {
            case ACCEPT -> alert.accept();
            case DISMISS -> {
                Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
                alert.dismiss();
            }
        }
    }

    @Step("Close Browser Windows.....")
    public static void closeAllOpenedBrowserWindows(WebDriver driver) {
        Logger.logStep("[Browser Action] Close Browser Windows");
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException rootCauseException) {
                Logger.logMessage(rootCauseException.getMessage());
            }
        } else {
            Logger.logMessage("Windows are already closed and the driver object is null.");
        }
    }

}
