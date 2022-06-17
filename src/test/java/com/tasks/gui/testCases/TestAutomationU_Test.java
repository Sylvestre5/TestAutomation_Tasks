package com.tasks.gui.testCases;


import com.tasks.gui.pages.TestAutomationU_Page;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.actions.ElementActions;
import utilities.browser.BrowserActions;
import utilities.browser.BrowserFactory;

import java.awt.*;

import static com.tasks.gui.pages.TestAutomationU_Page.fileUploader_dragDrop;
import static com.tasks.gui.pages.TestAutomationU_Page.uploadedFiles_text;

public class TestAutomationU_Test {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp_BeforeMethods() {
        driver.set(BrowserFactory.getBrowser());
    }

    @AfterMethod()
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver.get());
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://the-internet.herokuapp.com/checkboxes")
    @TmsLink("Tc_006")
    @Issue("Bug_006")
    public void task_006_verifyCheckBoxes_checked() {
        int indexInCheckBoxes = 1;

        new TestAutomationU_Page(driver.get()).navigateTo_HomePage("checkboxes")
                .selectCheckBox(indexInCheckBoxes);
        Assert.assertTrue(driver.get().findElement(TestAutomationU_Page.isCheckBoxes).isSelected());
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://the-internet.herokuapp.com/upload")
    @TmsLink("Tc_006")
    @Issue("Bug_006")
    public void task_007_UploadedFile_inputField() throws InterruptedException {
        String imageName = "uploadPic.jpg";
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/Uploads/" + imageName;

        new TestAutomationU_Page(driver.get()).navigateTo_HomePage("upload");
        new TestAutomationU_Page(driver.get())
                .uploadFileBy_inputFile(imagePath)
                .clickUploadButton();

        Assert.assertEquals(imageName, TestAutomationU_Page.getUploadedFiles_text(), "The Validation Message is incorrect");
        Assert.assertTrue(TestAutomationU_Page.getUploadedFiles_text().contains(imageName));
        Assert.assertTrue((driver.get().findElement(uploadedFiles_text).isDisplayed()));
    }


    @Test
    public void task_007_UploadFile_WithRobot() throws InterruptedException, AWTException {
        String imageName = "uploadPic.jpg";
        String imagePath = "C:\\Users\\ismail\\Automation Projects\\Projects - Ismail_Elshafeiy\\Practice_TestAutomation_SeleniumWebDriver\\src\\test\\resources\\uploads\\" + imageName;

        new TestAutomationU_Page(driver.get()).navigateTo_HomePage("upload");
        new TestAutomationU_Page(driver.get()).clickUpload_dragDropArea()
                .uploadFileBy_robot(imagePath);
        Assert.assertTrue(driver.get().findElement(fileUploader_dragDrop).getText().contains(imageName));
        Assert.assertTrue((driver.get().findElement(fileUploader_dragDrop).isDisplayed()));
    }


    @Test
    public void task_009_DragAndDrop() {
        driver.get().get("https://jqueryui.com/resources/demos/droppable/default.html");
        By source = By.id("draggable");
        By target = By.id("droppable");

        ElementActions.dragAndDrop(driver.get(), source, target);

        /* Assert.assertEquals("Dropped!", driver.findElement(target).getText());*/

    }

}
