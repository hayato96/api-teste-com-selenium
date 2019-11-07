package cursoselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testaJanelasPopUp {
	
private WebDriver driver;
	
	@Before
	public void inicializaDriver() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(500, 300));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
	}
	
	@After
	public void fechaBrowser() {
		
		driver.quit();
		
	}

	@Test
	public void deveInteragirComJanelasPopup() {
		
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("testei");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.id("textarea")).sendKeys("testei mesmo?");
		
}

}
