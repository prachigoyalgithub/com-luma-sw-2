package testsuite;

import browsefactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterTest extends BaseTest {


        public static void main(String[] args) {
            // Set the path to your ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

            // Create a new instance of the ChromeDriver
            WebDriver driver = new ChromeDriver();

            try {
                // Test Scenario 1: User should login successfully with valid credentials
                driver.get("your_sign_in_page_url");
                driver.findElement(By.linkText("Sign In")).click();
                // Fill in login form with valid credentials
                driver.findElement(By.name("email")).sendKeys("valid_email@example.com");
                driver.findElement(By.name("password")).sendKeys("valid_password");
                driver.findElement(By.cssSelector(".action.login.primary")).click();
                // Verify welcome text
                String welcomeText = "Welcome";
                WebElement welcomeElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + welcomeText + "')]")));
                assert welcomeElement.isDisplayed() : "Login Failed: Welcome text not found.";

                // Test Scenario 2: Verify the error message with invalid credentials
                driver.findElement(By.linkText("Sign In")).click();
                // Fill in login form with invalid credentials
                driver.findElement(By.name("email")).sendKeys("invalid_email@example.com");
                driver.findElement(By.name("password")).sendKeys("invalid_password");
                driver.findElement(By.cssSelector(".action.login.primary")).click();
                // Verify error message
                String errorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
                WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + errorMessage + "')]")));
                assert errorElement.isDisplayed() : "Error Message Verification Failed: Error message not found.";

                // Test Scenario 3: User should log out successfully
                driver.findElement(By.linkText("Sign In")).click();
                // Fill in login form with valid credentials
                driver.findElement(By.name("email")).sendKeys("valid_email@example.com");
                driver.findElement(By.name("password")).sendKeys("valid_password");
                driver.findElement(By.cssSelector(".action.login.primary")).click();
                // Verify welcome text
                welcomeElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + welcomeText + "')]")));
                assert welcomeElement.isDisplayed() : "Login Failed: Welcome text not found.";

                // Logout
                driver.findElement(By.cssSelector(".account-cart-wrapper > a")).click(); // Click on down arrow near Welcome
                driver.findElement(By.linkText("Sign Out")).click();

                // Verify sign out message
                String signOutMessage = "You are signed out";
                WebElement signOutElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + signOutMessage + "')]")));
                assert signOutElement.isDisplayed() : "Sign Out Failed: Sign out message not found.";

            } finally {
                // Close the browser
                driver.quit();
            }
        }
    }


