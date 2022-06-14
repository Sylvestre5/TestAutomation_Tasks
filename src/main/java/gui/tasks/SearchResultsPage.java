package gui.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.actions.ElementActions;

public class SearchResultsPage {
	// driver
	private static WebDriver driver;

	// Constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	//////////////////////////// Elements Locators ////////////////////////////
	public static By getSearchResultsNumber(String index) {
		return By.xpath("(//div[@class='NJo7tc Z26q7c jGGQ5e']/div[@class='yuRUbf']//h3)[" + index + "]");
	}
/*
	public static By getSearchResultsNumber(String index) {
		return By.xpath("(//div[@class='v7W49e']//div[contains(@class,'g')]//h3)[" + index + "]");
	}

	public static By getSearchResultsNumber(String index) {
		return By.xpath("//div[@class='g tF2Cxc']//div[@class='yuRUbf']//h3[" + index + "]");
	}
*/



	//////////////////////////// Business Actions ////////////////////////////

	/**
	 * get text By index
	 * 
	 * @param index*
	 * @return self reference
	 */

	public static String getTextSearchResults(String index) {
		return ElementActions.getText(driver, getSearchResultsNumber(index));
	}

	/**
	 * Navigate to Page index
	 * 
	 * @param index*
	 * @return self reference
	 */
	public Cucumber_Page navigateTo_cucumberSearchResult(String index) {
		ElementActions.click(driver, getSearchResultsNumber(index));
		return new Cucumber_Page(driver);
	}

}
