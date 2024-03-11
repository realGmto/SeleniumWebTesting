package PageObjectModel;

import Base.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    @FindBy(className = "cart_item")
    private List<WebElement> elements;

    public CartPage(WebDriver driver){
        this.driver=driver;
        if(!driver.getTitle().equals("Swag Labs")){
            throw new IllegalStateException("This is not Cart Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        else{
            PageFactory.initElements(driver,this);
        }
    }

    public List<Product> GetCartItems(){
        List<Product> products = new ArrayList<>();

        for (WebElement element : elements){
            products.add(new Product(element));
        }

        return products;
    }

}
