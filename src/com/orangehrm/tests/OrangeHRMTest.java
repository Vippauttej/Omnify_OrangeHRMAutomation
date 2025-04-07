package com.orangehrm.tests;

import com.orangehrm.pages.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PIMPage pimPage;
    

    @BeforeClass
    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        pimPage = new PIMPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Login Failed");
    }

    @Test(priority = 2)
    public void addEmployeesTest() {
        dashboardPage.navigateToPIM();
        pimPage.addEmployee("Sai", "ss");
        dashboardPage.navigateToPIM();
        pimPage.addEmployee("shailu", "sm");
        dashboardPage.navigateToPIM();
        pimPage.addEmployee("Bhanu", "kk");
        dashboardPage.navigateToPIM();
        pimPage.addEmployee("shravs", "ll");
    }

    @Test(priority = 3)
    public void verifyEmployeesTest() throws InterruptedException {
        dashboardPage.navigateToPIM();
        Assert.assertTrue(pimPage.verifyEmployee("Sai"));
        dashboardPage.navigateToPIM();
        Assert.assertTrue(pimPage.verifyEmployee("shailu"));
        dashboardPage.navigateToPIM();
        Assert.assertTrue(pimPage.verifyEmployee("Bhanu"));
        dashboardPage.navigateToPIM();
        Assert.assertTrue(pimPage.verifyEmployee("shravs"));
    }
    
    @Test(priority = 4)
    public void logoutTest() {
    	Actions act = new Actions(driver);
  	  act.moveToElement(driver.findElement(By.className("oxd-userdropdown-tab"))).build().perform();
       driver.findElement(By.linkText("Logout")).click();
       
    }

    
}
