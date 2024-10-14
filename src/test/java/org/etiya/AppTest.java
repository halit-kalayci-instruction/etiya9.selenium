package org.etiya;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
public class AppTest {

  @Test
  public void testTitleShouldBeCorrect()
  {
    // 3A Principle
    // Arrange => Hazırlık aşaması (selenium tanımı, veri okunması, beklenti olan verinin tanımlanması)
    // Act => (Action) Aksiyon aşaması (arrange'de hazırladığım veriler ile işlemi gerçekleştir.)
    // Assert => Doğrulama aşaması ( expectedResult == actualResult ) ?
    // Arrange
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    String expectedResult = "Swag Labs";
    //
    // act
    firefoxDriver.get("https://www.saucedemo.com/");
    String actualResult = firefoxDriver.getTitle();
    //
    // assert
    // Assert fonksiyonları
    // assert expectedResult.equals(actualResult);
    assertEquals(expectedResult, actualResult, "Titlelar uyuşmuyor.");
    firefoxDriver.quit();
    //
  }

  @Test
  public void testLoginWithCorrectCredentials() throws InterruptedException {
    // Yavaşlık durumu

    FirefoxDriver firefoxDriver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(firefoxDriver, Duration.ofSeconds(10));
    SoftAssertions softAssertions = new SoftAssertions();

    firefoxDriver.get("https://www.saucedemo.com/");
    try{
      WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
      softAssertions.assertThat(usernameInput.isDisplayed())
              .withFailMessage("Kullanıcı adı girişi bulunamadı.");
      usernameInput.sendKeys("standard_user");

      WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
      softAssertions.assertThat(passwordInput.isDisplayed())
              .withFailMessage("Şifre girişi bulunamadı.");
      passwordInput.sendKeys("secret_sauce");

      WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
      softAssertions.assertThat(loginBtn.isDisplayed())
              .withFailMessage("Giriş yap butonu bulunamadı.");
      loginBtn.click();

      softAssertions.assertThat(firefoxDriver.getCurrentUrl())
              .isEqualTo("https://www.saucedemo.com/inventory.html1")
              .withFailMessage("Giriş yapıldıktan sonra yönlendirilen url yanlış.");
    }finally {
      softAssertions.assertAll();
      firefoxDriver.quit();
    }


  }

}
