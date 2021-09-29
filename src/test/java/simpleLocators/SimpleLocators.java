package simpleLocators;

/*
By.cssSelector
By.tagName
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleLocators {

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
    By.className
    if element class have multiple attributes and you want to use By.className deliberately
    you need to use kind of below test
     */
    @Test
    public void findElementWithTwoClassNames(){
        driver.navigate().to("https://the-internet.herokuapp.com/add_remove_elements/");
        List<WebElement> list = driver.findElements(By.className("columns"));
        WebElement elementWithTwoClasses = null;
        for (WebElement tmp: list) {
            String elementClass = tmp.getAttribute("class");
            if(elementClass.equals("large-12 columns")){
                elementWithTwoClasses = tmp;
            }
        }
        Assertions.assertNotNull(elementWithTwoClasses, "Element with this two phrase class element NOT FOUND");
    }
    /*
    By.id
     */
    @Test
    public void findElementById(){
        driver.navigate().to("https://the-internet.herokuapp.com/add_remove_elements/");
        String expected = "Add/Remove Elements\n\nAdd Element";
        String actual = driver.findElement(By.id("content")).getText();
        Assertions.assertEquals(expected, actual);
    }
    /*
    By.name
    */
    @Test
    public void findElementByName(){
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        WebElement input = driver.findElement(By.name("username"));
        Assertions.assertNotNull(input, "Element with this name NOT FOUND");
    }
    /*
    By.linkText
    */
    @Test
    public void findElementByLinkText(){
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading");
        String expected = "Example 1: Element on page that is hidden";
        String actual = driver.findElement(By.linkText("Example 1: Element on page that is hidden")).getText();
        Assertions.assertEquals(expected, actual);
    }
    /*
    By.partialLinkText
     */
    @Test
    public void findElementByPartialLinkText(){
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading");
        String expected = "Example 1: Element on page that is hidden";
        String actual = driver.findElement(By.partialLinkText("Example 1: Element ")).getText();
        Assertions.assertEquals(expected, actual);
    }
    /*
    By.xpath
     */
    @Test
    public void findElementByXpath1(){
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        WebElement input = driver.findElement(By.xpath("//input[@id='username']"));
        Assertions.assertTrue(input.isDisplayed(), "Element with this name NOT FOUND");
    }
    @Test
    public void findElementByXpath2(){
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading");
        WebElement example1 = driver.findElement(By.xpath("//a[@href='/dynamic_loading/1']"));
        Assertions.assertTrue(example1.isDisplayed(), "Element with this name NOT FOUND");
    }
    @Test
    public void findElementByXpath3(){
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading");
        WebElement forkMe = driver.findElement(By.xpath("//img[@alt='Fork me on GitHub']"));
        Assertions.assertTrue(forkMe.isDisplayed(), "Element with this name NOT FOUND");
    }
    /*
    By.xpath
    xpath combined with two html tags (div, input, etc.) with attributes (id, class, etc.) with values (username, etc.)
    */
    @Test
    public void findElementByXpath4(){
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        WebElement input = driver.findElement(By.xpath("//div[@class='large-6 small-12 columns']//input[@id='username']"));
        Assertions.assertTrue(input.isDisplayed() , "Element with this name NOT FOUND");
    }
    /*
    By.xpath
    using starts-with
    */
    @Test
    public void findElementByXpath5(){
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        WebElement input = driver.findElement(By.xpath("//div[starts-with(@class, 'large-6')]"));
        Assertions.assertTrue(input.isDisplayed() , "Element with this name NOT FOUND");
    }

}
