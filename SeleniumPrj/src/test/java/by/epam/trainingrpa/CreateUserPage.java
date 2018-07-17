package by.epam.trainingrpa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;
import java.util.Iterator;

public class CreateUserPage {

    @FindBy(linkText = "Create User")
    private WebElement createUser;

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(xpath = "//input[@name = 'password1'][@type='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name = 'password2'][@type='password']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@name = 'fullname'][@type='text']")
    private WebElement fullname;

    @FindBy(xpath = "//input[@name = 'email'][@type='text']")
    private WebElement email;

    @FindBy(id = "yui-gen2-button")
    private WebElement createUserButton;

    @FindBy(xpath = "//div[@class='error']")
    private WebElement errorMessage;

    public String getCreateUserButtonColor() {
        String color = createUserButton.getCssValue("background-color");
        return Color.fromString(color).asHex();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    CreateUserPage setUserName(String name) {
        userName.sendKeys(name);
        return this;
    }

    CreateUserPage setPassword(String password1) {
        password.sendKeys(password1);
        return this;
    }

    CreateUserPage setConfirmPassword(String password2) {
        confirmPassword.sendKeys(password2);
        return this;
    }

    CreateUserPage setFullName(String fullName) {
        fullname.sendKeys(fullName);
        return this;
    }

    CreateUserPage setEmail(String Email) {
        email.sendKeys(Email);
        return this;
    }

    CreateUserPage clickCreateUserButton() {
        createUserButton.click();
        return this;
    }

    CreateUserPage pressCreateUser() {
        createUser.click();
        return this;
    }

    public boolean createUser() {
        return createUser.getText().equals("Create User");
    }

    public boolean checkEmptyRows() {
        return userName.getText().equals("") && fullname.getText().equals("") && password.getText().equals("")
                && confirmPassword.getText().equals("") && email.getText().equals("");
    }

    public boolean checkForm(WebDriver driver)
    {
        Collection<WebElement> elements = driver.findElements(By.tagName("form"));
        Iterator<WebElement> i = elements.iterator();
        WebElement element;
        if (i.hasNext()) {
            element = i.next();
            return element.findElements(By.xpath("//input[@type='text']")).size() == 3 && element.findElements(By.xpath("//input[@type='password']")).size() == 2;
        }
        return false;
    }

    WebElement getUserName() {
        return userName;
    }

    WebElement getFullname() {
        return fullname;
    }

    WebElement getPassword() {
        return password;
    }

    WebElement getConfirmPassword() {
        return confirmPassword;
    }

    WebElement getEmail() {
        return email;
    }
}