package com.tasks.gui.pages;


import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utilities.actions.ElementActions;
import utilities.actions.Helper;
import utilities.browser.BrowserActions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Google_Page {
    // driver
    private static WebDriver driver;

    // Constructor
    public Google_Page(WebDriver driver) {
        Google_Page.driver = driver;
    }

    private final String googleUrl = Helper.getProperty("project.properties", "googleUrl");

    //////////////////////////// Elements Locators ////////////////////////////
//    public static By googleLogo_image = By.xpath("//img[@id='hplogo']");

    public static By googleLogo_image = By.xpath("//img[@alt='Google']");
    private static final By search_textBx = By.xpath("//input[@name='q']");

    private By inputSearchKeyWord(String searchQuery) {
        return By.xpath("//div/span[contains(text(),'" + searchQuery + "')]");
    }

    private By inputOrdinalNumber_SearchList(String index) {
        return By.xpath("//li[contains(@class,'sbct')][" + index + "]");
    }

    //////////////////////////// Business Actions ////////////////////////////

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
    public boolean isGoogleLogoDisplayed() {
        String imgPath = System.getProperty("user.dir") + "/src/test/resources/testData/google_testData/getImage/actualImage/" + "googleTest.png";
        Shutterbug.shootElement(driver, driver.findElement(googleLogo_image)).save(imgPath);
        return driver.findElement(googleLogo_image).isDisplayed();
    }

    public static void takeFullPage_screenShot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            String destination = System.getProperty("user.dir") + "/src/test/resources/TestsScreenshots/" + screenshotName + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);

        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    public static void takeWebElement_screenshot(String screenshotName) throws IOException {
        WebElement element = driver.findElement(googleLogo_image);
        File source = element.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "/src/test/resources/testData/google_testData/actualImage/" + screenshotName + ".png");
        FileHandler.copy(source, destination);
        File image = new File(System.getProperty("user.dir") + "/src/test/resources/testData/google_testData/expectedImage/expectedGoogleLogo.png");

        BufferedImage expectedImage = ImageIO.read(image);
        BufferedImage actualImage = ImageIO.read(destination);
        System.out.println("image " + " [ " + expectedImage + " ] ");
        System.out.println("actualImage" + " [ " + actualImage + " ] ");
        ImageDiffer imageDiffer = new ImageDiffer();

        ImageDiff diff = imageDiffer.makeDiff(expectedImage, actualImage);
        if (!diff.hasDiff()) {
            System.out.println("Images are same");
        } else {
            System.out.println("Images are different");
        }

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

    public SearchResults_Page searchByText(String searchKeyWord) {
        ElementActions.type(driver, search_textBx, searchKeyWord);
        ElementActions.clickKeyboardKey(driver, search_textBx, Keys.ENTER);
        return new SearchResults_Page(driver);
    }

    /**
     * Search By text and index in a search list
     *
     * @param searchKeyword *, @param index*
     * @return self reference
     */

    public SearchResults_Page searchByTextAndIndex_fromList(String searchKeyword, String index) {
        ElementActions.type(driver, search_textBx, searchKeyword);
        ElementActions.click(driver, inputOrdinalNumber_SearchList(index));
        return new SearchResults_Page(driver);
    }

}
