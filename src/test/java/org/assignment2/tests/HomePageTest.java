package org.assignment2.tests;

import org.assignment2.base.BaseTest;
import org.assignment2.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HomePageTest extends BaseTest {
    private HomePage homePage;
    private String pageTitle = "TecRoot | Best Game & Audio Store In Sri Lanka";

    public HomePageTest(){
        super();
    }

    /**
     * Sets up the HomePage object and loads the home page before each test.
     */
    @BeforeClass
    public void setUpPage() {
        homePage = new HomePage(driver);
        homePage.load();
    }

    @Test(priority=1)
    public void verifyHomePageTitleTest(){
        String homePageTitle = homePage.verifyHomePageTitle();
        System.out.println(homePageTitle);
        Assert.assertEquals(homePageTitle, this.pageTitle,"Home page title not matched");
    }
    @Test(priority = 2)
    public void allowNotificationPopUpTest() {
        homePage.clickAllowOnPopup();
        Assert.assertTrue(homePage.isNotificationPopupDisplayed(), "Notification dialog did not close.");
    }

    @Test(priority = 2)
    public void cancelNotificationPopUpTest() {
        homePage.clickCancelOnPopup();
        Assert.assertTrue(homePage.isNotificationPopupDisplayed(), "Notification dialog did not close.");
    }

    @Test(priority = 2, dependsOnMethods = {"allowNotificationPopUpTest"})
    public void openChatWindowAndRedirectToWhatsappTest() {
        homePage.clickChatButton();
        Assert.assertTrue(homePage.isChatWindowOpen(), "Chat window did not open.");

        homePage.clickInnerChatButton();

        // Switch to the new tab that opens (WhatsApp tab)
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://web.whatsapp.com"));

        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    @Test(priority = 3)
    public void openAccountPage()
    {

    }
}
