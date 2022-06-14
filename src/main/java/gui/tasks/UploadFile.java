package gui.tasks;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.BrowserActions;
import utilities.Helper;

public class UploadFile {

    private static WebDriver driver;
    private final String w3schoolUrl = Helper.getProperty("project.properties", "herokUrl2");

    // Constructor
    public UploadFile(WebDriver driver) {
        this.driver = driver;
    }

    //////////////////////////// Elements Locators ////////////////////////////

    //////////////////////////// Business Actions ////////////////////////////

    /**
     * Navigate to Home Page
     *
     * @return self reference
     */
    @Step("Navigate to Home Page")
    public UploadFile navigateTo_HomePage() {
        BrowserActions.navigateToUrl(driver, w3schoolUrl);
        return this;
    }


}
