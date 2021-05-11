import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class basePage {

    public static WebDriver driver;

    @Before
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //SessionData used for referencing data across steps to verify consistency
    private static HashMap<String, String> sessionData = new HashMap<>();

    public void updateSessionData(String key, String value) {
        if (sessionData.containsKey(key))
            sessionData.replace(key, value);
        else
            sessionData.put(key, value);
    }

    public String getSessionData(String key) {
        return sessionData.get(key);
    }

    public void waitForElementClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementDisplayed(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    //For inputs that don't respond well to the copy/paste behavior of sendkeys
    public void typeText(WebElement element, String text) {
        element.clear();
        for (char c : text.toCharArray())
            element.sendKeys(String.valueOf(c));
    }

    public WebElement webElement(By by) {
        return webElement(by, 0);
    }

    public WebElement webElement(By by, int index) {
        return driver.findElements(by).get(index);
    }
}
