package com.orangehrm.pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
    WebDriver driver;
    By addEmployeeButton =By.xpath("//button[@type=\"button\" and @class='oxd-button oxd-button--medium oxd-button--secondary']");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By saveButton = By.xpath("//button[@type='submit']");
    
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

	/*
	 * public boolean verifyEmployee(String name) throws InterruptedException {
	 * 
	 * 
	 * driver.findElement(employeeSearch).clear();
	 * driver.findElement(employeeSearch).sendKeys(name);
	 * driver.findElement(Search).click(); Thread.sleep(3000); JavascriptExecutor js
	 * = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,500)");
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	 * String Rec =
	 * driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]"))
	 * .getText();
	 * 
	 * wait.until(ExpectedConditions.textToBePresentInElementLocated(Rec, name));
	 * 
	 * return driver.getPageSource().contains(name);
	 * 
	 * }
	 */
    public boolean verifyEmployee(String name) {
        driver.findElement(employeeSearch).clear();
        driver.findElement(employeeSearch).sendKeys(name);
        driver.findElement(Search).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        // Locator for the search result count
        By recordCountLocator = By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]");
        
        // Wait for the record count to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordCountLocator));
        
        // Get the text (it will be "0 record found" if no match)
        String recordText = driver.findElement(recordCountLocator).getText();
        
        // If "0 record found" is present, return false, else return true
        return !recordText.contains("0 record found");
    }


}