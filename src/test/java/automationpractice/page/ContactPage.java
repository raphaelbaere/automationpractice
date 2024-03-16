package automationpractice.page;

import org.openqa.selenium.By;

public class ContactPage extends BasePage {

    private static final By btnContact =
            By.cssSelector("#contact-link > a");

    private static final By textTituloPaginaContato =
            By.cssSelector("#center_column > h1");

    private static final By btnAssunto =
            By.cssSelector("#uniform-id_contact > select");

    private static final By btnAssuntoSelecionado =
            By.cssSelector("#id_contact > option:nth-child(2)");

    private static final By campoEmail =
            By.cssSelector("#email");

    private static final By campoMsgm =
            By.cssSelector("#message");

    private static final By btnSubmit =
            By.cssSelector("#submitMessage");

    private static final By textMsgmEnviadoComSucesso =
            By.cssSelector("#center_column > p");

    private static final By textMsgmNaoPodeEstarVazia =
            By.cssSelector("#center_column > div > ol > li");

    private static final By textMsgmEmailInvalido =
            By.cssSelector("#center_column > div > ol > li");

    private static final By textMsgmAssuntoNaoSelecionado =
            By.cssSelector("#center_column > div > ol > li");


    public String validarPaginaContact() {
        clicar(btnContact);
        return lerTexto(textTituloPaginaContato);
    }

    public String mandarMensagemDeContato(String email, String msgm) {
        clicar(btnAssunto);
        clicar(btnAssuntoSelecionado);
        preencherInput(campoEmail, email);
        preencherInput(campoMsgm, msgm);
        clicar(btnSubmit);
        return lerTexto(textMsgmEnviadoComSucesso);
    }

    public String mensagemDeContatoVazia(String email, String msgm) {
        clicar(btnAssunto);
        clicar(btnAssuntoSelecionado);
        preencherInput(campoEmail, email);
        preencherInput(campoMsgm, msgm);
        clicar(btnSubmit);
        return lerTexto(textMsgmNaoPodeEstarVazia);
    }

    public String mensagemDeContatoEmailInvalido(String email, String msgm) {
        clicar(btnAssunto);
        clicar(btnAssuntoSelecionado);
        preencherInput(campoEmail, email);
        preencherInput(campoMsgm, msgm);
        clicar(btnSubmit);
        return lerTexto(textMsgmEmailInvalido);
    }

    public String mensagemDeContatoAssuntoInvalido(String email, String msgm) {
        preencherInput(campoEmail, email);
        preencherInput(campoMsgm, msgm);
        clicar(btnSubmit);
        return lerTexto(textMsgmAssuntoNaoSelecionado);
    }


}
