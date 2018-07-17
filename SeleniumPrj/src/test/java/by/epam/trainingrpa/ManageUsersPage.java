package by.epam.trainingrpa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;

public class ManageUsersPage {

    @FindBy(xpath = "//table[@id='people']/tbody/tr/td[2]/a[@href='user/someuser/']")
    private WebElement userName;

    @FindBy(xpath = "//a[@href='user/someuser/delete']")
    private WebElement deleteUser;

    @FindBy(id = "yui-gen2-button")
    private WebElement confirmDeleteUser;

    @FindBy(xpath = "//form[@method='post'][@name='delete']")
    private WebElement info;

    @FindBy(xpath = "//table[@id='people']/tbody/tr/td/a[@href='user/admin/delete']")
    private WebElement deleteAdmin;

    public String getYesButtonColor() {
        String color = confirmDeleteUser.getCssValue("background-color");
        return Color.fromString(color).asHex();
    }

    ManageUsersPage clickDeleteUser() {
        deleteUser.click();
        return this;
    }

    ManageUsersPage confirmDeleteUser() {
        confirmDeleteUser.click();
        return this;
    }

    public boolean checkUserName() {
        return this.userName.getText().equals("someuser");
    }

    public String getDeleteUserInfo() {
        return info.getText().substring(0, info.getText().indexOf('\n'));
    }


    public boolean isUserNameEnabled(WebDriver driver) {
        try {
            return this.userName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserNameDeleteReference(WebDriver driver) {
        try {
            return deleteUser.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAdminDeleteReference(WebDriver driver) {
        try {
            return deleteAdmin.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
