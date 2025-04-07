package com.orangehrm.pages;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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

	
    
    public boolean verifyEmployee(String expectedName) {
        try {
            driver.findElement(employeeSearch).clear();
            driver.findElement(employeeSearch).sendKeys(expectedName);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(2000); // OR use WebDriverWait

            List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));
            for (WebElement row : rows) {
                String actualName = row.findElement(By.xpath(".//div[3]")).getText().trim();
                System.out.println("Found: " + actualName); // Debug log
                if (actualName.equalsIgnoreCase(expectedName)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
