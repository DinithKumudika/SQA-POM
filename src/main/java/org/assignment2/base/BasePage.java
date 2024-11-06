package org.assignment2.base;
import org.assignment2.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final String baseUrl;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.baseUrl = ConfigLoader.getInstance().getBaseUrl();
        PageFactory.initElements(driver, this);
    }

    /**
     * get baseUrl of the SUT.
     * @return base url.
     */
    public String getBaseUrl()
    {
        return this.baseUrl;
    }

    /**
     * Loads the full URL by appending the endpoint to the base URL.
     * @param endPoint The endpoint to append to the base URL.
     */
    public void load(String endPoint){
        driver.get(this.baseUrl + endPoint);
    }

    /**
     * Waits for the specified WebElement to be visible.
     * @param element The WebElement to wait for.
     * @return The visible WebElement.
     */
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for a WebElement to be clickable.
     * @param element The WebElement to wait for.
     * @return The clickable WebElement.
     */
    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
