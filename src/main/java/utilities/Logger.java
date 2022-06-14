package utilities;

import io.qameta.allure.Step;
import utilities.actions.Helper;

public class Logger {

    @Step("{message}")
    public static void logStep(String message) {
	System.out.println("<" + Helper.getCurrentTime("dd-MM-yyyy HH:mm:ss.SSS a") + "> " + message);
    }
	@Step("{message}")
    public static void logMessage(String message) {
	System.out.println("<" + Helper.getCurrentTime("dd-MM-yyyy HH:mm:ss.SSS a") + "> " + message);
    }



}
