package testproject.testproject.helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import testproject.testproject.Utils;

public class LoginPageHelper {

    private static final String XPATH_LOGIN_BUTTON = "//*[@id='login']/button[@type='submit']";
    private static final String ID_USER = "username";
    private static final String ID_PASSWORD = "password";
    private static final String ID_MESSAGES = "flash-messages";

    public void login(String userName, String userPassword) {
        WebElement username = Utils.webDriver.findElement(By.id(ID_USER));
        username.clear();
        username.sendKeys(userName);

        WebElement password = Utils.webDriver.findElement(By.id(ID_PASSWORD));
        password.clear();
        password.sendKeys(userPassword);

        WebElement loginButton = Utils.webDriver.findElement(By.xpath(XPATH_LOGIN_BUTTON));
        loginButton.click();
    }

    public void checkMessages(String message) {
        waitElementById(ID_MESSAGES);

        WebElement messageElement = Utils.webDriver.findElement(By.id(ID_MESSAGES));
        WebElement closeElement = messageElement.findElement(By.tagName("a"));
        String actualMessage = messageElement.getText().replace(closeElement.getText(), "").trim();
        Assert.assertEquals(message, actualMessage);

        closeElement.click();
    }

    public void logout() {
        WebElement logoutButton = Utils.webDriver.findElement(By.linkText("Logout"));
        logoutButton.click();
    }

    private void waitElementById(String id) {
        new WebDriverWait(Utils.webDriver, Utils.timeout)
            .withMessage("Waiting for Element id=[" + id + "] is failed")
            .until(webdriver -> webdriver.findElement(By.id(id)));
    }

}
