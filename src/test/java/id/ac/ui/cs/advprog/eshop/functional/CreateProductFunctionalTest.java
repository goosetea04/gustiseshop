package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);

    }

    @Test
    void createProductAndVerifyInList(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        //Fill in the product details
        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.sendKeys("Stuff");

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.sendKeys("2");

        // Submit the form
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Navigate to the product list page
        driver.get(baseUrl + "/product/list");

        // Verify that the new product is in the list
        WebElement productList = driver.findElement(By.tagName("body"));
        assertTrue(productList.getText().contains("Stuff"));
        assertTrue(productList.getText().contains("2"));
    }
}
