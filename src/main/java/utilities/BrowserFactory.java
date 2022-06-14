package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import static org.testng.Assert.fail;

public class BrowserFactory {
    //    private static WebDriver driver;
    private static WebDriver driver;
    private static String propertiesFileName = "project.properties";
    private static String browserTypeProperty = Helper.getProperty(propertiesFileName, "browser.type");
    private static String executionTypeProperty = Helper.getProperty(propertiesFileName, "execution.type");

    public enum BrowserType {
        MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome"), FROM_PROPERTIES(browserTypeProperty);

        private String value;

        BrowserType(String type) {
            this.value = type;
        }

        protected String getValue() {
            return value;
        }
    }

    public enum ExecutionType {
        LOCAL("Local"), LOCAL_HEADLESS("Local Headless"), FROM_PROPERTIES(executionTypeProperty);

        private String value;

        ExecutionType(String type) {
            this.value = type;
        }

        protected String getValue() {
            return value;
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chOptions = new ChromeOptions();
        chOptions.setHeadless(true);
        chOptions.addArguments("--window-size=1920,1080");
        return chOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setHeadless(true);
        ffOptions.addArguments("--window-size=1920,1080");
        return ffOptions;
    }

    // Check the Browser and Execution from properties file
    @Step("Initializing a new Web GUI Browser!.....")
    public static synchronized WebDriver getBrowser(BrowserType browserType, ExecutionType executionType) {
        ITestResult result = Reporter.getCurrentTestResult();
        ITestContext context = result.getTestContext();
        Logger.logStep("Initialize [" + browserType.getValue() + "] Browser and the Execution Type is [" + executionType.getValue() + "]");

        // Run  Local execution
        if (executionType == ExecutionType.LOCAL
                || (executionType == ExecutionType.FROM_PROPERTIES && executionTypeProperty.equalsIgnoreCase("local"))) {

            if (browserType == BrowserType.GOOGLE_CHROME
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                context.setAttribute("driver", driver);
                Helper.implicitWait(driver);
                BrowserActions.maximizeWindow(driver);

            } else if (browserType == BrowserType.MOZILLA_FIREFOX
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                context.setAttribute("driver", driver);
                Helper.implicitWait(driver);
                BrowserActions.maximizeWindow(driver);

            } else {
                String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty + "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
                Logger.logMessage(warningMsg);
                fail(warningMsg);
//		throw new NullPointerException(warningMsg);
            }
        }
        // Run Local Headless execution
        else if (executionType == ExecutionType.LOCAL_HEADLESS
                || (executionType == ExecutionType.FROM_PROPERTIES && executionTypeProperty.equalsIgnoreCase("local headless"))) {
            if (browserType == BrowserType.GOOGLE_CHROME
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                context.setAttribute("driver", driver);
                Helper.implicitWait(driver);

            } else if (browserType == BrowserType.MOZILLA_FIREFOX
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                context.setAttribute("driver", driver);
                Helper.implicitWait(driver);

            } else {
                String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty + "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
                Logger.logMessage(warningMsg);
                fail(warningMsg);
//		throw new NullPointerException(warningMsg);
            }
        } else {
            String warningMsg = "The driver is null! because the execution type [" + executionTypeProperty + "] is not valid/supported; Please choose a valid execution type from the given choices in the properties file";
            Logger.logMessage(warningMsg);
            fail(warningMsg);
//	    throw new NullPointerException(warningMsg);
        }
        return driver;
    }

    public static WebDriver getBrowser() {
        return getBrowser(BrowserType.FROM_PROPERTIES, ExecutionType.FROM_PROPERTIES);
    }

}
