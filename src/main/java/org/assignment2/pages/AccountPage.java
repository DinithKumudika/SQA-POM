package org.assignment2.pages;

import org.assignment2.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage {

    private String pageTitle = "My Account | TecRoot";

    @FindBy(id = "username")
    private WebElement usernameLogin;
    @FindBy(id = "password")
    private WebElement passwordLogin;
    @FindBy(className = "woocommerce-form-login__submit")
    private WebElement loginBtn;
    @FindBy(className = "woocommerce-MyAccount-content")
    private WebElement accountLoginContent;
    @FindBy(xpath = "//ul[@class='woocommerce-error' and @role='alert']")
    private WebElement loginErrorMsg;


    public AccountPage(WebDriver driver)
    {
        super(driver);
        load();
    }

    /**
     * Loads the account page.
     */
    public void load(){
        load("my-account/");
    }

    public String getPageTitle(){
        return  this.pageTitle;
    }

    /**
     * verify the title of account page
     * @return the title
     */
    public String verifyAccountPageTitle(){
        return this.driver.getTitle();
    }

    public void setUsernameLogin(String username){
        usernameLogin.sendKeys(username);
    }

    public void setPasswordLogin(String password){
        passwordLogin.sendKeys(password);
    }

    public void clickOnLoginBtn(){
        loginBtn.click();
    }

    public boolean isUsernameDisplayed(String expectedUsername) {
        // Locate the strong tag containing the username
        String xpath = "//div[@class='woocommerce-MyAccount-content']//strong[text()='" + expectedUsername + "']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Check if the element exists with the expected username
        try {
            WebElement usernameElement = driver.findElement(By.xpath(xpath));
            return usernameElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try{
            return loginErrorMsg.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
