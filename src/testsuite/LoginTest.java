package testsuite;

import browsefactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest extends BaseTest {



        public static void main(String[] args) {
            // Set the path to your ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

            // Create a new instance of the ChromeDriver
            WebDriver driver = new ChromeDriver();

            try {
                // Test Scenario 1: Verify That SignIn Page Displays
                driver.get("your_sign_in_page_url");
                driver.findElement(By.linkText("Create an Account")).click();
                String expectedText = "Create New Customer Account";
                WebElement createAccountTextElement = driver.findElement(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
                assert createAccountTextElement.isDisplayed() : "Verification Failed: Text not found on the page.";

                // Test Scenario 2: User Should Register Account Successfully
                driver.findElement(By.linkText("Create an Account")).click();
                // Fill in registration form
                driver.findElement(By.name("firstname")).sendKeys("John");
                driver.findElement(By.name("lastname")).sendKeys("Doe");
                driver.findElement(By.name("newsletter")).click();
                driver.findElement(By.name("email")).sendKeys("john.doe@example.com");
                driver.findElement(By.name("password")).sendKeys("password123");
                driver.findElement(By.name("confirmation")).sendKeys("password123");
                driver.findElement(By.name("register")).click();

                // Verify success message
                String successMessage = "Thank you for registering with Main Website Store.";
                WebElement successMessageElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + successMessage + "')]")));
                assert successMessageElement.isDisplayed() : "Registration Failed: Success message not found.";

                // Sign out
                driver.findElement(By.cssSelector(".account-cart-wrapper > a")).click(); // Click on down arrow near Welcome
                driver.findElement(By.linkText("Sign Out")).click();

                // Verify sign out message
                String signOutMessage = "You are signed out";
                WebElement signOutMessageElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + signOutMessage + "')]")));
                assert signOutMessageElement.isDisplayed() : "Sign Out Failed: Sign out message not found.";

            } finally {
                // Close the browser
                driver.quit();
            }
        }
    }


