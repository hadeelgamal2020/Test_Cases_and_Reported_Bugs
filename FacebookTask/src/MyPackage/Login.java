package MyPackage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		// System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/ChromeDriver/chromedriver.exe");

		// Instantiate a ChromeDriver class.
		driver = new ChromeDriver();

		// Launch Website
		driver.navigate().to("https://www.facebook.com/");

		// Maximize the browser
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "testdata")
	public void userLogin(String username, String password) {
		try {

			// Select the unique element
			driver.findElement(By.id("email")).sendKeys(username);
			driver.findElement(By.id("pass")).sendKeys(password);
			driver.findElement(By.id("u_0_b")).click();
			
			driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
			
			if (driver.findElements(By.id("ssrb_rhc_content")).size() != 0)

			{
				System.out.println("Login Scenario Test : Passed");
			} else {

				System.out.println("Login Scenario Test : Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterMethod
	void ProgramTermination() {
		driver.quit();
	}

	@DataProvider(name = "testdata")
	public Object[][] loginTestData() {
		ReadExcelFile configuration = new ReadExcelFile("R:\\Personal\\Instabug Challenge\\Login_Testdata.xlsx");
		int rows = configuration.getRowCount(0);
		Object[][] signin_credentials = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			signin_credentials[i][0] = configuration.getData(0, i, 0);
			signin_credentials[i][1] = configuration.getData(0, i, 1);
		}
		return signin_credentials;
	}

}

