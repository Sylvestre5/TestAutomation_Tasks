package testCases;

import gui.tasks.FileUploadPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.BrowserActions;
import utilities.BrowserFactory;

import java.awt.*;

import static gui.tasks.FileUploadPage.fileUploader_dragDrop;
import static gui.tasks.FileUploadPage.uploadedFiles_text;

public class UploadFiles_Test {

    private WebDriver driver;


    @BeforeMethod
    public void setUp_BeforeMethod() {
        driver = BrowserFactory.getBrowser();
    }

    @AfterMethod(enabled = false)
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);
    }

    @Test
    public void setUploadedFile_inputField() throws InterruptedException {
        String imageName = "uploadPic.jpg";
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/Uploads/" + imageName;
        driver.get("https://the-internet.herokuapp.com/upload");
        new FileUploadPage(driver)
                .uploadFileBy_inputFile(imagePath)
                .clickUploadButton();


        Assert.assertEquals(imageName, FileUploadPage.getUploadedFiles_text(), "The Validation Message is incorrect");
        Assert.assertTrue(FileUploadPage.getUploadedFiles_text().contains(imageName));
        Assert.assertTrue((driver.findElement(uploadedFiles_text).isDisplayed()));
    }


    @Test
    public void FileUploadWithRobot() throws InterruptedException, AWTException {
        String imageName = "uploadPic.jpg";
        String imagePath = "C:\\Users\\ismail\\Automation Projects\\Projects - Ismail_Elshafeiy\\Practice_TestAutomation_SeleniumWebDriver\\src\\test\\resources\\Uploads\\" + imageName;
        driver.get("https://the-internet.herokuapp.com/upload");
        new FileUploadPage(driver).clickUpload_dragDropArea()
                .uploadFileBy_robot(imagePath);
        Assert.assertTrue(driver.findElement(fileUploader_dragDrop).getText().contains(imageName));
        Assert.assertTrue((driver.findElement(fileUploader_dragDrop).isDisplayed()));
    }


    @BeforeTest
    public void setup() {

        driver.get("http://the-internet.herokuapp.com/download");
        driver.manage().window().maximize();
    }


}













