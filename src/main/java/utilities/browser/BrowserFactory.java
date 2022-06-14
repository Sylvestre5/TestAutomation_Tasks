package utilities.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.actions.Helper;
import utilities.Logger;

import static org.testng.Assert.fail;

public class BrowserFactory {
    private static WebDriver driver;
    private static final String propertiesFileName = "project.properties";
    private static final String browserTypeProperty = Helper.getProperty(propertiesFileName, "browser.type");
    private static final String executionTypeProperty = Helper.getProperty(propertiesFileName, "execution.type");

    public enum BrowserType {

        MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome"), FROM_PROPERTIES(browserTypeProperty);

        private final String value;

        BrowserType(String type) {
            this.value = type;
        }

        private String getValue() {
            return value;
        }
    }

    public enum ExecutionType {
        LOCAL("Local"), FROM_PROPERTIES(executionTypeProperty);

        private final String value;

        ExecutionType(String type) {
            this.value = type;
        }

        private String getValue() {
            return value;
        }
    }


    /**
     * Check the Browser and Execution from properties file
     *
     * @return BrowserType , ExecutionType
     */
    public static WebDriver getBrowser() {
        return getBrowser(BrowserType.FROM_PROPERTIES, ExecutionType.FROM_PROPERTIES);
    }

    @Step("Initializing a new Web Browser .....")
    public static WebDriver getBrowser(BrowserType browserType, ExecutionType executionType) {
        Logger.logStep("Initialize [" + browserType.getValue() + "] Browser and the Execution Type is ["
                + executionType.getValue() + "]");

        // Run Local execution
        if (executionType == ExecutionType.LOCAL || (executionType == ExecutionType.FROM_PROPERTIES
                && executionTypeProperty.equalsIgnoreCase("local"))) {

            if (browserType == BrowserType.GOOGLE_CHROME || (browserType == BrowserType.FROM_PROPERTIES
                    && browserTypeProperty.equalsIgnoreCase("google chrome"))) {

//                System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                Helper.implicitWait(driver);
                BrowserActions.maximizeWindow(driver);

            } else if (browserType == BrowserType.MOZILLA_FIREFOX || (browserType == BrowserType.FROM_PROPERTIES
                    && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                Helper.implicitWait(driver);
                BrowserActions.maximizeWindow(driver);

            } else {
                String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
                        + "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
                Logger.logMessage(warningMsg);
                fail(warningMsg);
            }

        }
        return driver;
    }



    /*
     * private static ChromeOptions getChromeOptions() { ChromeOptions chOptions =
     * new ChromeOptions(); chOptions.setHeadless(true);
     * chOptions.addArguments("--window-size=1920,1080"); return chOptions; }
     *
     * private static FirefoxOptions getFirefoxOptions() { FirefoxOptions ffOptions
     * = new FirefoxOptions(); ffOptions.setHeadless(true);
     * ffOptions.addArguments("--window-size=1920,1080"); return ffOptions; }
     */
}
