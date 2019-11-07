package cursoselenium;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String name;
	@Parameter(value=1)
	public String middleName;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;

	
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
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[] {},"Nome eh obrigatorio" }
		});
		
	}
	
	@Test
	public void deveValidarRegras() {
		
		page.setName(name);
		page.setMiddleName(middleName);
		if(sexo.equals("Masculino")) {
			page.setSexMale();
		}else {
			page.setSexMale();
		}
		if(comidas.contains("Carne")) page.setFavoriteFoodCarne();
		if(comidas.contains("Frango")) page.setFavoriteFoodFrango();
		if(comidas.contains("Pizza")) page.setFavoriteFoodPizza();
		if(comidas.contains("Vegetariano")) page.setFavoriteFoodVegetariano();
		page.setSports(esportes);
		page.clickCadastrar();
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
		
	}

}
