package cursoselenium;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class testeCampoTreinamento {
	
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
	public void testeTextField() {
		
		dsl.escreve("elementosForm:nome", "teste");
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:nome"));
				
	}
	
	@Test
	public void deveInteragirComTextoArea() {
		
		dsl.escreve("elementosForm:sugestoes", "um texto \n muito curto\n mesmo");
		//driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("um texto \n muito curto\n mesmo", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void checaRadioButton() {
		
		dsl.selecionaRadioButton("elementosForm:sexo:0");
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(dsl.checaRadioButtonMarcado("elementosForm:sexo:0"));		
	}
	
	@Test
	public void checaCheckBoxButton() {
		
		dsl.selecionaRadioButton("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.checaRadioButtonMarcado("elementosForm:comidaFavorita:2"));		
	}
	
	@Test
	public void deveInteragirComOpcoesEscolaridade() {
		
		dsl.selecionaCombo("elementosForm:escolaridade", "Superior");
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		
		dsl.selecionaCombo("elementosForm:escolaridade", "superior");
		Assert.assertEquals("superior", dsl.obterValorCombo("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void verificarValoresComboMultiplo() {
		dsl.selecionaCombo("elementosForm:esportes", "Corrida");
		dsl.selecionaCombo("elementosForm:esportes", "Natacao");
		dsl.selecionaCombo("elementosForm:esportes", "O que eh esporte?");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select (element);
		combo.deselectByVisibleText("Corrida");
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		
		dsl.deveClicarEmBotoes("buttonSimple");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	@Ignore
	public void deveInteragirComLinks() {
		
	    dsl.deveClicarEmBotoes("Voltar");
	    Assert.assertEquals("Voltou", dsl.obterTexto("resultado"));
	    
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		
		
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de treinamento", dsl.obterTextoNaPagina(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTextoNaPagina(By.className("facilAchar")));
		
	}
}
