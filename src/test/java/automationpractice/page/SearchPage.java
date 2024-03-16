package automationpractice.page;

import org.openqa.selenium.By;

public class SearchPage extends BasePage{
    private static final By SEARCH = By.cssSelector("#searchbox > #search_query_top");
    private static final By BTN_SEARCH = By.cssSelector("#searchbox > button");
    private static final By PRODUTO_RESPONSE = By.cssSelector("#center_column > ul > li:nth-child(1) > div > div > h5 > a");
    private static final By MSG_PRODUTO_INVALIDO = By.cssSelector("div#center_column > p");


    public void enviarProdutoNoSearch(String produto){
        preencherInput(SEARCH, produto);
        clicarSearch();
    }

    public void clicarSearch(){
        clicar(BTN_SEARCH);
    }

    public String validarNomeDoProdutoBuscado(){
        return lerTexto(PRODUTO_RESPONSE);
    }

    public String validarMensagemDeNaoEncontrado(){
        return lerTexto(MSG_PRODUTO_INVALIDO);
    }

}
