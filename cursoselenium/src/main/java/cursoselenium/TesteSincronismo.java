package cursoselenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteSincronismo {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializaDriver() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(500, 300));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		}
	
	@After
	public void fechaBrowser() {
		
		driver.quit();
		
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.deveClicarEmBotoes("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "deu certo");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() {
	dsl.deveClicarEmBotoes("buttonDelay");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	dsl.escreve("novoCampo", "deu certo");
	}
}
