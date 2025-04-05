package com.orangehrm.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver;
    By pimModule = By.xpath("//span[text()='PIM']");
    By logoutButton = By.xpath("//a[text()='Logout']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPIM() {
        driver.findElement(pimModule).click();
    }

    public boolean isDashboardDisplayed() {
        return driver.findElement(pimModule).isDisplayed();
    }

    public void logout() {
        driver.findElement(logoutButton).click();
    }
}
