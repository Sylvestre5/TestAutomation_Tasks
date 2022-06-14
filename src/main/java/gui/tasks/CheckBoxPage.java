package gui.tasks;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserActions;
import utilities.ElementActions;
import utilities.Helper;

public class CheckBoxPage {
    // driver
    private static WebDriver driver;
    private final String herokUrl = Helper.getProperty("project.properties", "herokUrl");

    // Constructor
    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    //////////////////////////// Elements Locators ////////////////////////////
    private By checkBoxes(int index) {
        return By.xpath("//input[@type='checkbox'][" + index + "]");
    }

    public static By isCheckBoxes = By.xpath("//form[@id='checkboxes']/input");
    public  By checkBoxesText = By.id("checkboxes");

    //////////////////////////// Business Actions ////////////////////////////

    /**
     * Navigate to Home Page
     *
     * @return self reference
     */
    @Step("Navigate to Home Page")
    public CheckBoxPage navigateTo_HomePage() {
        BrowserActions.navigateToUrl(driver, herokUrl);
        return this;
    }


    public CheckBoxPage selectCheckBox(int index) {
        ElementActions.click(driver, checkBoxes(index));
        ElementActions.getText(driver, checkBoxesText);
        return this;
    }


}
