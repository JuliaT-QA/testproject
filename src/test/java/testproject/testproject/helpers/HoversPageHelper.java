package testproject.testproject.helpers;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import testproject.testproject.Utils;

public class HoversPageHelper {

    private static final String CLASS_NAME_FIGURE = "figure";
    private static final String CLASS_NAME_FIGURE_CAPTION = "figcaption";

    public List<WebElement> getUsersAvatars(){
        List<WebElement> usersAvatars = Utils.webDriver.findElements(By.className(CLASS_NAME_FIGURE));
        return usersAvatars;
    }

    public void hoverAvatar(WebElement avatar){
        Actions actions = new Actions(Utils.webDriver);
        actions.moveToElement(avatar).perform();
    }

    public void checkUserName(WebElement avatar, String username){
        WebElement caption = avatar.findElement(By.className(CLASS_NAME_FIGURE_CAPTION));
        Assert.assertTrue(caption.isDisplayed());

        WebElement link = avatar.findElement(By.tagName("a"));
        String captionText = caption.getText().replace(link.getText(), "").trim();
        Assert.assertEquals("name: " + username, captionText);
    }

}
