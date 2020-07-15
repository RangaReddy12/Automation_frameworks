package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class NewBranchPage {
WebDriver driver;
public NewBranchPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//input[@id='BtnNewBR']")
WebElement clickBranch;
@FindBy(xpath="//input[@id='txtbName']")
WebElement enterbname;
@FindBy(xpath="//input[@id='txtAdd1']")
WebElement enteradd1;
@FindBy(xpath="//input[@id='Txtadd2']")
WebElement enteradd2;
@FindBy(xpath="//input[@id='txtadd3']")
WebElement enteradd3;
@FindBy(xpath="//input[@id='txtArea']")
WebElement enterarea;
@FindBy(xpath="//input[@id='txtZip']")
WebElement enterzcode;
@FindBy(xpath="//select[@id='lst_counrtyU']")
WebElement selectcountry;
@FindBy(xpath="//select[@id='lst_stateI']")
WebElement selectstate;
@FindBy(xpath="//select[@id='lst_cityI']")
WebElement selectcity;
@FindBy(xpath="//input[@id='btn_insert']")
WebElement clicksubmit;
public boolean VerifyBranchCreation(String bname,String add1,String add2,String add3,
		String area,String zcode,int country,int state,int city)throws Throwable
{
	this.clickBranch.click();
	Thread.sleep(5000);
	this.enterbname.sendKeys(bname);
	this.enteradd1.sendKeys(add1);
	this.enteradd2.sendKeys(add2);
	this.enteradd3.sendKeys(add3);
	this.enterarea.sendKeys(area);
	this.enterzcode.sendKeys(zcode);
	new Select(this.selectcountry).selectByIndex(country);
	new Select(this.selectstate).selectByIndex(state);
	new Select(this.selectcity).selectByIndex(city);
	this.clicksubmit.click();
	Thread.sleep(5000);
	//get alert text
	String alertmessage=driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String expected="new Branch with";
	if(alertmessage.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Branch Created Success",true);
		return true;
	}
	else
	{
		Reporter.log("Branch Created Fail",true);
		return false;
	}
}
}















