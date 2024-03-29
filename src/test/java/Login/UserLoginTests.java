package Login;

import Base.BaseClassTests;
import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserLoginTests extends BaseClassTests {
    LoginPage loginPage;
    String password = "secret_sauce";
    @Parameters("browser")
    @BeforeTest
    public void Setup(String browser){
        super.Setup(browser);

        url= "https://www.saucedemo.com";

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
}
