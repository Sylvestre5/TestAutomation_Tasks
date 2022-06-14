package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static org.testng.Assert.fail;

public class Helper {
    private static FileReader reader = null;
    private static final String  propertiesRoot = "src/main/resources/";
    private static Properties properties = new Properties();

    /**
     * Get Properties
     *
     * @param propertyFileName*
     * @param propertyName*
     * @return self reference
     */
    public static String getProperty(String propertyFileName, String propertyName) {
        String propertiesPath = propertiesRoot + propertyFileName;

        try {
            reader = new FileReader(propertiesPath);
        } catch (FileNotFoundException e) {
            Logger.logMessage("No file found in the given path: " + propertiesPath);
            e.printStackTrace();
        }

        try {
            properties.load(reader);
        } catch (IOException e) {
            Logger.logMessage("Couldn't find any properties with the given property name: " + propertyName);
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }

    private static final int TIMEOUT = Integer.parseInt(Helper.getProperty("project.properties", "webdriver.wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }

    /**
     * Handle the elements by wait, isVisible, Scroll, isDisplayed, print and catch
     * exception
     *
     * @param driver*
	 * @param elementLocator*
     */
    protected static void locatingElementStrategy(WebDriver driver, By elementLocator) {
        try {
            // Wait for the element to be visible
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            // Scroll the element into view to handle some browsers cases
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
                    driver.findElement(elementLocator));

            if (!driver.findElement(elementLocator).isDisplayed()) {
                Logger.logStep("The element [" + elementLocator.toString() + "] is not Displayed");
                fail("The element [" + elementLocator.toString() + "] is not Displayed");
            }
        } catch (TimeoutException toe) {
            Logger.logStep(toe.getMessage());
            fail(toe.getMessage());
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public static String getCurrentTime(String dateFormat) {
        String currentTime = "";
        try {
            currentTime = new SimpleDateFormat(dateFormat).format(new Date());
        } catch (IllegalArgumentException e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("ddMMyyyyHHmmssSSS");
    }

}
