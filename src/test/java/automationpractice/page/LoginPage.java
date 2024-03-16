package automationpractice.page;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By CAMPO_EMAIL = By.cssSelector("#login_form > div > div > #email");

    private static final By CAMPO_SENHA = By.cssSelector("#login_form > div > div:nth-child(2) > input");

    private static final By BTN_ACESSAR = By.cssSelector("#login_form > div > p.submit > #SubmitLogin");

    private static final By MSG_EMAIL_INCORRETO = By.cssSelector("#center_column > div.alert.alert-danger > ol > li");

    private static final By VALIDATOR_LOGINAREA = By.cssSelector("div#center_column li:nth-child(4) > a > span");

    public void preencherCampoEmail(String email) {
        preencherInput(CAMPO_EMAIL, email);
    }

    public void preencherCampoSenha(String senha) {
        preencherInput(CAMPO_SENHA, senha);
    }

    public void clicarBtnAcessar() {
        clicar(BTN_ACESSAR);
    }

    public String validarMsgEmailIncorreto() {
        return lerTexto(MSG_EMAIL_INCORRETO);
    }

    public Boolean validarLoginArea(){
        boolean validator = validarElementoNaTela(VALIDATOR_LOGINAREA);
        return validator;
    }

    public void fazerLogin(String email, String senha) {
        preencherInput(CAMPO_EMAIL, email);
        preencherInput(CAMPO_SENHA, senha);
        clicar(BTN_ACESSAR);
    }

    public String loginIncorreto(String email, String senha) {
        preencherInput(CAMPO_EMAIL, email);
        preencherInput(CAMPO_SENHA, senha);
        clicar(BTN_ACESSAR);
        return lerTexto(MSG_EMAIL_INCORRETO);
    }
}
