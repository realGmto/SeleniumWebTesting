package MainPage;

import Base.BaseClassTests;
import Base.Product;
import PageObjectModel.CartPage;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AddProductsToCartTests extends BaseClassTests {

    HomePage homePage;

    CartPage cartPage;

    ObjectMapper objectMapper;

    private List<WebElement> elements;

    Random random;

    @Parameters("browser")
    @BeforeTest
    public void Setup(String browser){
        super.Setup(browser);

        url= "https://www.saucedemo.com";

        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        LoginPage loginPage = new LoginPage(driver);

        homePage = loginPage.LoginValidUser("standard_user","secret_sauce");

        objectMapper = new ObjectMapper();

        random = new Random();
    }

    @Test
    public void AddToCartTest(){
        elements = homePage.GetElements();
        HashSet<Integer> numbers = new HashSet<Integer>();
        List<Product> expected = new ArrayList<>();
        List<Product> actual;

        // Chooses random amount of elements randomly
        for(int i=0;i<elements.size();i++){
            numbers.add(random.nextInt(elements.size()));
        }

        for (int number : numbers){
            homePage.AddProductToCart(elements.get(number));
            expected.add(new Product(elements.get(number)));
        }

        cartPage = homePage.MoveToCart();

        actual = cartPage.GetCartItems();

        Assert.assertTrue(Product.IsSameProducts(actual,expected));
    }
}
