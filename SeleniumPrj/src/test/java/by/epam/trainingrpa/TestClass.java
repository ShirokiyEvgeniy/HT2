package by.epam.trainingrpa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class TestClass {
    ResourceBundle resourceBundle;
    private String baseURL;
    private WebDriver driver = null;
    private MainPage mainPage = null;
    private LogInPage logInPage = null;
    private CreateUserPage createUserPage = null;
    private ManageUsersPage manageUsersPage = null;

    @BeforeClass
    public void beforeClass() {
        resourceBundle = ResourceBundle.getBundle("props");
        baseURL = resourceBundle.getString("baseURL");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(resourceBundle.getString("lang"));
        System.setProperty("webdriver.gecko.driver", resourceBundle.getString("driver"));
        this.driver = new FirefoxDriver(options);
        driver.get(baseURL);
        logInPage = PageFactory.initElements(this.driver, LogInPage.class);
        logInPage.setName(resourceBundle.getString("name")).setPassword(resourceBundle.getString("password")).pressButton();
        mainPage = PageFactory.initElements(this.driver, MainPage.class);
        createUserPage = PageFactory.initElements(this.driver, CreateUserPage.class);
        manageUsersPage = PageFactory.initElements(this.driver, ManageUsersPage.class);
    }

    @Test
    public void checkFirst() {
        mainPage.pressManageJenkins();

        Assert.assertTrue(mainPage.checkManageUsersDD(), "Отстутсвует тег dd с 'Create/delete/modify users that can log in to this Jenkins'");
        Assert.assertTrue(mainPage.checkManageUsersDT(), "Отстутсвует тег dt c 'Manage Users'");

        mainPage.pressManageUsers();

        Assert.assertTrue(createUserPage.createUser(), "Отсутствует ссылка 'Create User'");

        createUserPage.pressCreateUser();

        Assert.assertTrue(createUserPage.checkForm(driver), "Поля text != 3 или поля password != 2");
        Assert.assertTrue(createUserPage.checkEmptyRows(), "Поля не пустые");

        createUserPage.setUserName("someuser").setPassword("somepassword").setConfirmPassword("somepassword").setFullName("Some Full Name").setEmail("some@addr.dom");
        createUserPage.clickCreateUserButton();

        Assert.assertTrue(manageUsersPage.checkUserName(), "Отстутсвует someuser");

        manageUsersPage.clickDeleteUser();

        Assert.assertEquals(manageUsersPage.getDeleteUserInfo(), "Are you sure about deleting the user from Jenkins?");

        manageUsersPage.confirmDeleteUser();

        Assert.assertFalse(manageUsersPage.isUserNameEnabled(driver), "someuser не удалён");
        Assert.assertFalse(manageUsersPage.isUserNameDeleteReference(driver), "user/someuser/delete осталась");
        Assert.assertFalse(manageUsersPage.isAdminDeleteReference(driver), "Admin был удален");
    }

    @Test
    public void checkButtonsColor() {
        mainPage.pressManageJenkins();
        mainPage.pressManageUsers();
        createUserPage.pressCreateUser();
        Assert.assertEquals(createUserPage.getCreateUserButtonColor(), "#4b758b");
        createUserPage.setUserName("someuser").setPassword("somepassword").setConfirmPassword("somepassword").setFullName("Some Full Name").setEmail("some@addr.dom");
        createUserPage.clickCreateUserButton();
        manageUsersPage.clickDeleteUser();
        Assert.assertEquals(manageUsersPage.getYesButtonColor(), "#4b758b");
        manageUsersPage.confirmDeleteUser();
    }

    @Test(enabled = false)
    public void checkWarningMessage() {
        mainPage.pressManageJenkins();
        mainPage.pressManageUsers();
        createUserPage.pressCreateUser();
        createUserPage.setUserName("").setPassword("somepassword").setConfirmPassword("somepassword").setFullName("Some Full Name").setEmail("some@addr.dom");
        createUserPage.clickCreateUserButton();

        Assert.assertEquals(createUserPage.getErrorMessage(), "\"\" is prohibited as a full name for security reasons.");
    }

    @Test
    public void checkEnableAutoRefresh() {
        mainPage.pressEnableAutoRefresh();
        Assert.assertFalse(mainPage.checkEnableAutoRefresh());
        mainPage.pressDisableAutoRefresh();
        Assert.assertFalse(mainPage.checkDisableAutoRefresh());
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}