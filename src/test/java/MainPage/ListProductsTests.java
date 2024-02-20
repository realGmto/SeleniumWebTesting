package MainPage;

import Base.BaseClassTests;
import Base.Product;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class ListProductsTests extends BaseClassTests {

    HomePage homePage;

    ObjectMapper objectMapper;

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
    }
    //Test if every product is shown in the main page
    @Test
    public void GetListOfProductsTest(){
        List<Product> actual = homePage.getProducts();

        try {
            List<Product> expected = objectMapper.readValue(new File("C:\\Users\\JA\\Desktop\\programming\\Software testing\\SeleniumWebTesting\\src\\main\\java\\Data\\ListOfProducts.json")
                    ,objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            Assert.assertTrue(Product.IsSameProducts(actual,expected));
        }   catch (IOException e){
            e.printStackTrace();
        }
    }
}
