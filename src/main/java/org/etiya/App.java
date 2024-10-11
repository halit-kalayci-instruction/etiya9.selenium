package org.etiya;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        // Bir insanın tarayıcı üzerinden yapabildiği bütün işlemleri html kodu üzerinden yapabilen bir otomasyon aracı.
        // click
        // type, send_keys()

        FirefoxDriver firefoxDriver = new FirefoxDriver();

        firefoxDriver.get("https://www.saucedemo.com/");

        WebElement usernameInput = firefoxDriver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = firefoxDriver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = firefoxDriver.findElement(By.id("login-button"));
        loginBtn.click();

        Thread.sleep(5000);
        firefoxDriver.quit();
    }
}

// /html/body/div[1]/div/div[2]/div[1]/div/div/form/div[2]/input
