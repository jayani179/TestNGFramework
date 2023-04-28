import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestngRegistration {
    WebDriver driver;
    @Test(priority = 1)
    void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

    }
    @Test(priority = 2)
    void register(){
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")).click();
        driver.findElement(By.id("gender-female")).click();
        driver.findElement(By.id("FirstName")).sendKeys("jayani");
        driver.findElement(By.id("LastName")).sendKeys("shah");
        driver.findElement(By.name("DateOfBirthDay")).sendKeys("18");
        driver.findElement(By.name("DateOfBirthMonth")).sendKeys("April");
        driver.findElement(By.name("DateOfBirthYear")).sendKeys("1985");
        driver.findElement(By.name("Email")).sendKeys("jaini1.shah@gmail.com");
        driver.findElement(By.id("Company")).sendKeys("ABC Ltd.");
        driver.findElement(By.id("Newsletter")).click();
        driver.findElement(By.id("Password")).sendKeys("jaini1804");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("jaini1804");
        driver.findElement(By.xpath("//*[@id=\"register-button\"]")).click();
   }

   @Test(priority = 3)
   void logIn(){
        driver.findElement(By.xpath("//a[@href=\"/login?returnUrl=%2F\"]")).click();
        driver.findElement(By.className("email")).sendKeys("jaini1.shah@gmail.com");
        driver.findElement(By.className("password")).sendKeys("jaini1804");

        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button")).click();


   }
    @Test(priority = 4)
    void logOut() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.titleContains("nopCommerce demo store"));
        //Thread.sleep(2000);
        driver.findElement(By.className("ico-logout")).click();
        String expected_Url="https://demo.nopcommerce.com/";
        String actual_Url=driver.getCurrentUrl();
        System.out.println(actual_Url);
        if(expected_Url.equals(actual_Url)){
            System.out.println("Homepage loaded successfully");
        }
        else {
            System.out.println("Homepage loading unsuccessful");
        }


    }
    @Test(priority = 5)
    void closeBrowser(){
        driver.close();

    }

    }
