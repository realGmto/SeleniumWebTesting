# SeleniumWebTesting


## Page Object Model
Page object model or POM is a design pattern in which webpages are represented as classes, and elements on the page are defined as variables on the class. 
### Setup
Steps to create a class which represents webpage. This will be an example of an login webpage:
1. Create new class inside PageObjectModel package.

2. Define all elements of a webpage that will be used as variables of a class. Example (@FindBy is used to setup the location of elements on a webpage):
```java
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
```
3. Create Constructor for class which takes WebDriver as a parameter and initializes all variables declared. Example:
```java
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
```
4. Now we can expand on this class by writting methods, but to use it we just need to import it inside our testing file. Example:
```java
import PageObjectModel.LoginPage;
...
// Usage
LoginPage loginPage; = new LoginPage(driver);
loginPage.LoginValidUser(username,password).getTitle();
```


## WebDriver Manager
This project uses WebDriver Manager library which makes setting up drivers really easy. It is because we don't have to go and download drivers for our WebDrivers, rather they will be automatically downloaded.
### How to use
Steps to use this library:
1. Import WebDriverManager library like this:
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Setup the needed driver based on which browser we want to use. Example:
```java
WebDriverManager.chromedriver().setup();
```
3. Last step is to initialize WebDriver variable. Example:
```java
driver = new ChromeDriver();
```


## Cross Browser Testing
This test suite contains cross browser testing which means it runs all tests through multiple browsers.
### Setup
Steps to add new browser to run tests:
1. Add new conditional branching in BaseClassTests where you test if browser variable equals to the name of new browser and if it does then setup the driver and instantiate it. Example:
```java
else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
```
2. Navigate to testng.xml and add new ```<test></test>``` tag which will have the name: [part that is to be tested]-[name of the new browser]

3. Inside <test></test> add ```<parameter></parameter>``` tag in which you will add name browser and value which is the name of the new browser. Example:
```xml
    <test name="Login-chrome">
        <parameter name="browser" value="chrome"></parameter>
        <classes>
            <class name="SwagLabsLogin.UserLoginTests"/>
            <class name="SwagLabsLogin.AccessWithoutLoginTests"></class>
        </classes>
    </test>
```