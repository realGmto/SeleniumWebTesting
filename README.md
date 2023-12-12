# SeleniumWebTesting

## Cross Browser Testing
This test suite contains cross browser testing which means it runs all tests through multiple browsers.
### Setup
Steps to add new browser to run all tests through:
1. Add new conditional branching in BaseClassTests where you test if browser variable equals to the name of new browser and if it does then setup the driver and instantiate it. Example:
```
else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
```
2. Navigate to testng.xml and add new <test></test> tag which will have the name: [part that is to be tested]-[name of the new browser]
3. Inside <test></test> add <parameter></parameter> tag in which you will add name browser and value which is the name of the new browser. Example:
```
    <test name="Login-chrome">
        <parameter name="browser" value="chrome"></parameter>
        <classes>
            <class name="SwagLabsLogin.UserLoginTests"/>
            <class name="SwagLabsLogin.AccessWithoutLoginTests"></class>
        </classes>
    </test>
```