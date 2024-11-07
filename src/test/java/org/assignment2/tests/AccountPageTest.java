package org.assignment2.tests;

import org.assignment2.base.BaseTest;
import org.assignment2.dataProviders.TestDataProvider;
import org.assignment2.pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountPageTest extends BaseTest {
    private AccountPage accountPage;
    public AccountPageTest(){
        super();
    }

    /**
     * Sets up the HomePage object and loads the home page before each test.
     */
    @BeforeClass
    public void setUpPage() {
        accountPage = new AccountPage(driver);
        accountPage.load();
    }

    @Test(priority=1)
    public void verifyAccountPageTitleTest(){
        String accountPageTitle = accountPage.verifyAccountPageTitle();
        System.out.println(accountPageTitle);
//        Assert.assertEquals(homePageTitle, this.pageTitle,"Home page title not matched");
    }

    @Test(dataProvider = "getLoginCredentials", dataProviderClass = TestDataProvider.class, priority=2, dependsOnMethods = {"verifyAccountPageTitleTest"})
    public void loginTest(String testCaseId, String username, String password, String expectedResult) {
        // Implement login logic here
        System.out.println("Running test case ID: " + testCaseId);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Expected Result: " + expectedResult);

        accountPage.setUsernameLogin(username);
        accountPage.setPasswordLogin(password);
        accountPage.clickOnLoginBtn();

        boolean usernameDisplayed = accountPage.isUsernameDisplayed(username);
        boolean errorDisplayed = accountPage.isErrorDisplayed();

        // Assert based on the expected result
        if(expectedResult.equalsIgnoreCase("pass")){
            Assert.assertTrue(usernameDisplayed,"user login failed, invalid username or password");
        }
        else {
            Assert.assertTrue(errorDisplayed);
            accountPage.clearUsernameLogin();
            accountPage.clearPasswordLogin();
        }
    }
}
