package automationpractice.test;

import io.qameta.allure.*;
import automationpractice.data.factory.datafaker.LoginData;
import automationpractice.page.*;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends BaseTest {

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    SummaryCartPage summaryCartPage = new SummaryCartPage();
    LoginPage loginPage = new LoginPage();
    LoginData loginData = new LoginData();

    @Test
    @Feature("Carrinho de Compras")
    @Story("Adicionar um produto ao carrinho com sucesso")
    @Description("Testa se o usuário consegue adicionar um produto no carrinho e concluir a compra, deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarAdicionarUmProdutoAoCarrinhoComSucesso() {
        homePage.clicarBotaoCategoriaWomen();
        homePage.selecionarProdutoNaCategoriaWomen();
        productPage.adicionarProdutoAoCarrinho();
        productPage.clicarIrParaSumarioDoCarrinho();
        summaryCartPage.fecharCarrinhoCompras();
    }
    @Test
    @Feature("Carrinho de Compras")
    @Story("Adicionar um produto sem estoque")
    @Description("Testa se o usuário recebe uma mensagem ao tentar adicionar um produto fora de estoque")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarMensagemAdicionarProdutoAoCarrinhoSemEstoque() {
        homePage.clicarBotaoCategoriaWomen();
        homePage.selecionarProdutoNaCategoriaWomen();
        productPage.validarMensagemProdutoForaDeEstoque();
    }
}
