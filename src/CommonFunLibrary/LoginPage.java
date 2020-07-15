package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
}
//Store locators
@FindBy(xpath="//input[@id='txtuId']")
WebElement enterusername;
@FindBy(xpath="//input[@id='txtPword']")
WebElement enterpassword;
@FindBy(xpath="//input[@id='login']")
WebElement clicklogin;
//develope method
public boolean verifyLogin(String username,String password)throws Throwable
{
	this.enterusername.sendKeys(username);
	this.enterpassword.sendKeys(password);
	this.clicklogin.click();
	Thread.sleep(5000);
	String expected="adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login Success",true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail",true);
		return false;
	}
}
}











