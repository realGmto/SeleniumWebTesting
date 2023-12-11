package SwagLabsLogin;

import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserLoginTests {
    String url= "https://www.saucedemo.com";
    WebDriver driver;
    LoginPage loginPage;
    String password = "secret_sauce";

    @BeforeTest
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        loginPage = new LoginPage(driver);
    }

    @Test
    public void ValidUserLogin() throws InterruptedException {
        String username = "standard_user";

        String actual = loginPage.LoginValidUser(username,password).getTitle();

        Assert.assertEquals(actual,"Products");
    }

    @Test
    public void InvalidUsernameLogin(){
        String username = "invalid_user";

        String actual = loginPage.LoginInvalidUser(username,password);

        Assert.assertEquals(actual,"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void LockedUserLogin(){
        String username = "locked_out_user";

        String actual = loginPage.LoginInvalidUser(username,password);

        Assert.assertEquals(actual,"Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterTest
    public void Teardown(){
        driver.quit();
    }
}
