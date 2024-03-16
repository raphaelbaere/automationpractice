package automationpractice.test;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import automationpractice.data.factory.datafaker.NewsletterEmailData;
import automationpractice.page.HomePage;
import automationpractice.utils.Scroll;

public class HomeNewsLetterTest extends BaseTest {

    private HomePage homePage = new HomePage();

    NewsletterEmailData factory = new NewsletterEmailData();

    @Test
    @Feature("Newsletter")
    @Story("Inscricao com email valido")
    @Description("Testa se o usuário consegue se inscrever no NewsLetter com um email valido, deve retornar mensagem de sucesso")
    @Severity(SeverityLevel.MINOR)
    public void validarUmaInscricaoNewsLetterComEmailValido() {
        String response = homePage.enviarEmailNewsletter(factory.getDTOcomEmailValido().getEmail());

        Assert.assertEquals("Newsletter : You have successfully subscribed to this newsletter.", response);
    }

    @Test
    @Feature("Newsletter")
    @Story("Inscricao com email ja existente")
    @Description("Testa se o usuário recebe mensagem de erro ao tentar inscrever email ja anteriormente cadastrado")
    @Severity(SeverityLevel.MINOR)
    public void validarMensagemDeEmailRepetido() {
        String response = homePage.enviarEmailNewsletter(factory.getDTOcomEmailExistente().getEmail());

        Assert.assertEquals("Newsletter : This email address is already registered.", response);
    }

    @Test
    @Feature("Newsletter")
    @Story("Inscricao com email invalido")
    @Description("Testa se o usuário recebe mensagem de erro ao tentar inscrever email com formatacao invalida")
    @Severity(SeverityLevel.MINOR)
    public void validarMensagemDeEmailInvalido() {
        String response = homePage.enviarEmailNewsletter(factory.getDTOcomEmailVazio().getEmail());

        Assert.assertEquals("Newsletter : Invalid email address.", response);
    }
}
