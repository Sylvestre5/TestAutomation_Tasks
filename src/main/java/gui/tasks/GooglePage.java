package gui.tasks;


import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import utilities.BrowserActions;
import utilities.ElementActions;
import utilities.Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GooglePage {
    // driver
    private static WebDriver driver;

    // Constructor
    public GooglePage(WebDriver driver) {
        GooglePage.driver = driver;
    }

    private final String googleUrl = Helper.getProperty("project.properties", "googleUrl");

    //////////////////////////// Elements Locators ////////////////////////////
//    public static By googleLogo_image = By.xpath("//img[@id='hplogo']");

    public static By googleLogo_image = By.xpath("//img[@alt='Google']");
    private static By search_textBx = By.xpath("//input[@name='q']");

    private By inputSearchKeyWord(String searchQuery) {
        return By.xpath("//div/span[contains(text(),'" + searchQuery + "')]");
    }

    private By inputOrdinalNumber_SearchList(int index) {
        return By.xpath("//li[contains(@class,'sbct')][" + index + "]");
    }

    //////////////////////////// Business Actions ////////////////////////////

    /**
     * Navigate to Home Page
     *
     * @return self reference
     */
    @Step("Navigate to Home Page")
    public GooglePage navigateTo_HomePage() {
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
    public boolean isGoogleLogoDisplayed() {
        String imgPath =System.getProperty("user.dir")+"/src/test/resources/testData/google_testData/getImage/actualImage/"+"googleTest.png";
        Shutterbug.shootElement(driver, driver.findElement(googleLogo_image)).save(imgPath);
        return driver.findElement(googleLogo_image).isDisplayed();
    }

    public static void getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/src/test/resources/TestsScreenshots/" + screenshotName
               + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

    }

/*    public void GooglePage takeScreenShootGooglePage() {
        String imgPath = "src/test/resources/testData/google_testData/getImage/actualImage";
        File tes = new File(Shutterbug.shootElement(driver, driver.findElement(googleLogo_image)).save(imgPath));
        Files.move(imgPath, new File("resources/screenshots/test.png"));
        Shutterbug.shootElement(driver, driver.findElement(googleLogo_image)).save(imgPath);


    }*/
//
//    public GooglePage takeScreenShootElementLogo() {
//        Shutterbug.shootElement(driver, driver.findElement(googleLogo_image)).save("src/test/resources/testData/google_testData/getImage");
//        return this;
//
//    }


    /**
     * search By text
     *
     * @param searchKeyWord *
     * @return self reference
     */

    public SearchResultsPage searchByText(String searchKeyWord) {
        ElementActions.type(driver, search_textBx, searchKeyWord);
        ElementActions.clickKeyboardKey(driver, search_textBx, Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    /**
     * Search By text and index in a search list
     *
     * @param searchKeyword *, @param index*
     * @return self reference
     */

    public SearchResultsPage searchByTextAndIndex_fromList(String searchKeyword, int index) {
        ElementActions.type(driver, search_textBx, searchKeyword);
        ElementActions.click(driver, inputOrdinalNumber_SearchList(index));
        return new SearchResultsPage(driver);
    }

}
