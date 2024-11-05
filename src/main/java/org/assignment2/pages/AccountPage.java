package org.assignment2.pages;

import org.assignment2.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.time.Duration;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Loads the account page.
     */
    public void load(){
        load("/my-account/");
    }
}
