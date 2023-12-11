package SwagLabsLogin;

import PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccessWithoutLoginTests {
    String url;
    WebDriver driver;
    LoginPage loginPage;

    @BeforeTest
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void AccessMainPage(){
        url = "https://www.saucedemo.com/inventory.html";
        driver.get(url);

        loginPage = new LoginPage(driver);

        String actual = loginPage.GetErrorMessage();

        Assert.assertEquals(actual, "Epic sadface: You can only access '/inventory.html' when you are logged in.");
    }

    @Test
    public  void AccessCartPage(){
        url ="https://www.saucedemo.com/cart.html";
        driver.get(url);

        loginPage = new LoginPage(driver);

        String actual = loginPage.GetErrorMessage();

        Assert.assertEquals(actual, "Epic sadface: You can only access '/cart.html' when you are logged in.");
    }

    @AfterTest
    public void TearDown(){
        driver.quit();
    }
}
