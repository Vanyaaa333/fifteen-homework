package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class BMICalculatorTest {

    protected static String BASE_URL = "https://healthunify.com/bmicalculator/";

    WebDriver driver;
    WebElement weightElement;
    WebElement heightElement;
    WebElement calculateBottom;
    WebElement bmiStatus;


    @BeforeMethod
    private void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setHeadless(true);
        driver = new ChromeDriver(option);
        driver.get(BASE_URL);
        weightElement = driver.findElement(By.name("wg"));
        heightElement = driver.findElement(By.name("ht"));
        calculateBottom = driver.findElement(By.name("cc"));
        bmiStatus = driver.findElement(By.className("content"));

    }

    @Test
    public void normalCategoryTest(){
        weightElement.sendKeys("54");
        heightElement.sendKeys("165");
        calculateBottom.click();
        Assert.assertEquals(bmiStatus.getAttribute("value"),"Your category is Normal","Parameters do not correspond to this category");
    }

    @Test
    public void overweightCategoryTest(){
        weightElement.sendKeys("90");
        heightElement.sendKeys("186");
        calculateBottom.click();
        Assert.assertEquals(bmiStatus.getAttribute("value"),"Your category is Overweight","Parameters do not correspond to this category");
    }

    @Test
    public void obeseCategoryTest(){
        weightElement.sendKeys("110");
        heightElement.sendKeys("175");
        calculateBottom.click();
        Assert.assertEquals(bmiStatus.getAttribute("value"),"Your category is Obese","Parameters do not correspond to this category");
    }

    @Test
    public void starvationCategoryTest(){
        weightElement.sendKeys("40");
        heightElement.sendKeys("175");
        calculateBottom.click();
        Assert.assertEquals(bmiStatus.getAttribute("value"),"Your category is Starvation","Parameters do not correspond to this category");
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }

}
