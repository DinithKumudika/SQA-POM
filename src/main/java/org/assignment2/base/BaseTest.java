package org.assignment2.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assignment2.utils.BrowserFactory;
import org.assignment2.utils.ConfigLoader;
import org.assignment2.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    public static Properties properties;
    protected BrowserFactory browserFactory;

    public BaseTest(){
        properties = ConfigLoader.getInstance().getProperties();
    }

    /**
     * Sets up the WebDriver before each test method.
     * applies default configurations and navigates to the base URL specified in the properties file.
     */
    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser){

//        if(browser.equalsIgnoreCase("chrome")){
//            WebDriverManager.chromedriver().driverVersion("130.0.6723.93").setup();
//            driver = new ChromeDriver();
//        }
//        else if(browser.equalsIgnoreCase("edge")){
//            WebDriverManager.edgedriver().setup();
//            driver = new EdgeDriver();
//        }

        this.browserFactory = BrowserFactory.getBrowserFactory(browser);
        this.driver = browserFactory.getDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));
    }

    /**
     * Tears down the WebDriver after each test method.
     * This method quits the WebDriver instance, closing the browser after the test completes.
     */
    @AfterClass
    public void tearDown() throws InterruptedException, IOException {
// Call the BrowserFactory's quit method to clean up the driver instance
        this.browserFactory.quitDriver();
    }

    private void takeScreenshotUsingAShot(File destFile){
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);
        try{
            ImageIO.write(screenshot.getImage(), "PNG", destFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
