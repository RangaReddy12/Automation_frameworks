package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDriven {
String inputpath="C:\\Selenium11oclock\\Selenium_Frameworks\\TestInput\\LoginData.xlsx";
String outputpath="C:\\Selenium11oclock\\Selenium_Frameworks\\TestOutput\\Results.xlsx";
WebDriver driver;
ExtentReports report;
ExtentTest test;
File screen;
@BeforeTest
public void setUp()
{
report= new ExtentReports("./ExtentReports/datadriven.html");
System.setProperty("webdriver.chrome.driver", "C:\\Selenium11oclock\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
driver=new ChromeDriver();
}
@Test
public void verifyLogin()throws Throwable
{
	//create reference object to access xl methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//row count a sheet
	int rc=xl.rowCount("Login");
	//count no of columns in a row
	int cc=xl.colCount("Login");
	Reporter.log("No of rows are::"+rc+"  "+"No of columns are::"+cc,true);
	for(int i=1;i<=rc;i++)
	{
	//start test case
	test=report.startTest("Verify Login");
	test.assignAuthor("Ranga");
	test.assignCategory("Data Driven Framework");
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	//read username and password
	String username=xl.getCellData("Login", i, 0);
	String password=xl.getCellData("Login", i, 1);
	driver.findElement(By.name("txtUsername")).sendKeys(username);
	driver.findElement(By.name("txtPassword")).sendKeys(password);
	driver.findElement(By.name("Submit")).click();
	if(driver.getCurrentUrl().contains("dash"))
	{
		Reporter.log("Login success",true);
		//write into results cell
		xl.setCellData("Login", i, 2, "Login Success", outputpath);
		//write as pass into status
		xl.setCellData("Login", i, 3, "Pass", outputpath);
		test.log(LogStatus.PASS, "Login Success");
	}
	else
	{
//take screen shot for fail itertion
screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("./screens/iterations/"+i+"loginpage.png"));
	//get error message
String erormessage=driver.findElement(By.id("spanMessage")).getText();
Reporter.log(erormessage,true);
xl.setCellData("Login", i, 2, erormessage, outputpath);
xl.setCellData("Login", i, 3, "Fail", outputpath);
test.log(LogStatus.FAIL, erormessage);
	}
	report.endTest(test);
	report.flush();
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}










