package cursoselenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteCadastro {
	
	private WebDriver driver;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializaDriver() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(500, 300));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void fechaBrowser() {
		
		driver.quit();
		
	}

	@Test
	public void testaCadastroCompleto() {
		
		page.setName("Glaiton");
		page.setMiddleName("Santos");
		page.setSexMale();
		page.setFavoriteFoodPizza();
		page.setScholarityMasters("Mestrado");
		page.setSports("Futebol");
		page.clickCadastrar();
		
		Assert.assertEquals("Cadastrado!",page.getCadastro());
		Assert.assertEquals("Glaiton", page.getNomeCadastro());
		Assert.assertEquals("Santos", page.getSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.getSexoCadastro());
		Assert.assertEquals("Pizza", page.getComidaCadastro());
		Assert.assertEquals("mestrado", page.getEscolaridadeCadastro());
		Assert.assertEquals("Futebol", page.getEsportesCadastro());
	}
	
}