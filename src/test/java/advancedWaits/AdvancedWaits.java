package advancedWaits;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AdvancedWaits {

    WebDriver driver;
    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }
    /*
    example for use JavascriptExecutor where it scroll view untill find some element
     */
    @Test
    public void javascriptExecutorScrollView(){
        driver.navigate().to("https://the-internet.herokuapp.com/");
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//a[@href='/javascript_alerts']")));
        driver.findElement(By.xpath(".//a[@href='/javascript_alerts']")).click();
        driver.findElement(By.xpath("(//button)[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().accept();
        Assertions.assertEquals("You clicked: Ok", driver.findElement(By.id("result")).getText());
    }
    /*
    additionaly, use wait for alertIsPresent
     */
    @Test
    public void waitForAlertIsPresent(){
        driver.navigate().to("https://the-internet.herokuapp.com/");
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//a[@href='/javascript_alerts']")));
        driver.findElement(By.xpath(".//a[@href='/javascript_alerts']")).click();
        driver.findElement(By.xpath("(//button)[text()='Click for JS Confirm']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Assertions.assertEquals("You clicked: Ok", driver.findElement(By.id("result")).getText());
    }
}
