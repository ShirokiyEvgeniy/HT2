package by.epam.trainingrpa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[@href='securityRealm/'][@title='Manage Users']")
    private WebElement manageUsers;

    @FindBy(xpath = "//a[@href='securityRealm/'][@title='Manage Users']/dl/dt[text()='Manage Users']")
    private WebElement manageUsersDT;

    @FindBy(xpath = "//a[@href='securityRealm/'][@title='Manage Users']/dl/dd[text()='Create/delete/modify users that can log in to this Jenkins']")
    private WebElement manageUsersDD;

    @FindBy(xpath = "//div[@class ='smallfont']/a[@href='?auto_refresh=true']")
    private WebElement enableAutoRefresh;

    @FindBy(xpath = "//div[@class ='smallfont']/a[@href='?auto_refresh=false']")
    private WebElement disableAutoRefresh;

    MainPage pressManageJenkins() {
        manageJenkins.click();
        return this;
    }

    MainPage pressManageUsers() {
        manageUsers.click();
        return this;
    }

    MainPage pressDisableAutoRefresh() {
        disableAutoRefresh.click();
        return this;
    }

    MainPage pressEnableAutoRefresh() {
        enableAutoRefresh.click();
        return this;
    }

    public boolean checkEnableAutoRefresh() {
        try {
            enableAutoRefresh.click();
            return true;
        } catch (Exception exc) {
            return false;
        }
    }

    public boolean checkDisableAutoRefresh() {
        try {
            disableAutoRefresh.click();
            return true;
        } catch (Exception exc) {
            return false;
        }
    }

    boolean checkManageUsersDT() {
        return manageUsersDT.getText().equals("Manage Users");
    }

    boolean checkManageUsersDD() {
        return manageUsersDD.getText().equals("Create/delete/modify users that can log in to this Jenkins");
    }
}