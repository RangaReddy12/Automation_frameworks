package DriverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.BranchUpdatePage;
import CommonFunLibrary.BranchesPage;
import CommonFunLibrary.LoginPage;
import CommonFunLibrary.LogoutPage;
import CommonFunLibrary.NewBranchPage;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScript extends PBConstant 
{
String inputpath="C:\\Selenium11oclock\\Selenium_Frameworks\\TestInput\\Controller.xlsx";
String outputpath="C:\\Selenium11oclock\\Selenium_Frameworks\\TestOutput\\keyword.xlsx";
String TCSheet="TestCases";
String TSSheet="TestSteps";
ExtentReports report;
ExtentTest test;

@Test
public void Primus()throws Throwable
{
	//generate report
	report=new ExtentReports("./ExtentReports/keyword.html");
	//call all page classes
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	BranchesPage navigatebranch=PageFactory.initElements(driver, BranchesPage.class);
	NewBranchPage branchc=PageFactory.initElements(driver, NewBranchPage.class);
	BranchUpdatePage branchu=PageFactory.initElements(driver, BranchUpdatePage.class);
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	boolean res=false;
	String tcres=null;
	//access excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows in TCsheet
	int TCcount=xl.rowCount(TCSheet);
	//count no of rows in TSSheet
	int TScount=xl.rowCount(TSSheet);
	for(int i=1;i<=TCcount;i++)
	{
		//start test case here
		test=report.startTest(TCSheet);
		//read execute column from TCSheet
		String execute=xl.getCellData(TCSheet, i, 2);
		if(execute.equalsIgnoreCase("Y"))
		{
		////read tcid column from TCSheet
			String tcid=xl.getCellData(TCSheet, i, 0);
			for(int j=1;j<=TScount;j++)
			{
				//read description column
			String Description=xl.getCellData(TSSheet, j, 2);	
				//read tsid column from TSsheet
				String tsid=xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					//read keyword column from TSsheet
					String keyword=xl.getCellData(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
					res=login.verifyLogin("Admin", "Admin");
					test.log(LogStatus.INFO,Description);
					}
					else if(keyword.equalsIgnoreCase("NewBranchCreation"))
					{
						navigatebranch.VerifyBranches();
						res=branchc.VerifyBranchCreation("Kadiri", "Ananatapur", "Kadiri2", "madanapalli", "Kadiri", "12345", 1, 1, 1);
						test.log(LogStatus.INFO,Description);
					}
					else if(keyword.equalsIgnoreCase("UpdateBranch"))
					{
						navigatebranch.VerifyBranches();
					res=branchu.verifyBranchUpdate("Ameerpet", "Hyderabad", "34567");
					test.log(LogStatus.INFO,Description);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res=logout.verifyLogout();
						test.log(LogStatus.INFO,Description);
					}
					String tsres="";
					if(res)
					{
						//write as pass into results column
						tsres="Pass";
					xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					test.log(LogStatus.PASS, Description);
					}
					else
					{
						//write as fail into results column
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						test.log(LogStatus.FAIL, Description);
					}
					if(!tsres.equalsIgnoreCase("Fail"))
					{
						tcres=tsres;
					}
				}
				report.endTest(test);
				report.flush();
			}
			//write into TCsheet
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//write as not Executed in results column
			xl.setCellData(TCSheet, i, 3, "Not Executed", outputpath);
		}
	}
}
}

















