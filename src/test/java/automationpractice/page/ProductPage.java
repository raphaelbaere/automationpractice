package automationpractice.page;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    public static final By titleProduct = By.cssSelector("#center_column > div > div > div.pb-center-column.col-xs-12.col-sm-4 > h1");
    public static final By seletorTamanhoRoupa = By.cssSelector("#group_1 > option:nth-child(2)");
    public static final By seletorPositivoQuantidadeProduto = By.cssSelector("#quantity_wanted_p > a.btn.btn-default.button-plus.product_quantity_up > span");
    public static final By buttonAddtoCart = By.cssSelector("#add_to_cart > button");
    public static final By buttonProceedToCheckout = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a");
    public static final By textReponseCadastroProduto = By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_product.col-xs-12.col-md-6 > h2");
    public static final By textProdutoForaDeEstoque = By.cssSelector("#availability_value");



    public String getTitleProduct() {
        return lerTexto(titleProduct);
    }
    public void clicarSeletorTamanho() {
        clicarEsperandoSerClicavel(seletorTamanhoRoupa);
    }
    public void clicarAdicionarQuantidadeProduto() {
        clicarEsperandoSerClicavel(seletorPositivoQuantidadeProduto);
    }
    public void clicarAdicionarProdutoAoCarrinho() {
        clicar(buttonAddtoCart);
    }
    public String getResponseAdicionarCarrinho() {
        return lerTextoVisivel(textReponseCadastroProduto);
    }
    public void clicarIrParaSumarioDoCarrinho() {
        clicar(buttonProceedToCheckout);
    }

    public void validarMensagemProdutoForaDeEstoque() {
        String messageValidation = lerTexto(textProdutoForaDeEstoque);
        Assertions.assertEquals("This product is no longer in stock with those attributes but is available with others.", messageValidation);
    }

    public void adicionarProdutoAoCarrinho() {
        String titleText = getTitleProduct();
        Assertions.assertEquals("Faded Short Sleeve T-shirts", titleText);

        clicarSeletorTamanho();
        clicarAdicionarQuantidadeProduto();

        scrollarAoElemento(buttonAddtoCart);
        clicarAdicionarProdutoAoCarrinho();

        String responseAddCarrinho = getResponseAdicionarCarrinho();
        Assertions.assertEquals("Product successfully added to your shopping cart", responseAddCarrinho);
    }

}
