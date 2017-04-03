import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * Created by Yana on 30.03.2017.
 */
public class FindHospital {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    String searchButton = "a[href*='#toggle-search']";
    String selectSearch = "select_search";
    String selectFind = "button.btn-info";
    String urlHome = "HospitalSeeker/";
    String urlSearch = "hospitals?q=";
    String result = ".//*[@class='card panel panel-default text-xs-right']";
    String classOfCss = "[class='card panel panel-default text-xs-right']";


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver   = new FirefoxDriver();
        baseUrl = "https://localhost:8443/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSeleniumTestFindHospital() throws Exception {
        String searchWord = "поліклініка";
        testMain(searchWord, 1);
    }

    //вынести несколько позиций в отдельный метод Сёрч

    @Test
    public void testSeleniumTestFindHospitalNum2() throws Exception {
        String searchWord = "Angel";
        testMain(searchWord, 2);
//        driver.get(baseUrl + urlHome);
//        if (isElementPresent(By.cssSelector(searchButton))) {
//            driver.findElement(By.cssSelector(searchButton)).click();
//        }
//        driver.findElement(By.id(selectSearch)).click();

//        driver.findElement(By.id(selectSearch)).sendKeys(searchWord);
//        driver.findElement(By.cssSelector(selectFind)).click();
//        driver.get(baseUrl + urlHome + urlSearch + searchWord);
//        int expected = 2;
//        assertEquals(expected, countOfElement(By.cssSelector(classOfCss)));
    }

    @Test
    public void testSeleniumTestFindHospitalNum3() throws Exception {
        String searchWord = "абрвал";
        testMain(searchWord, 0);
//
//        if (isElementPresent(By.cssSelector(searchButton))) {
//            driver.findElement(By.cssSelector(searchButton)).click();
//        }
//        driver.findElement(By.id(selectSearch)).click();
//        String searchWord = "абрвал";
//        driver.findElement(By.id(selectSearch)).sendKeys("абрвал");
//        driver.findElement(By.cssSelector(selectFind)).click();
//        driver.get(baseUrl + urlHome + urlSearch + searchWord);
//        int expected=0;
//        assertEquals(expected, countOfElement(By.cssSelector(classOfCss)));
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.close();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private int countOfElement(By by) {
        int i = driver.findElements(by).size();
        return i;
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void testMain(String searchWord, int expected) {
        driver.get(baseUrl + urlHome);
        if (isElementPresent(By.cssSelector(searchButton))) {
            driver.findElement(By.cssSelector(searchButton)).click();
        }
        driver.findElement(By.id(selectSearch)).click();
        driver.findElement(By.id(selectSearch)).sendKeys(searchWord);
        driver.findElement(By.cssSelector(selectFind)).click();
        driver.get(baseUrl + urlHome + urlSearch + searchWord);
        assertEquals(expected, countOfElement(By.cssSelector(classOfCss)));

    }


    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
