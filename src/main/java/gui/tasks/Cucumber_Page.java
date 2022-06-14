package gui.tasks;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.browser.BrowserActions;

public class Cucumber_Page {

	// driver
	private static WebDriver driver;

	// Constructor
	public Cucumber_Page(WebDriver driver) {
		this.driver = driver;
	}

	//////////////////////////// Elements Locators ////////////////////////////
/*	public static By getSearchResults(String assertText, String index) {

		return By.xpath("//div[@class='tF2Cxc']/div[@class='yuRUbf']//h3)[" + index + "][contains(text(),'" + assertText
				+ "')]");
	}*/
	//////////////////////////// Business Actions ////////////////////////////

	/**
	 * Get Current Page URl
	 *
	 * @return self reference
	 */
	@Step("Get Current Page URL")
	public static String getCurrentPage_Url() {
		return BrowserActions.getCurrentPageUrlTitle(driver);
	}
}
