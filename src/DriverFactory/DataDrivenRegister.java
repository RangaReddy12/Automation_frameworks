package DriverFactory;

import org.openqa.selenium.By;
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

public class DataDrivenRegister {
String inputpath="C:\\Selenium11oclock\\Selenium_Frameworks\\TestInput\\Registerdata.xlsx";
String outputpath="C:\\Selenium11oclock\\Selenium_Frameworks\\TestOutput\\Register.xlsx";
WebDriver driver;
ExtentReports report;
ExtentTest test;
@BeforeTest
public void setUp()
{
	report= new ExtentReports("./ExtentReports/Register.html");
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium11oclock\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
	driver=new ChromeDriver();
}
@Test
public void verifyRegister()throws Throwable
{
	//create reference object to access xl methods
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	//count no of rows
	int rc=xl.rowCount("Register");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		test=report.startTest("Validate Register");
	driver.get("http://newtours.demoaut.com/");
	driver.manage().window().maximize();
	driver.findElement(By.partialLinkText("REGIST")).click();
	//read all cells
	String fname=xl.getCellData("Register", i, 0);
	String lname=xl.getCellData("Register", i, 1);
	String phone=xl.getCellData("Register", i, 2);
	String mail=xl.getCellData("Register", i, 3);
	String add1=xl.getCellData("Register", i, 4);
	String add2=xl.getCellData("Register", i, 5);
	String city=xl.getCellData("Register", i, 6);
	String state=xl.getCellData("Register", i, 7);
	String pcode=xl.getCellData("Register", i, 8);
	String country=xl.getCellData("Register", i, 9);
	String username=xl.getCellData("Register", i, 10);
	String password=xl.getCellData("Register", i, 11);
	String cpassword=xl.getCellData("Register", i, 12);
	driver.findElement(By.name("firstName")).sendKeys(fname);
	driver.findElement(By.name("lastName")).sendKeys(lname);
	driver.findElement(By.name("phone")).sendKeys(phone);
	driver.findElement(By.name("userName")).sendKeys(mail);
	driver.findElement(By.name("address1")).sendKeys(add1);
	driver.findElement(By.name("address2")).sendKeys(add2);
	driver.findElement(By.name("city")).sendKeys(city);
	driver.findElement(By.name("state")).sendKeys(state);
	driver.findElement(By.name("postalCode")).sendKeys(pcode);
	driver.findElement(By.name("country")).sendKeys(country);
	driver.findElement(By.name("email")).sendKeys(username);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("confirmPassword")).sendKeys(cpassword);
	driver.findElement(By.name("register")).click();
	if(password.equals(cpassword))
	{
String message=driver.findElement(By.xpath("//font[contains(text(),'Thank you for registering.')]")).getText();
Reporter.log(message,true);
xl.setCellData("Register", i, 13, message, outputpath);
xl.setCellData("Register", i, 14, "Pass", outputpath);
test.log(LogStatus.PASS, message);
	}
	else
	{
	Reporter.log("Password and cpassword not eqaul",true);
	xl.setCellData("Register", i, 13, "Password and cpassword not eqaul", outputpath);
	xl.setCellData("Register", i, 14, "Fail", outputpath);
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












