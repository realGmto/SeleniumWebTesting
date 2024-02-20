package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {
    String label;
    String description;
    String price;
    WebElement button;

    public Product(WebElement product) {
        label = product.findElement(By.className("inventory_item_name")).getText();
        description = product.findElement(By.className("inventory_item_desc")).getText();
        price = product.findElement(By.className("inventory_item_price")).getText();
        button = product.findElement(By.className("btn"));
    }

    @JsonCreator
    public Product(
            @JsonProperty("label") String label,
            @JsonProperty("description") String description,
            @JsonProperty("price") String price) {
        this.label = label;
        this.price = price;
        this.description = description;
    }

    public void AddToCart(){
        button.click();
    }

    static public boolean IsSameProduct(Product actual, Product expected){
        return actual.label.equals(expected.label) &&
                actual.description.equals(expected.description) &&
                actual.price.equals(expected.price);
    }

    static public boolean IsSameProducts(List<Product> actuals, List<Product> expecteds){
        for (int i =0; i< actuals.size();i++){
            if (!IsSameProduct(actuals.get(i),expecteds.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "label: " + label + "\ndescription: " +
                description + "\nprice: " + price;
    }
}

