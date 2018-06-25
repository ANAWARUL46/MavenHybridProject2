package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import operation.ReadObject;
import operation.UIOperation;

public class HybridTestCases {
WebDriver driver;
	
	@Test(dataProvider="HybridData")
	public void UnderTest(String testCaseId, String testCaseName, String keyWord, 
			String objectName, String objectType, String data) throws Exception {
		
		if(testCaseName!=null && testCaseName.length()!=0) {
			driver=new FirefoxDriver();
		}
		
		ReadObject object=new ReadObject();
		Properties allObject=object.getObjectRepository();
		
		UIOperation uio=new UIOperation(driver);
		uio.KeyWordPerform(allObject, keyWord, objectName, objectType, data);
		
		
	}
	@DataProvider(name="HybridData")
    public Object[][] TestNgDataProvide() throws IOException {
    	Object[][] object=null;
    	
    	File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testData", "TestCases.xlsx");
    	FileInputStream fis=new FileInputStream(f);
    	Workbook wb=new XSSFWorkbook(fis);
    	Sheet ws=wb.getSheet("Data1");
    	
    	int rowCount=ws.getLastRowNum()-ws.getFirstRowNum();
    	int colCount=6;
    	object=new Object[rowCount][colCount];
    	for(int i=0;i<rowCount; i=i+1) {
    		Row r=ws.getRow(i+1);
    		for(int j=0; j<r.getLastCellNum(); j=j+1) {
    			object[i][j]=r.getCell(j).toString();
    		}
    	}
    	return object;
    	
    }

}
