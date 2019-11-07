package cursoselenium;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setName(String name) {
		dsl.escreve("elementosForm:nome", name);
	}
	
	public void setMiddleName(String middleName) {
		dsl.escreve("elementosForm:sobrenome", middleName);
	}
	
	public void setSexMale() {
		dsl.selecionaRadioButton("elementosForm:sexo:0");
	}
	
	public void setSexFemale() {
		dsl.selecionaRadioButton("elementosForm:sexo:1");
	}
	
	public void setFavoriteFoodPizza() {
		dsl.selecionaRadioButton("elementosForm:comidaFavorita:2");
	}
	public void setFavoriteFoodCarne() {
		dsl.selecionaRadioButton("elementosForm:comidaFavorita:0");
	}
	public void setFavoriteFoodVegetariano() {
		dsl.selecionaRadioButton("elementosForm:comidaFavorita:3");
	}
	public void setFavoriteFoodFrango() {
		dsl.selecionaRadioButton("elementosForm:comidaFavorita:1");
	}
	public void setScholarityMasters(String choice) {
		dsl.selecionaCombo("elementosForm:escolaridade", choice);
	}
	public void setSports(String... valores) {
		for (String valor: valores )
		dsl.selecionaCombo("elementosForm:esportes", valor);	
	}
	public void clickCadastrar() {
		dsl.deveClicarEmBotoes("elementosForm:cadastrar");
	}
	public String getCadastro() { 
		return dsl.obterTexto("resultado");
	}
	public String getNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	public String getSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	public String getSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	public String getComidaCadastro() {
		return dsl.obterTexto("descComida");
	}
	public String getEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	public String getEsportesCadastro() {
		return dsl.obterTexto("descEsportes");
	}
	
}
