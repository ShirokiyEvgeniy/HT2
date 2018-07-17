package by.epam.trainingrpa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage {

    private WebDriver webDriver;

    @FindBy(id = "j_username")
    private WebElement login;

    @FindBy(name = "j_password")
    private WebElement password;

    @FindBy (xpath = "//input[@name = 'Submit'][@type='submit']")
    private WebElement button;

    LogInPage setName(String name)
    {
        login.clear();
        login.sendKeys(name);
        return this;
    }

    LogInPage setPassword(String passwordString)
    {
        password.clear();
        password.sendKeys(passwordString);
        return this;
    }

    LogInPage pressButton()
    {
        button.click();
        return this;
    }
}