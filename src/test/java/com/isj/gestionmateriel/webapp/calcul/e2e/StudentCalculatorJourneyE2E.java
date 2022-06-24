package com.isj.gestionmateriel.webapp.calcul.e2e;

import com.isj.gestionmateriel.webapp.calcul.e2e.page.CalculatorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentCalculatorJourneyE2E {

	@LocalServerPort
	private Integer port;
	private WebDriver webDriver = null;
	private String baseUrl;

	@BeforeAll
	public static void setUpFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void setUpWebDriver() {
		webDriver = new FirefoxDriver();
		baseUrl = "http://localhost:" + port + "/calculator";
	}

	@AfterEach
	public void quitWebDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	@Test
	public void aStudentUsesTheCalculatorToMultiplyTwoBySixteen() {

		// GIVEN
		webDriver.get(baseUrl);
		final WebElement leftField = webDriver.findElement(By.id("left"));
		final WebElement typeDropdown = webDriver.findElement(By.id("type"));
		final WebElement rightField = webDriver.findElement(By.id("right"));
		final WebElement submitButton = webDriver.findElement(By.id("submit"));

		// WHEN
		leftField.sendKeys("2");
		typeDropdown.sendKeys("x");
		rightField.sendKeys("16");
		submitButton.click();

		// THEN
		final WebDriverWait waiter = new WebDriverWait(webDriver, 5);
		final WebElement solutionElement = waiter.until(
				ExpectedConditions.presenceOfElementLocated(By.id("solution")));
		final String solution = solutionElement.getText();
		assertThat(solution).isEqualTo("32"); // 2 x 16
	}

	@Test
	public void aStudentUsesTheCalculatorToAddTwoToSixteen() throws InterruptedException {
		// GIVEN
		webDriver.get(baseUrl);
		final CalculatorPage calculatorPage = new CalculatorPage(webDriver);

		// WHEN
		final String solution = calculatorPage.add("2", "16");

		// THEN
		assertThat(solution).isEqualTo("18"); // 2 + 16
	}
}

