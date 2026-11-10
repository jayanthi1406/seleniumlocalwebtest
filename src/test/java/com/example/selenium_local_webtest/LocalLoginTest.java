package com.example.selenium_local_webtest;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        URL resource = getClass().getClassLoader()
                .getResource("webpage/login.html");

        if (resource == null) {
            throw new IllegalArgumentException(
                    "File not found in resources!");
        }

        driver.get(resource.toString());
    }

    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("student");

        driver.findElement(By.id("password"))
                .sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        String message =
                driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Login Successful");
    }

    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("wrong");

        driver.findElement(By.id("password"))
                .sendKeys("wrong");

        driver.findElement(By.id("loginButton")).click();

        String message =
                driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message,
                "Invalid Username or Password");
    }

    @Test
    public void checkBackgroundColor() {

        String bgColor =
                driver.findElement(By.id("loginBox"))
                        .getCssValue("background-color");

        System.out.println("Background Color: " + bgColor);

        Assert.assertEquals(bgColor,
                "rgba(173, 216, 230, 1)");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
