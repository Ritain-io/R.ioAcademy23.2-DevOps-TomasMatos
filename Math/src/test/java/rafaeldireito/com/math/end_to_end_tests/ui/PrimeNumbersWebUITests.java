package rafaeldireito.com.math.end_to_end_tests.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import rafaeldireito.com.math.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumbersWebUITests {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    @Before
    public void setUp() {
        //System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver_mac_arm");
        // run the firefox browser in headless mode
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.get("http://localhost:" + port + "/service/prime");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Test if the WebUI shows the desired base information.")
    public void baseInformation() {
        // Validate base information
        assertEquals("Math Application", driver.getTitle());
        assertEquals("Prime", driver.findElement(By.id("thumbnailTitle")).getText());
        assertEquals("Is this number prime?", driver.findElement(By.id("inputTitle")).getText());
    }

    @Test
    @DisplayName("Test if the WebUI correctly identifies all prime numbers until 1000 and displays the desired UI elements.")
    public void primeNumbersUntil1000WebUI(){
        // Let's select only 50 elements, so the test doesn't take ages to be performed
        List<Integer> shuffledPrimeNumbersUntil1000List = new ArrayList<>(Constants.primeNumbersUntil1000List);
        Collections.shuffle(shuffledPrimeNumbersUntil1000List);
        shuffledPrimeNumbersUntil1000List = shuffledPrimeNumbersUntil1000List.subList(0, 50);

        // Validate the prime service outputs
        for (int number: shuffledPrimeNumbersUntil1000List){
            // first, lets clear the input
            driver.findElement(By.id("inputNumber")).clear();
            // submit a number
            driver.findElement(By.id("inputNumber")).sendKeys(String.valueOf(number));
            driver.findElement(By.id("submissionButton")).click();
            // check the output
            // success div should be visible while the errors div should not
            assertTrue(driver.findElement(By.id("result_success_div")).isDisplayed());
            assertFalse(driver.findElement(By.id("result_error_div")).isDisplayed());
            // check if the message displayed is the one we wish for
            assertEquals(
                    "Result:",
                    driver.findElement(By.cssSelector("#result_success_div strong")).getText()
            );
            assertEquals(
                    number + " IS a prime number!",
                    driver.findElement(By.cssSelector("#result_success_div span")).getText()
            );

        }
    }

    @Test
    @DisplayName("Test if the WebUI correctly identifies all non-prime numbers until 1000 and displays the desired UI elements.")
    public void nonPrimeNumbersUntil1000WebUI(){
        // Let's select only 50 elements, so the test doesn't take ages to be performed
        List<Integer> shuffledNonPrimeNumbersUntil1000List = new ArrayList<>(Constants.nonPrimeNumbersUntil1000List);
        Collections.shuffle(shuffledNonPrimeNumbersUntil1000List);
        shuffledNonPrimeNumbersUntil1000List = shuffledNonPrimeNumbersUntil1000List.subList(0, 50);

        // Validate the prime service outputs
        for (int number: shuffledNonPrimeNumbersUntil1000List){
            // first, lets clear the input
            driver.findElement(By.id("inputNumber")).clear();
            // submit a number
            driver.findElement(By.id("inputNumber")).sendKeys(String.valueOf(number));
            driver.findElement(By.id("submissionButton")).click();
            // check the output
            // success div should be visible while the errors div should not
            assertTrue(driver.findElement(By.id("result_success_div")).isDisplayed());
            assertFalse(driver.findElement(By.id("result_error_div")).isDisplayed());
            // check if the message displayed is the one we wish for
            assertEquals(
                    "Result:",
                    driver.findElement(By.cssSelector("#result_success_div strong")).getText()
            );
            assertEquals(
                    number + " is NOT a prime number!",
                    driver.findElement(By.cssSelector("#result_success_div span")).getText()
            );

        }
    }


    @Test
    @DisplayName("Test if the WebUI shows an error when checking if a number > 1000 is prime.")
    public void primeNumbersOver1000RestWebUI(){
        // Validate the prime service outputs
        for (int number: Constants.randomNumberBiggerThan1000){
            // first, lets clear the input
            driver.findElement(By.id("inputNumber")).clear();
            // submit a number
            driver.findElement(By.id("inputNumber")).sendKeys(String.valueOf(number));
            driver.findElement(By.id("submissionButton")).click();
            // check the output
            // success div should be visible while the errors div should not
            assertTrue(driver.findElement(By.id("result_error_div")).isDisplayed());
            assertFalse(driver.findElement(By.id("result_success_div")).isDisplayed());
            // check if the message displayed is the one we wish for
            assertEquals(
                    "Error:",
                    driver.findElement(By.cssSelector("#result_error_div strong")).getText()
            );
            assertTrue(
                driver.findElement(By.cssSelector("#result_error_div span")).getText()
                .contains("We have no computing resources to know if " + number + " is a prime number!")
            );
            assertTrue(
                    driver.findElement(By.cssSelector("#result_error_div span")).getText()
                            .contains("We can only compute this for numbers <= 1000 !")
            );

        }
    }


}
