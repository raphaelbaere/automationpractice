package automationpractice.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import automationpractice.data.dto.SignInDTO;
import automationpractice.data.factory.datafaker.SignInData;
import automationpractice.page.AuthenticationPage;
import automationpractice.page.SignInPage;

import java.util.ArrayList;


public class SignInTest extends BaseTest{
    SignInData signInData = new SignInData();
    SignInPage signInPage = new SignInPage();
    AuthenticationPage authenticationPage = new AuthenticationPage();

    @Test
    @Feature("Cadastro")
    @Story("Cadastrar usuario com sucesso")
    @Description("Testa se o usuário ao efetuar cadastro preenchendo todos os dados de forma valida é realmente cadastro, deve receber uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarRegistroComDadosValidos(){
        SignInDTO signInDTO = signInData.signInDadosValidosDinamicos();
        authenticationPage.irParaPaginaSignInERegistrarUmaContaComEmailValido(signInDTO);
        ArrayList<String> mensagens = signInPage.registrarUsuarioValido(signInDTO);

        Assertions.assertEquals("Your account has been created.", mensagens.get(0));
        Assertions.assertEquals(signInDTO.getFirstName() + " " + signInDTO.getLastName(), mensagens.get(1));
    };

    @Test
    @Feature("Cadastro")
    @Story("Cadastrar usuario com email invalido")
    @Description("Testa se o usuário ao efetuar cadastro preenchendo um email invalido recebe uma mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarRegistroComEmailInvalido(){
        SignInDTO signInDTO = signInData.signInDadosValidosDinamicos();
        authenticationPage.irParaPaginaSignInERegistrarUmaContaComEmailValido(signInDTO);
        String mensagem = signInPage.registrarUsuarioInvalido(signInDTO);

        Assertions.assertEquals("email is invalid.", mensagem);
    };

    @Test
    @Feature("Cadastro")
    @Story("Cadastrar usuario com email ja cadastrado na pagina de Authentication")
    @Description("Testa se o usuário ao efetuar cadastro preenchendo um email ja anteriormente cadastrado recebe uma mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarRegistroComEmailInvalidoJaCadastradoNaPageDeAuthentication(){
        SignInDTO signInDTO = signInData.signInDadosValidosDinamicos();
        signInDTO.setEmail("baereraphael@gmail.com");
        String mensagem = authenticationPage.irParaPaginaSignInERegistrarUmaContaComEmailInvalidoJaExistente(signInDTO);

        Assertions.assertEquals("An account using this email address has already been registered. Please enter a valid password or request a new one.", mensagem);
    };

    @Test
    @Feature("Cadastro")
    @Story("Cadastrar usuario com email ja cadastrado na pagina de SignIn")
    @Description("Testa se o usuário ao efetuar cadastro preenchendo um email ja anteriormente cadastrado recebe uma mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarRegistroComEmailInvalidoJaCadastradoNaPageDeSignIn(){
        SignInDTO signInDTO = signInData.signInDadosValidosDinamicos();
        authenticationPage.irParaPaginaSignInERegistrarUmaContaComEmailValido(signInDTO);
        String mensagem = signInPage.registrarUsuarioInvalidoJaExistente(signInDTO);
        Assertions.assertEquals("An account using this email address has already been registered.", mensagem);
    };

    @Test
    @Feature("Cadastro")
    @Story("Cadastrar usuario com dados vazios e com email valido na pagina de Authentication")
    @Description("Testa se o usuário ao efetuar cadastro preenchendo um email valido porem com dados vazios recebe uma mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarRegistroComDadosVaziosNaPageSignInComEmailValidoNaPageAuthentication(){
        SignInDTO signInDTO = signInData.signInDadosValidosDinamicos();
        authenticationPage.irParaPaginaSignInERegistrarUmaContaComEmailValido(signInDTO);
        String mensagem = signInPage.registrarUsuarioComTodosOsCamposVazios();
        Assertions.assertEquals("There are 3 errors\nlastname is required.\nfirstname is required.\npasswd is required.", mensagem);
    };
}
