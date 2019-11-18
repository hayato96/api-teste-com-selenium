package cursoselenium;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void escreve(String id_campo, String texto) {
		
		driver.findElement(By.id(id_campo)).sendKeys(texto);
		
	}
	
	public String obterValorCampo(String id_campo) {
		
		return driver.findElement(By.id(id_campo)).getAttribute("value");
		
	}
	public void selecionaRadioButton(String id) {
		
		driver.findElement(By.id(id)).click();
	}
	
	public boolean checaRadioButtonMarcado(String id) {
		
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionaCombo(String id_combo, String valor) {
		WebElement element = driver.findElement(By.id(id_combo));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id_combo) {
		
		WebElement element = driver.findElement(By.id(id_combo));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void deveClicarEmBotoes(String id) {
		
		driver.findElement(By.id(id)).click();		
	}
	
	public void deveClicarEmLinks(String id) {
		
	    driver.findElement(By.linkText(id)).click();

	}
	
	public String obterTextoNaPagina(By by) {
		return driver.findElement(by).getText();
		
	}
	
	public String obterTexto(String id) {
		return obterTextoNaPagina(By.id(id));
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	/******************************TABELA*****************************/
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTab ) {
		
		WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tabelaUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for( int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String colunaBusca, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for( int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(colunaBusca)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
	
}
