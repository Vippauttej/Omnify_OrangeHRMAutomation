package com.orangehrm.pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PIMPage {
    WebDriver driver;
    By addEmployeeButton =By.xpath("//button[@type=\"button\" and @class='oxd-button oxd-button--medium oxd-button--secondary']");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
    
    By employeeSearch = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
    By Search = By.xpath("//button[@type='submit']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addEmployee(String fName, String lName) {
        driver.findElement(addEmployeeButton).click();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        
        driver.findElement(saveButton).click();
    }

	
    public boolean verifyEmployee(String name) throws InterruptedException {
        driver.findElement(employeeSearch).clear();
        driver.findElement(employeeSearch).sendKeys(name);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        
        
        String expname = name;
		  
		  WebElement actname = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[3]/div")) ;
		  
		  String act = actname.getText();
		  Assert.assertEquals(act, expname);
		return false;
    }


}
