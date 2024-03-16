package automationpractice.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import automationpractice.utils.Scroll;

public class HomePage extends BasePage {
    public static final By inputEmailNewsLetter = By.cssSelector("#newsletter-input");
    public static final By buttonSendNewsletter = By.cssSelector("#newsletter_block_left > div > form > div > button");
    public static final By buttonWomenCategory = By.cssSelector("#block_top_menu > ul > li:nth-child(1) > a");
    public static final By responseMessageNewsletter = By.cssSelector("#columns > p");

    WomenCategoryPage womanPage = new WomenCategoryPage();

    public void preencherInputNewsLetter(String emailInput) {
        preencherInput(inputEmailNewsLetter, emailInput);
    }

    public String enviarEmailNewsletter(String emailinput) {
        scrollarAteOElemento(inputEmailNewsLetter);

        preencherInputNewsLetter(emailinput);

        clicar(buttonSendNewsletter);
        return getResponseText();
    }
    public void clicarBotaoEnviar() {
        clicar(buttonSendNewsletter);
    }

    public void clicarBotaoCategoriaWomen() {
        clicar(buttonWomenCategory);
    }

    public String getResponseText() {
        return lerTexto(responseMessageNewsletter);
    }

    public void selecionarProdutoNaCategoriaWomen() {
        String titlePage = womanPage.getTitlePageText();
        Assert.assertEquals("WOMEN", titlePage.trim());

        womanPage.clickAddMoreProduct();
    }
}
