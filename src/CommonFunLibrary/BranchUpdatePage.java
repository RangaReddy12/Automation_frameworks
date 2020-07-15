package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdatePage {
WebDriver driver;
public BranchUpdatePage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement clickedit;
@FindBy(xpath="//input[@id='txtbnameU']")
WebElement enterbname;
@FindBy(xpath="//input[@id='txtadd1u']")
WebElement enteradd1;
@FindBy(xpath="//input[@id='txtzipu']")
WebElement entezcode;
@FindBy(xpath="//input[@id='btnupdate']")
WebElement clickupdate;
public boolean verifyBranchUpdate(String bname,String add1,String zcode)throws Throwable
{
	this.clickedit.click();
	Thread.sleep(5000);
	this.enterbname.clear();
	this.enterbname.sendKeys(bname);
	this.enteradd1.clear();
	this.enteradd1.sendKeys(add1);
	this.entezcode.clear();
	this.entezcode.sendKeys(zcode);
	this.clickupdate.click();
	Thread.sleep(5000);
	String updatemessage=driver.switchTo().alert().getText();
	System.out.println(updatemessage);
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String expected="Branch updated";
	if(updatemessage.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log(updatemessage,true);
		return true;
	}
	else
	{
		Reporter.log("Not updated",true);
		return false;
	}
}
}













