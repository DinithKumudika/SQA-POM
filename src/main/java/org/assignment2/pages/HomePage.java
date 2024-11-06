package org.assignment2.pages;

import org.assignment2.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage {

    // Locators for the popup elements
    @FindBy(id = "onesignal-slidedown-allow-button")
    private WebElement allowButton;

    @FindBy(id = "onesignal-slidedown-cancel-button")
    private WebElement cancelButton;
    @FindBy(id = "onesignal-slidedown-container")
    private WebElement popupContainer;
    @FindBy(className = "joinchat__button__open")
    private WebElement chatButton;
    @FindBy(className = "joinchat__button")
    private WebElement innerChatButton;
    @FindBy(className = "joinchat__box")
    private WebElement chatBox;
    @FindBy(xpath = "//input[@id='mce-EMAIL']")
    private WebElement newsletterEmail;
    @FindBy(xpath = "//input[@id='mc-embedded-subscribe']")
    private WebElement newsletterSignUpBtn;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Loads the home page.
     */
    public void load(){
        load("/");
    }


    /**
     * verify the title of home page
     * @return the title
     */
    public String verifyHomePageTitle(){
        return this.driver.getTitle();
    }

    /**
     * Checks if the notification popup is displayed.
     * @return true if the popup is visible, otherwise false.
     */
    public boolean isNotificationPopupDisplayed() {
        return this.popupContainer.isDisplayed();
    }

    /**
     * Clicks on the 'Allow' button in the notification popup.
     */
    public void clickAllowOnPopup() {
        if (isNotificationPopupDisplayed()) {
            this.allowButton.click();
        }
    }

    /**
     * Clicks on the 'Cancel' button in the notification popup.
     */
    public void clickCancelOnPopup() {
        if (isNotificationPopupDisplayed()) {
            this.cancelButton.click();
        }
    }

    /**
     * Click on the chat button to open the chat window.
     */
    public void clickChatButton() {
        this.chatButton.click();
    }

    /**
     * Click on the open chat button in the chat window.
     */
    public void clickInnerChatButton() {
        waitForClickability(innerChatButton);
        this.innerChatButton.click();
    }

    /**
     * Verify if the chat window is opened.
     */
    public boolean isChatWindowOpen() {
        waitForVisibility(chatBox);
        return chatBox.isDisplayed();
    }

    /**
     * enter email for newsletter subscription.
     */
    public void enterEmailForNewsletter(String email) {
        newsletterEmail.sendKeys(email);
    }

    /**
     * click the SignUp button for newsletter subscription.
     */
    public void clickSignUpNewsletterButton() {
        newsletterSignUpBtn.click();
    }
}
