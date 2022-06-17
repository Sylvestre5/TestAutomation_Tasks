package utilities.actions;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.Logger;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

public class ElementActions {
    private final WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Click on element: [{elementLocator}]")
    public static void click(WebDriver driver, By elementLocator) {
        // Mouse hover on the element before clicking
        mouseHover(driver, elementLocator);
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            Logger.logStep(toe.getMessage());
            fail(toe.getMessage());
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        try {
            // Log element text if not empty. Else, log clicking
            if (!driver.findElement(elementLocator).getText().isBlank()) {
                Logger.logStep(
                        "[Element Action] Click on [" + driver.findElement(elementLocator).getText() + "] Button");
            } else {
                Logger.logStep("[Element Action] Click on element [" + elementLocator + "]");
            }
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                        driver.findElement(elementLocator));
            } catch (Exception rootCauseException) {
                rootCauseException.initCause(exception);
                Logger.logStep(exception.getMessage());
                Logger.logStep(rootCauseException.getMessage());
                fail("Couldn't click on the element:" + elementLocator, rootCauseException);
            }
        }
    }

    public ElementActions click(By elementLocator) {
        click(driver, elementLocator);
        return this;
    }

    public static void clickKeyboardKey(WebDriver driver, By elementLocator, Keys key) {
        Helper.locatingElementStrategy(driver, elementLocator);
        try {
            Logger.logStep(
                    "[Element Action] Click a Keyboard key [" + key.name() + "] on element [" + elementLocator + "]");
            driver.findElement(elementLocator).sendKeys(key);
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
        }
    }

    public ElementActions clickKeyboardKey(By elementLocator, Keys key) {
        clickKeyboardKey(driver, elementLocator, key);
        return this;
    }

    public static void doubleClick(WebDriver driver, By elementLocator) {
        Helper.locatingElementStrategy(driver, elementLocator);
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(driver.findElement(elementLocator)).perform();
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions doubleClick(By elementLocator) {
        doubleClick(driver, elementLocator);
        return this;
    }

    // @Step("Type data: [{data}] on element: [{elementLocator}]")
    public static void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        Helper.locatingElementStrategy(driver, elementLocator);
        try {
            if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
                Logger.logStep("[Element Action] Clear and Type [" + text + "] on element [" + elementLocator + "]");
                driver.findElement(elementLocator).clear();
                driver.findElement(elementLocator).sendKeys(text);

                if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
                            driver.findElement(elementLocator));
                }
            } else {
                Logger.logStep("[Element Action] Type [" + text + "] on element [" + elementLocator + "]");
                driver.findElement(elementLocator).sendKeys(text);

                if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
                    String currentValue = driver.findElement(elementLocator).getAttribute("value");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].setAttribute('value', '" + currentValue + text + "')",
                            driver.findElement(elementLocator));
                }
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        Assert.assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
                "The data is not inserted successfully to the field, the inserted data should be: [" + text
                        + "]; But the current field value is: ["
                        + driver.findElement(elementLocator).getAttribute("value") + "]");
    }

    public ElementActions type(By elementLocator, String text, boolean clearBeforeTyping) {
        type(driver, elementLocator, text, clearBeforeTyping);
        return this;
    }

    public static void type(WebDriver driver, By elementLocator, String text) {
        type(driver, elementLocator, text, true);
    }

    public ElementActions type(By elementLocator, String text) {
        type(driver, elementLocator, text, true);
        return this;
    }

    public enum SelectType {
        TEXT, VALUE, INDEX
    }

    public static void select(WebDriver driver, By elementLocator, SelectType selectType, String option) {
        Helper.locatingElementStrategy(driver, elementLocator);
        try {
            Select select = new Select(driver.findElement(elementLocator));
            Logger.logStep("[Element Action] Select [" + option + "] on element [" + elementLocator + "]");
            assertFalse(select.isMultiple());

            switch (selectType) {
                case TEXT -> select.selectByVisibleText(option);
                case VALUE -> select.selectByValue(option);
                case INDEX -> select.selectByIndex(Integer.parseInt(option));
                default -> Logger.logMessage("Unexpected value: " + selectType);
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions select(By elementLocator, SelectType selectType, String option) {
        select(driver, elementLocator, selectType, option);
        return this;
    }

    public ElementActions select(By elementLocator, SelectType selectType, int option) {
        select(driver, elementLocator, selectType, String.valueOf(option));
        return this;
    }

    public static void dragAndDrop(WebDriver driver, By sourceLocator, By targetLocator) {

        Helper.locatingElementStrategy(driver, sourceLocator);
        Helper.locatingElementStrategy(driver, targetLocator);
        try {
            Actions actions = new Actions(driver);
            WebElement source = driver.findElement(sourceLocator);
            WebElement target = driver.findElement(targetLocator);
            actions.dragAndDrop(source, target);
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions dragAndDrop(By sourceLocator, By targetLocator) {
        dragAndDrop(driver, sourceLocator, targetLocator);
        return this;
    }


    public static String getText(WebDriver driver, By elementLocator) {
        Helper.locatingElementStrategy(driver, elementLocator);
        try {
            String text = driver.findElement(elementLocator).getText();
            Logger.logStep(
                    "[Element Action] Get the Text of element [" + elementLocator + "]; The Text is [" + text + "]");
            return text;
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
        }
        return null;
    }

    public static void mouseHover(WebDriver driver, By elementLocator) {
        Helper.locatingElementStrategy(driver, elementLocator);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(elementLocator)).perform();
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

    public ElementActions mouseHover(By elementLocator) {
        mouseHover(driver, elementLocator);
        return this;
    }
}
