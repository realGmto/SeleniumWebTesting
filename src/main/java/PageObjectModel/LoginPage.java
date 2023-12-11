package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    @FindBy(xpath = "/html/body/div/div/div[1]")
    private WebElement title;
    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button" )
    private WebElement loginButton;
    @FindBy(xpath = "/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        if(!driver.getTitle().equals("Swag Labs")){
            throw new IllegalStateException("This is not Login Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        else{
            PageFactory.initElements(driver,this);
        }
    }

    public HomePage LoginValidUser(String username, String password){
        CleanTextFields();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        return new HomePage(driver);
    }

    public String LoginInvalidUser(String username, String password){
        CleanTextFields();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        return GetErrorMessage();
    }

    public String GetErrorMessage(){
        return errorMessage.getText();
    }

    private void CleanTextFields(){;
        driver.navigate().refresh();
    }
}
