package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LogoutPage {
WebDriver driver;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tbody/tr/td[3]/a/img")
WebElement clicklogout;
@FindBy(xpath="//input[@id='login']")
WebElement clicklogin;
public boolean verifyLogout()
{
	this.clicklogout.click();
	if(this.clicklogin.isDisplayed())
	{
		Reporter.log("Logout success",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail",true);
		return false;
	}
}
}
