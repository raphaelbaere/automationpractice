package automationpractice.test;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import automationpractice.data.dto.LoginDTO;
import automationpractice.data.factory.datafaker.LoginData;
import automationpractice.page.LoginPage;

public class LoginTest extends BaseTestLogin {

    LoginPage loginPage = new LoginPage();
    LoginData loginData = new LoginData();

    @Test
    @Feature("Login")
    @Story("Login valido com sucesso")
    @Description("Testa se o usuário consegue efetuar o login com dados validos e se de fato esta logado na pagina")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarLoginComDadosValidos(){
        LoginDTO usuario = loginData.loginDadosValidos();
        loginPage.preencherCampoEmail(usuario.getEmail());
        loginPage.preencherCampoSenha(usuario.getSenha());
        loginPage.clicarBtnAcessar();
        Assertions.assertTrue(loginPage.validarLoginArea());

    };

    @Test
    @Feature("Login")
    @Story("Login invalido com mensagem de erro")
    @Description("Testa se o usuário ao efetuar login com dados invalidos recebe a mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarLoginDadosInvalidos() {
        LoginDTO usuario = loginData.loginDadosDinamicos();
        loginPage.preencherCampoEmail(usuario.getEmail());
        loginPage.preencherCampoSenha(usuario.getSenha());
        loginPage.clicarBtnAcessar();
        String mensagem = loginPage.validarMsgEmailIncorreto();
        Assertions.assertEquals("Authentication failed.", mensagem);
    }

    @Test
    @Feature("Login")
    @Story("Login com campos vazios")
    @Description("Testa se o usuário ao efetuar login sem preencher nenhum campo recebe a mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarLoginTodosDadosVazios(){
        LoginDTO usuario = loginData.loginCamposVazios();
        loginPage.preencherCampoEmail(usuario.getEmail());
        loginPage.preencherCampoSenha(usuario.getSenha());
        loginPage.clicarBtnAcessar();
        String mensagem = loginPage.validarMsgEmailIncorreto();
        Assertions.assertEquals("An email address required.", mensagem);
    }

    @Test
    @Feature("Login")
    @Story("Login com email valido e senha vazia")
    @Description("Testa se o usuário ao efetuar login preenchendo um email valido e nao inserindo uma senha recebe a mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarLoginComEmailValidoESenhaVazia(){
        LoginDTO usuario = loginData.loginComEmailValidoSemSenha();
        loginPage.preencherCampoEmail(usuario.getEmail());
        loginPage.preencherCampoSenha(usuario.getSenha());
        loginPage.clicarBtnAcessar();
        String mensagem = loginPage.validarMsgEmailIncorreto();
        Assertions.assertEquals("Password is required.", mensagem);
    }

    @Test
    @Feature("Login")
    @Story("Login com email invalido")
    @Description("Testa se o usuário ao efetuar login preenchendo um email invalido recebe a mensagem apropriada")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidarLoginComEmailInvalido(){
        LoginDTO usuario = loginData.loginEmailInvalido();
        loginPage.preencherCampoEmail(usuario.getEmail());
        loginPage.preencherCampoSenha(usuario.getSenha());
        loginPage.clicarBtnAcessar();
        String mensagem = loginPage.validarMsgEmailIncorreto();
        Assertions.assertEquals("Invalid email address.", mensagem);
    }
}
