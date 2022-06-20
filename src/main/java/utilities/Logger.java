package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.actions.Helper;

public class Logger {

    @Step("{message}")
    public static void logStep(String message) {
        System.out.println("<" + Helper.getCurrentTime("dd/MM/yyyy HH:mm:ss") + ">  " + message);
        ExtentReport.info(message);
    }

    @Step("{message}")
    public static void logMessage(String message) {
        System.out.println("<" + Helper.getCurrentTime("dd/MM/yyyy HH:mm:ss") + ">  " + message);
        ExtentReport.info(message);
    }

    @Attachment
    public static byte[] attachScreenshotToAllureReport(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public static Media attachScreenshotToExtentReport(WebDriver driver) {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64), "Screenshot").build();
    }
}
