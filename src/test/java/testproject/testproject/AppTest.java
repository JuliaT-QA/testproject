package testproject.testproject;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class AppTest extends Utils {

    @Test
    public void testCase1()
    {
        openPage("http://the-internet.herokuapp.com/login");
        loginPage.login("tomsmith", "SuperSecretPassword!");
        loginPage.checkMessages("You logged into a secure area!");
        loginPage.logout(); //BUG: the flash-messages was closed in the previous step, but after the logout it reappeared
    }

    @Test
    public void testCase2()
    {
        openPage("http://the-internet.herokuapp.com/login");
        loginPage.login("NOT_EXIST_STRING", "NOT_EXIST_STRING");
        loginPage.checkMessages("Your username is invalid!");
    }

    @Test
    public void testCase3()
    {
        openPage("http://the-internet.herokuapp.com/login");
        loginPage.login("tomsmith", "NOT_EXIST_STRING");
        loginPage.checkMessages("Your password is invalid!");
    }

    @Test
    public void testCase4()
    {
        openPage("http://the-internet.herokuapp.com/hovers");
        List<WebElement> usersAvatars = hoversPage.getUsersAvatars();
        Assert.assertEquals(3, usersAvatars.size());

        hoversPage.hoverAvatar(usersAvatars.get(0));
        hoversPage.checkUserName(usersAvatars.get(0), "user1");

        hoversPage.hoverAvatar(usersAvatars.get(1));
        hoversPage.checkUserName(usersAvatars.get(1), "user2");

        hoversPage.hoverAvatar(usersAvatars.get(2));
        hoversPage.checkUserName(usersAvatars.get(2), "user3");
    }

    @Test
    public void testCase5()
    {
        openPage("http://the-internet.herokuapp.com/tables");

        String tableId = "table2";
        String columnHeader = "Last Name";
        tablesPage.checkRowsCount(tableId, 5);
        tablesPage.checkColumnsCount(tableId, 6);

        List<String> unsortedValues = Arrays.asList("Smith", "Bach", "Doe", "Conway");
        tablesPage.checkColumnValues(tableId, columnHeader, unsortedValues);

        List<String> sortedAscValues = Arrays.asList("Bach", "Conway", "Doe", "Smith");
        tablesPage.sortColumn(tableId, columnHeader);
        tablesPage.checkColumnValues(tableId, columnHeader, sortedAscValues);

        List<String> sortedDescValues = Arrays.asList("Smith", "Doe", "Conway", "Bach" );
        tablesPage.sortColumn(tableId, columnHeader);
        tablesPage.checkColumnValues(tableId, columnHeader, sortedDescValues);
    }

}
