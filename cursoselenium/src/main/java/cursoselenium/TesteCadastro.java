package cursoselenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializaDriver() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(500, 300));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
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
		
		Assert.assertTrue(page.getCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.getNomeCadastro().endsWith("Glaiton"));
		Assert.assertTrue(page.getSobrenomeCadastro().endsWith("Santos"));
		Assert.assertEquals("Sexo: Masculino", page.getSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.getComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.getEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Futebol", page.getEsportesCadastro());
	}
	
	@Test
	public void validarNomeObrigatorio() {
		page.clickCadastrar();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
	}
	
	@Test
	public void validarSobrenomeObrigatorio() {
		page.setName("Glaiton");
		page.clickCadastrar();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}
	
	@Test
	public void validaSexoObrigatorio() {
		
		page.setName("Glaiton");
		page.setMiddleName("Santos");
		page.clickCadastrar();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
	}
	
	@Test
	public void validaComidaObrigatorio() {
		
		page.setName("Glaiton");
		page.setMiddleName("Santos");
		page.setSexMale();
		page.setFavoriteFoodPizza();
		page.setFavoriteFoodVegetariano();
		page.clickCadastrar();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}
	
	@Test
	public void validaEsporteIndeciso() {
		
		page.setName("Glaiton");
		page.setMiddleName("Santos");
		page.setSexMale();
		page.setFavoriteFoodPizza();
		page.setSports("Natacao", "O que eh esporte?");
		page.clickCadastrar();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		
	}
	
}
