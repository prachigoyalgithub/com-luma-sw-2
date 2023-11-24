package testsuite;

import browsefactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SaleTest extends BaseTest {

        public static void main(String[] args) {
            // Set the path to your ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

            // Create a new instance of the ChromeDriver
            WebDriver driver = new ChromeDriver();

            try {
                // Navigate to the Women's Jackets Page
                driver.get("your_website_url");

                // Click on 'Sale' Menu tab
                driver.findElement(By.linkText("Sale")).click();

                // Click on 'Jackets' link under WOMEN’S DEAL Category
                driver.findElement(By.xpath("//a[text()='Jackets']")).click();

                // Verify the text 'Jackets' is displayed
                String expectedHeaderText = "Jackets";
                WebElement headerElement = driver.findElement(By.xpath("//h1[contains(text(), '" + expectedHeaderText + "')]"));
                assert headerElement.isDisplayed() : "Verification Failed: Header text not found.";

                // Count the total items displayed on the page
                List<WebElement> items = driver.findElements(By.className("product-item"));

                // Print the name of all items into the console
                System.out.println("Items on the Women's Jackets Page:");
                for (WebElement item : items) {
                    System.out.println(item.findElement(By.className("product-item-link")).getText());
                }

                // Verify total 12 items displayed on the page
                int expectedItemCount = 12;
                assert items.size() == expectedItemCount : "Verification Failed: Expected " + expectedItemCount + " items, but found " + items.size();

                System.out.println("Verification Passed: Total 12 Items displayed on the Women's Jackets Page.");

            } finally {
                // Close the browser
                driver.quit();
            }
        }
    }


