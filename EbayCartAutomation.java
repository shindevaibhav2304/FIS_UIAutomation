import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayCartAutomation {

    public static void main(String[] args) {
    System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver");
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--disable-popup-blocking");

        
        WebDriver driver = new ChromeDriver(options);

        try {
            
            driver.get("https://www.ebay.com");
            WebElement searchBox = driver.findElement(By.id("gh-ac"));
            searchBox.sendKeys("book");
            WebElement searchButton = driver.findElement(By.id("gh-btn"));
            searchButton.click();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-item")));

            WebElement firstBook = driver.findElement(By.cssSelector(".s-item .s-item__link"));
            firstBook.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("isCartBtn_btn")));
            
            WebElement addToCartButton = driver.findElement(By.id("isCartBtn_btn"));
            addToCartButton.click();
          
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gh-cart-n"));
          
            WebElement cartIcon = driver.findElement(By.id("gh-cart-n"));
            String cartCountText = cartIcon.getText();
           
            if (cartCountText.equals("1")) {
                System.out.println("Test Passed: Item added to the cart successfully.");
            } else {
                System.out.println("Test Failed: Cart count is not correct. Cart shows " + cartCountText);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test failed due to exception: " + e.getMessage());
        } finally {            
            driver.quit();
        }
    }
}
