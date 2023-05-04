import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ProductShoppingFlow {
    WebDriver driver;

    @BeforeClass
    void openBrowser() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test(priority = 1)
    void selectProduct(){
        driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div[1]/div[2]/ul/li[1]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[1]/a/img")).click();
        driver.findElement(By.id("add-to-cart-button-3")).click();

    }
    @Test(priority = 2)
    void shoppingCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"topcartlink\"]/a/span[1]")).click();
     //validation of product in cart
        WebElement product=driver.findElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[3]/a"));
        Assert.assertTrue(product.isDisplayed(),"Product is not present");
     //changing quantity and updating cart
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys("3");
        driver.findElement(By.id("updatecart")).click();

        driver.findElement(By.id("termsofservice")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    }
    @Test(priority = 3)
    void guestCheckOut(){
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("abcd");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("efgh");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("abcd@gmail.com");
        driver.findElement(By.id("BillingNewAddress_CountryId")).sendKeys("India");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Mumbai");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("12,abc street");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("123456");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("12345698745");
        driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]")).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

       // Alert alert = driver.switchTo().alert();
        //alert.accept();
    }
    @Test(priority = 4)
    void shippingOption(){
        driver.findElement(By.xpath("//*[@id=\"shippingoption_2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
    }
    @Test(priority = 5)
    void paymentOption(){
        driver.findElement(By.xpath("//*[@id=\"paymentmethod_1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/button")).click();

        driver.findElement(By.id("CreditCardType")).sendKeys("Visa");
        driver.findElement(By.id("CardholderName")).sendKeys("abc");
        driver.findElement(By.id("CardNumber")).sendKeys("00000000");

        driver.findElement(By.id("ExpireMonth")).sendKeys("05");
        driver.findElement(By.id("ExpireYear")).sendKeys("2025");

        driver.findElement(By.xpath("//*[@id=\"CardCode\"]")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id=\"payment-info-buttons-container\"]/button")).click();
    }
    @Test(priority = 6)
    void confirmOrder(){
        driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button")).click();
        System.out.println("Order is placed");
    }
    @AfterClass
    void closeBrowser(){
        driver.close();

    }
}
