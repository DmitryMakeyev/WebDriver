import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverAlphaIndustriesTest{
    private WebDriver driver;
    private Wait<WebDriver> wait;

    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 5).withMessage("Element was not found");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void AddItemsToCart() throws InterruptedException {
        driver.get("https://www.alphaindustries.com/");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("geCloseBtn"))));
        WebElement closeAdvButton = driver.findElement(By.id("geCloseBtn"));
        closeAdvButton.click();
        WebElement searchButton = driver.findElement(By.xpath("//*[@id='alpha-login-bar-right']/div[3]/div/a"));
        searchButton.click();
        WebElement searchInput = driver.findElement(By.id("bc-sf-search-box-0"));
        searchInput.sendKeys("mjm21000c1");
        searchInput.sendKeys(Keys.ENTER);
        WebElement product = driver.findElement(By.xpath("//*[@id=\"collectionsallproductsmjm21000c1-ma-1-bomber-jacket-heritage-m-1\"]/div/div[1]/a"));
        product.click();
        WebElement addToCartButton = driver.findElement(By.id("AddToCart-product-template"));
        addToCartButton.click();
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//*[@id='shopify-section-cart-template']/div/form/table/tbody/tr"));
        Assert.assertTrue(itemsInCart.size()>0,"The item is not in the cart");
    }

    @AfterMethod(alwaysRun = true)
    public void BrowserTearDown() {
        driver.quit();
    }
}