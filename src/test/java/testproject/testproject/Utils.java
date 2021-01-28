package testproject.testproject;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import testproject.testproject.helpers.HoversPageHelper;
import testproject.testproject.helpers.LoginPageHelper;
import testproject.testproject.helpers.TablesPageHelper;

public class Utils {

    public static WebDriver webDriver;
    public static int timeout = 60;

    public LoginPageHelper loginPage = new LoginPageHelper();
    public HoversPageHelper hoversPage = new HoversPageHelper();
    public TablesPageHelper tablesPage = new TablesPageHelper();

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", LocalProperties.getProperty("chromedriver"));

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

    public void openPage(String url) {
        webDriver.get(url);
        waitDocumentReadyStateComplete();
    }

    private void waitDocumentReadyStateComplete() {
        new WebDriverWait(Utils.webDriver, Utils.timeout)
            .withMessage("Waiting for ReadyState complete is failed")
            .until(webdriver -> Boolean.valueOf(execJs("return document.readyState == 'complete'")));
    }

    private String execJs(String script) {
        String res = null;
        try {
            Object result = ((JavascriptExecutor) Utils.webDriver).executeScript(script);
            if (result != null) {
                res = result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
