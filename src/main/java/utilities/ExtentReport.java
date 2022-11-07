package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void initReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport/ExtentReports.html");
        extentReports.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Extent Report");
        spark.config().setReportName("Test Automation-Extent Report");

    }

    public static void createTest(String testCaseName) {
        extentTest = extentReports.createTest(testCaseName);
    }

    public static void removeTest(String testCaseName) {
        extentReports.removeTest(testCaseName);
    }

    public static void info(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }

    public static void info(Markup m) {
        extentTest.info(m);
    }


    public static void pass(String message) {
        extentTest.pass(message);
    }

    public static void pass(Markup m) {
        extentTest.pass(m);
    }


    public static void fail(String message) {
        extentTest.fail(message);
    }

    public static void fail(Markup m) {
        extentTest.fail(m);
    }

    public static void fail(Throwable t) {
        extentTest.fail(t);
    }

    public static void fail(Media media) {
        extentTest.fail(media);
    }


    public static void flushReports() {
        extentReports.flush();
    }

}
