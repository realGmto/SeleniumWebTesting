package PageObjectModel;

import Base.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomePage {
    private WebDriver driver;
    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/span")
    private WebElement title;
    @FindBy(className = "inventory_item")
    private List<WebElement> elements;

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Swag Labs")) {
            throw new IllegalStateException("This is not Home Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        else PageFactory.initElements(driver,this);
    }

    public String getTitle(){
        return title.getText();
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        for (WebElement element : elements){
            Product product = new Product(element);
            products.add(product);
        }

        return products;
    }

    public CartPage MoveToCart() {
        cart.click();
        return new CartPage(driver);
    }

    public List<WebElement> GetElements() {
        return elements;
    }

    public void AddProductToCart(WebElement webElement) {
        webElement.findElement(By.className("btn")).click();
    }
}
