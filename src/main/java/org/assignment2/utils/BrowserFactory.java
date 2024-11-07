package org.assignment2.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    private static ThreadLocal<BrowserFactory> browserFactoryThreadLocal = new ThreadLocal<>();
    static String browserType;

    private ThreadLocal<WebDriver> threadLocal = ThreadLocal.withInitial(()->{
        WebDriver driver = null;
        switch(this.browserType.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().driverVersion("130.0.6723.93").setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("The browser is not defined");
        }
        driver.manage().window().maximize();
        return driver;

    });

    private BrowserFactory(String browserType){
        this.browserType = browserType;
    }

    public static BrowserFactory getBrowserFactory(String browserType){
        if(browserFactoryThreadLocal.get() == null){
            browserFactoryThreadLocal.set(new BrowserFactory(browserType));
        }
        return browserFactoryThreadLocal.get();
    }

    public WebDriver getDriver(){
        return threadLocal.get();
    }

    // Method to clean up the WebDriver for the current thread
    public void quitDriver() {
        WebDriver driver = threadLocal.get();
        if (driver != null) {
            driver.quit();
            threadLocal.remove();
        }
    }
}
