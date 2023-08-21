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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaggerWebUITests {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    private int MAX_WAITING_TIME_MS = 10000;

    @Before
    public void setUp() {
        //System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver_mac_arm");
        // run the firefox browser in headless mode
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.get("http://localhost:" + port + "/swagger-ui/index.html");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Test if Swagger's Documentation is available.")
    public void documentationIsVisibleTest() throws InterruptedException {

        // Wait for page to load
        int currentWaitingTime = 0;
        while (driver.findElements(By.cssSelector(".title")).size() < 1){
            System.out.println("Page has not loaded yet. Will wait 1 second and try again.");
            Thread.sleep(1000);
            currentWaitingTime += 1000;
            if (currentWaitingTime > MAX_WAITING_TIME_MS) break;
        }
        assertEquals(
            "Math API",
            driver.findElement(By.cssSelector(".title")).getText().split("\n")[0].trim()
        );
        assertEquals(
            "This API exposes endpoints to manage Math services.",
            driver.findElement(By.cssSelector(".description > .renderedMarkdown > p")).getText()
        );
        assertEquals(
            "prime-rest-controller",
            driver.findElement(By.cssSelector("#operations-tag-prime-rest-controller span")).getText()
        );
        assertEquals(
            "odd-vs-even-rest-controller",
            driver.findElement(By.cssSelector("#operations-tag-odd-vs-even-rest-controller span")).getText()
        );
        assertEquals(
            "Schemas",
            driver.findElement(By.cssSelector(".models-control > span")).getText()
        );


    }


}
