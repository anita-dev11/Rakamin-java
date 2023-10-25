package SauceDemo;

import com.google.common.util.concurrent.TimeLimiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    @Test
    public void success_login_case (){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        //Membuka halaman login

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        //input username
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        //input password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        //click login
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        //quit

    }

    @Test
    public void failed_login_case (){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        //Membuka halaman login

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //input username
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        //input password yang salah
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_number");
        //click login
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        String ErrorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");

        driver.close();
    }
}