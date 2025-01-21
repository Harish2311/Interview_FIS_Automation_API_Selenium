import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Ebay {
    public static void main(String[] args) throws ParseException, InterruptedException {

        //Launch Webdirver

        System.setProperty("webdriver.edge.driver", "C:\\Users\\haris\\Downloads\\edge\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // Search for shoe to be added to cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='eBay Home']")));
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("puma shoes");
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//*[@role=\"heading\"])[3]")).click();

        //Switch to new window

        String winHandleBefore = driver.getWindowHandle();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Buy It Now']")));
        driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@aria-label='Close overlay']")).click();
        Thread.sleep(5000);

        //Validate Cart

        Assert.assertEquals("1", driver.findElement(By.xpath("//span[@class='gh-cart__icon']")).getText());
        driver.switchTo().window(winHandleBefore);

        driver.quit();

    }
}
