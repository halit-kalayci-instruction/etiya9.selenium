package org.etiya;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // firefoxOptions.addArguments("--headless"); // ** Eğer tarayıcı açılmasın istiyorsak..

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);

        getAllProducts(firefoxDriver);

        Thread.sleep(5000);
        firefoxDriver.quit();

        // Veri karşılaştırma
    }

    private static void getAllProducts(FirefoxDriver firefoxDriver)
    {
        firefoxDriver.get("https://www.saucedemo.com/");

        WebElement usernameInput = firefoxDriver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = firefoxDriver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = firefoxDriver.findElement(By.id("login-button"));
        loginBtn.click();

        List<WebElement> products = firefoxDriver.findElements(By.className("inventory_item"));

        List<String> productNames = new ArrayList<>();
        for(WebElement element: products)
        {
            WebElement headerElement = element.findElement(By.cssSelector("[data-test='inventory-item-name']"));
            productNames.add(headerElement.getText());

            WebElement priceElement = element.findElement(By.className("inventory_item_price"));

            System.out.println(headerElement.getText() + " " + priceElement.getText());
        }

        String excelFilePath = "C:\\Users\\klyyc\\Desktop\\Projects\\Selenium\\etiya.nine\\src\\product_names.xlsx";

        List<String> excelList = new ArrayList<>();
        try(FileInputStream fs = new FileInputStream(new File(excelFilePath)))
        {
            Workbook workbook = new XSSFWorkbook(fs);

            Sheet sheet = workbook.getSheet("product_names");

            for(Row row: sheet)
            {
                Cell cell = row.getCell(0);
                excelList.add(cell.getStringCellValue());
            }
        }
        catch(Exception e)
        {
            System.out.println("Excel dosyası okunamadı..");
        }

        for(String p:excelList)
        {
            System.out.println("Excelden okundu: " + p);
        }

        if(excelList.equals(productNames))
        {
            System.out.println("Ürünler uyuşuyor");
        }else{
            System.out.println("Ürünler uyuşmuyor");
        }


        // CSV örneğini ekleyelim..
    }
}
// /html/body/div[1]/div/div[2]/div[1]/div/div/form/div[2]/input
