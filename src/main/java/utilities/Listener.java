package utilities;

import org.testng.*;

public class Listener implements ISuiteListener, ITestListener, IInvokedMethodListener {
	///////////////// ISuiteListener //////////////////
	@Override
	public void onStart(ISuite suite) {
		System.out.println("\n" + "**********************************************");
		System.out.println("Starting Test Suite; By Ismail Elshafeiy ");
		System.out.println("**********************************************" + "\n");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("\n" + "**********************************************");
		System.out.println("Finished Test Suite; By Ismail Elshafeiy ");
		System.out.println("**********************************************" + "\n");

	}

	///////////////// ITestListener //////////////////

	@Override
	public void onStart(ITestContext context) {
		System.out.println("\n" + "**************************************************** " + "Test: ["
				+ context.getName() + "] Started" + " ****************************************************" + "\n");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("\n" + "**************************************************** " + "Test: ["
				+ context.getName() + "] Finished" + " ****************************************************" + "\n");
	}

	///////////////// IInvokedMethodListener //////////////////

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod testMethod = method.getTestMethod();
		if (testMethod.getDescription() != null && !testMethod.getDescription().equals("")) {

			System.out.println("\n"
					+ "============================================================================================");
		} else if (method.isConfigurationMethod()) {
			System.out.println("Starting Configuration Method (Setup or Teardown): [" + testResult.getName() + "]");
			if (testMethod.getDescription() != null && !testMethod.getDescription().equals("")) {
				System.out.println("Starting Test Case: [" + testResult.getName() + "]");
			}
			System.out.println(
					"============================================================================================"
							+ "\n");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println(
				"\n" + "===========================================================================================");
		if (method.isConfigurationMethod()) {
			System.out.println("Finished Configuration Method (Setup or Teardown): [" + testResult.getName() + "]");
		} else {
			System.out.println("Finished Test Case: [" + testResult.getName() + "]");
		}
		System.out.println(
				"===========================================================================================" + "\n");
	}

}
