package automationpractice.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import automationpractice.data.dto.SearchDTO;
import automationpractice.data.factory.datafaker.SearchData;
import automationpractice.page.SearchPage;

public class SearchTest extends BaseTest {

    SearchPage searchPage = new SearchPage();
    SearchData searchData = new SearchData();

    @Test
    @Feature("Search")
    @Story("Procurar produto existente")
    @Description("Testa se o usuário consegue procurar um produto existente e verificar se o produto procurado é o mesmo retornado")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidaSearchComProdutoValido(){
        searchPage.enviarProdutoNoSearch(searchData.produtoValido().getNome());
        String nomeDoProduto = searchPage.validarNomeDoProdutoBuscado();
        Assertions.assertEquals("Printed Dress", nomeDoProduto);
    }

    @Test
    @Feature("Search")
    @Story("Procurar produto nao existente")
    @Description("Testa se o usuário ao procurar produto nao existente recebe a mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidaSearchComProdutoInvalido(){
        SearchDTO produtoNomeInvalido = searchData.produtoInvalido();
        searchPage.enviarProdutoNoSearch(produtoNomeInvalido.getNome());
        String mensagem = searchPage.validarMensagemDeNaoEncontrado();
        Assertions.assertEquals("No results were found for your search \"" + produtoNomeInvalido.getNome() + "\"", mensagem);
    }

    @Test
    @Feature("Search")
    @Story("Procurar produto com erro de nomenclatura")
    @Description("Testa se o usuário ao procurar produto com um erro de nomenclatura retorna um produto com um nome semelhante")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidaSearchComProdutoComNomeErros(){
        searchPage.enviarProdutoNoSearch(searchData.produtoComNomeProximo().getNome());
        String nomeDoProduto = searchPage.validarNomeDoProdutoBuscado();
        Assertions.assertEquals("Printed Dress", nomeDoProduto);
    }





}
