package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\Dean\\Kuliah\\Semester 6\\PPL\\Praktikum\\Minggu14\\edgedriver_win64\\msedgedriver.exe"); // sesuaikan path
        driver = new EdgeDriver();
    }

    @Given("I open the login page")
    public void i_open_the_login_page() {
        driver.get("http://ptbsp.ddns.net:6882/login"); // Ganti dengan URL login asli
    }

    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        driver.findElement(By.name("username")).sendKeys("bendahara");
        driver.findElement(By.name("password")).sendKeys("admin123");
    }

    @When("I enter invalid username or password")
    public void i_enter_invalid_username_or_password() {
        driver.findElement(By.name("username")).sendKeys("wronguser");
        driver.findElement(By.name("password")).sendKeys("wrongpass");
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        driver.findElement(By.xpath("//button[@type='submit' and contains(text(),'Login')]")).click();
    }

    @Then("I should see the homepage")
    public void i_should_see_the_homepage() {
        // Wait up to 10 seconds for the text to appear on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Dasbor - Bendahara"));

        // Assert that the text is present
        assertTrue(driver.getPageSource().contains("Dasbor - Bendahara"));
    }

    @Then("I should see the alert message")
    public void i_should_see_the_alert_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the specific <p> element containing the error message
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"), "Incorrect username or password, please try again!"));

        // Assert that the error message is present
        assertTrue(driver.getPageSource().contains("Incorrect username or password, please try again!"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}