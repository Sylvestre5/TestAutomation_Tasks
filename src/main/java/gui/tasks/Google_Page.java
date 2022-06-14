package gui.tasks;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserActions;
import utilities.ElementActions;
import utilities.Helper;

public class Google_Page {
    // driver
    private static WebDriver driver;

    // Constructor
    public Google_Page(WebDriver driver) {
        Google_Page.driver = driver;
    }

    private final String googleUrl = Helper.getProperty("project.properties", "googleUrl");

    ////////////////////////////  Elements Locators ////////////////////////////
    private static By googleLogo_image = By.xpath("//img[@id='hplogo']");
    private static By search_textBx =By.xpath("//input[@name='q']");
    private By inputSearchKeyWord(String searchQuery) {
        return By.xpath("//div/span[contains(text(),'"+ searchQuery +"')]") ;
    }

    private By inputOrdinalNumber_SearchList(int index) {
        return By.xpath("//li[contains(@class,'sbct')][" + index + "]");
    }

    ////////////////////////////  Business Actions ////////////////////////////

    /**
     * Navigate to Home Page
     *
     * @return self reference
     */
    @Step("Navigate to Home Page")
    public Google_Page navigateTo_HomePage() {
        BrowserActions.navigateToUrl(driver, googleUrl);
        return this;
    }

    /**
     * Get title Page
     *
     * @return self reference
     */
    @Step("Get Title Page")
    public static String getTitle_Page() {
        return BrowserActions.getPageTitle(driver);
    }

    /**
     * Get Current Page URl
     *
     * @return self reference
     */
    @Step("Get Current Page URL")
    public static String getCurrentPage_Url() {
        return BrowserActions.getCurrentPageUrlTitle(driver);
    }

    /**
     * check logo is displayed ?
     *
     * @return self reference
     */
    @Step("Check logo is displayed")
    public static boolean isGoogleLogoDisplayed() {
        return driver.findElement(googleLogo_image).isDisplayed();
    }

    @Step("Open Search Results Page --> [{searchKeyWord}] from List of Search")
    public SearchResults_Page openSearchResultsByText_Page(String searchKeyWord) {
        ElementActions.type(driver,search_textBx ,searchKeyWord);
        ElementActions.click(driver, inputSearchKeyWord(searchKeyWord));
        return new  SearchResults_Page(driver);
    }

    @Step("Open Search Results Page --> [{searchKeyWord}] from List of Search")
    public SearchResults_Page openSearchResultsByIndex_Page(String searchKeyword, int index) {
        ElementActions.type(driver,search_textBx ,searchKeyword);
        ElementActions.click(driver, inputOrdinalNumber_SearchList(index));
        return new  SearchResults_Page(driver);
    }

}
