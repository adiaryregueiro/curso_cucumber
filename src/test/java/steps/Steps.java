package steps;

import base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class Steps extends BaseUtil {

    private BaseUtil baseUtil;
    private WebDriver driver;

    public Steps(BaseUtil baseUtil) {
        this.baseUtil = baseUtil;
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Given("Iam in the login page of the Para Bank Application")
    public void iam_in_the_login_page_of_the_para_bank_application() {
        driver.get("http://parabank.parasoft.com/parabank/index.htm");
    }

    @When("I enter valid {string} and {string} with {string}")
    public void i_enter_valid_credencials(String username, String password, String userFullName1) {
        baseUtil.userFullName = userFullName1;

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("username")).submit();
    }
/*
    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_Overview_page() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rightPanel']/div/div/h1")));

        driver.findElement(By.xpath("//*[@id='rightPanel']/div/div/h1")).isDisplayed();
        driver.findElement(By.linkText("Log out")).click();
    }
    */


    /**
     *
     * When after logging the userFullName is displayed and also the Log Out option
     */

    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_Overview_page() throws Exception {
        String actualuserFullName = driver.findElement(By.className("smallText")).getText().toString();

        assertTrue(actualuserFullName, actualuserFullName.contains(baseUtil.userFullName));
        driver.findElement(By.linkText("Log Out")).click();
    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
