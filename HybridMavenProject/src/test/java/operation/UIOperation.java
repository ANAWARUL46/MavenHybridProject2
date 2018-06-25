package operation;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UIOperation {
	WebDriver driver;
	
	public  UIOperation(WebDriver driver){
		this.driver=driver;
	}
	public void KeyWordPerform(Properties p, String keyword, String objectName, 
			String objectType, String data) throws Exception {
		switch(keyword.toUpperCase()){
		case "GOTOURL":
			driver.get(p.getProperty(data));
			break;
		case "SENDKEYS":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(data);
			break;
		case "CLICK":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			break;
		case "SELECT":
			new Select (driver.findElement(this.getObject(p, objectName, objectType))).selectByVisibleText(data);
			break;
		case "GETTITLE":
			String title=driver.getTitle();
			System.out.println("Title is : "+title);
			break;
		case "GETTEXT":
			String text  =driver.findElement(this.getObject(p, objectName, objectType)).getText();
			System.out.println("Text is : "+text);
			break;
			
		case "GETTYPEDTEXT":
			String TypedText  =driver.findElement(this.getObject(p, objectName, objectType)).getAttribute("value");
			System.out.println("TypedText is : "+TypedText);
			break;
			
		case "GETPOINT":
			 Point p1  =driver.findElement(this.getObject(p, objectName, objectType)).getLocation();
			System.out.println("GetPoint is : "+p1 );
			break;
			
		case "NEWLINE":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(data);
			break;
			
		case "SCREENSHOT":
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("C:\\Shot\\Facebook.jpg"));
			break;
			
		case "GOTOBACKPAGE":
			driver.navigate().back();
			break;
			
		case "GOTOFORWARDPAGE":
			driver.navigate().forward();
			break;
			
		case "PAGEREFRESH":
			driver.navigate().refresh();
			break;
			
		case "ALERTHANDLING":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			Alert alt=driver.switchTo().alert();
			alt.accept();
			break;
			
		case "IMAGEVERIFY":
			Actions act= new Actions (driver);
			act .moveToElement(driver.findElement(this.getObject(p, objectName, objectType))).build().perform();
            break;
            
		case "DRAGANDDROP":
			WebElement src=driver.findElement(this.getObject(p, objectName, objectType));
			WebElement dst=driver.findElement(this.getObject(p, objectName, objectType));
			Actions act1=new Actions (driver);
			act1.dragAndDrop(src, dst).build().perform();
			break;
			
		case"DOUBLECLICK":
			WebElement DBC=driver.findElement(this.getObject(p, objectName, objectType));
			Actions act2 =new Actions(driver);
			act2.doubleClick(DBC).build().perform();
			break;
			
		case "THREAD":
			Thread.sleep(3000);
			break;
			
		case "CLOSE":
			driver.close();
			break;
			
		case "QUIT":
			driver.quit();
			break;
			
		}
	
	}
	public By getObject(Properties p, String objectName, String objectType) throws Exception {
		if(objectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CLASS")) {
			return By.className(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("LINKTEXT")) {
			return By.linkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("PARTIALLINKTEXT")) {
			return By.partialLinkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CSSSELECTOR")) {
			return By.cssSelector(p.getProperty(objectName));
		}
		else {
			throw new Exception("Wrong object type");
		}
	}
	

}
