package SwagLabsLogin;

import Base.BaseClassTests;
import PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AccessWithoutLoginTests extends BaseClassTests {
    LoginPage loginPage;
    @Parameters("browser")
    @BeforeTest
    public void Setup(String browser){
        super.Setup(browser);
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
    public void AccessCartPage(){
        url ="https://www.saucedemo.com/cart.html";
        driver.get(url);

        loginPage = new LoginPage(driver);

        String actual = loginPage.GetErrorMessage();

        Assert.assertEquals(actual, "Epic sadface: You can only access '/cart.html' when you are logged in.");
    }
}
