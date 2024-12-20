package testClasses;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public static WebDriver driver;
	static Properties pr;

	public static void readProperty() throws IOException {

		pr = new Properties(); // do like how done for driver...
		FileInputStream f = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\Configuration.properties");
		pr.load(f); //

	}

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browservalue) {

		if (browservalue.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browservalue.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		}
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {

		readProperty();
		driver = new ChromeDriver();
		driver.get(pr.getProperty("Base_URL"));
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterMethod
	public void afterMethod() throws IOException {

//		if (itr.getStatus() == ITestResult.FAILURE) {
//
//			ScreenshotClass.takeScreenshot(driver, itr.getName());
//
//		}
		driver.quit();
	}

	@BeforeSuite(alwaysRun = true)
	  public void createReport(final ITestContext testContext) {
			extentReport.ExtentManager.createInstance();
		}

	@AfterSuite
	public void afterSuite() {

		System.out.println("*****Test excecution completed!!!*****");
	}

}
//Reading the data from property file...(src/main/resources)
//created the file configuration.properties with given file and it's name...
//Then created a object for properties as static as no need to create other objects. Also don't need to call with classname as it is inside class and outside method...
//by using fileinputstream getting the property from user dir and given path of file and then load file f.
//then in before method, in place of base url, we are calling pr with the name of property given in file...

//Screenshots
//for taking screenshots as per the test cases failure or pass, ITestResult is an interface that's help us...
