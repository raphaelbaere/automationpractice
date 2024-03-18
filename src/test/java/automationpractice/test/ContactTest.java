package automationpractice.test;

import io.qameta.allure.*;
import automationpractice.data.dto.ContactDTO;
import automationpractice.data.factory.datafaker.ContactData;
import automationpractice.page.ContactPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ContactTest extends BaseTest {

    ContactPage contactPage = new ContactPage();

    ContactData contactData = new ContactData();

    @Test
    @Feature("Serviço de Apoio ao Cliente")
    @Story("Mensagem de contato com dados validos")
    @Description("Testa se o usuário consegue enviar uma mensagem de contato com os dados válidos, deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.NORMAL)
    public void validarMensagemDeContatoComDadosValidos() {
        ContactDTO usu = contactData.contatoComDadosValido();
        String msgmTituloContact = contactPage.validarPaginaContact();
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US", msgmTituloContact);
        String msgmSucesso = contactPage.mandarMensagemDeContato(usu.getEmail(), usu.getMsg());
        Assertions.assertEquals("Your message has been successfully sent to our team.", msgmSucesso);
    }

    @Test
    @Feature("Serviço de Apoio ao Cliente")
    @Story("Mensagem de contato com mensagem vazia")
    @Description("Testa se o usuário recebe uma mensagem de erro ao nao inserir uma mensagem")
    @Severity(SeverityLevel.NORMAL)
    public void validarMensagemDeContatoComMensagemVazia() {
        ContactDTO usu = contactData.contatoComMensagemInvalida();
        String msgmTituloContact = contactPage.validarPaginaContact();
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US", msgmTituloContact);
        String msgmContatoVazio = contactPage.mensagemDeContatoVazia(usu.getEmail(), usu.getMsg());
        Assertions.assertEquals("The message cannot be blank.", msgmContatoVazio);
    }

    @Test
    @Feature("Serviço de Apoio ao Cliente")
    @Story("Mensagem de contato com email invalido")
    @Description("Testa se o usuário recebe uma mensagem de erro ao inserir um email fora da formatacao esperada")
    @Severity(SeverityLevel.NORMAL)
    public void validarMensagemDeContatoComEmailInvalido() {
        ContactDTO usu = contactData.contatoComEmailInvalido();
        String msgmTituloContact = contactPage.validarPaginaContact();
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US", msgmTituloContact);
        String msgmEmailInvalido = contactPage.mensagemDeContatoEmailInvalido(usu.getEmail(), usu.getMsg());
        Assertions.assertEquals("Invalid email address.", msgmEmailInvalido);
    }

    @Test
    @Feature("Serviço de Apoio ao Cliente")
    @Story("Mensagem de contato com assunto nao Selecionado")
    @Description("Testa se o usuário recebe uma mensagem de erro ao nao inserir um assunto da mensagem")
    @Severity(SeverityLevel.NORMAL)
    public void validarMensagemDeContatoAssuntoNaoSelecionado() {
        ContactDTO usu = contactData.contatoComDadosValido();
        String msgmTituloContact = contactPage.validarPaginaContact();
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US", msgmTituloContact);
        String msgmAssuntoNaoSelecionado = contactPage.mensagemDeContatoAssuntoInvalido(usu.getEmail(), usu.getMsg());
        Assertions.assertEquals("Please select a subject from the list provided.", msgmAssuntoNaoSelecionado);
    }

}
