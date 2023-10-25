package SauceDemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Halaman login Swag Labs")
    public void halamanLoginSwagLabs(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("Input username")
    public void inputUsername() {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
    }

    @And("Password")
    public void password() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
    }

    @And("Click login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @Then("User on dashboard page")
    public void userOnDashboardPage() {
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
        String username = driver.findElement(By.xpath("//*[@id=\"user-name\"]")).getText();
        Assert.assertEquals(username, "standard_user");
    }

    @And("Input Invalid password")
    public void inputInvalidPassword() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_number");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        String ErrorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");
    }

}
