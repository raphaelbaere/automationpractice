package automationpractice.page;

import org.openqa.selenium.By;
import automationpractice.data.dto.SignInDTO;

import java.util.ArrayList;

public class SignInPage extends BasePage {
    private static final By radioBtnTitleMr =
            By.cssSelector("#id_gender1");
    private static final By radioBtnTitleMrs =
            By.cssSelector("#id_gender2");
    private static final By campoFirstName =
            By.cssSelector("#customer_firstname");
    private static final By campoLastName =
            By.cssSelector("#customer_lastname");
    private static final By campoEmail =
            By.cssSelector("#email");
    private static final By campoPassword =
            By.cssSelector("#passwd");
    private static final By btnRegister =
            By.cssSelector("#submitAccount");
    private static final By msgCreateSuccess =
            By.cssSelector("#center_column > p.alert.alert-success");
    private static final By msgError =
            By.cssSelector("#center_column > div > ol > li");
    private static final By loggedAs =
            By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a > span");
    private static final By divError =
            By.cssSelector("#center_column > div");
    public ArrayList<String> registrarUsuarioValido(SignInDTO signINDTO) {
        if (signINDTO.getTitle() == "1") {
            clicar(radioBtnTitleMr);
        } else {
            clicar(radioBtnTitleMrs);
        }
        preencherInput(campoFirstName, signINDTO.getFirstName());
        preencherInput(campoLastName, signINDTO.getLastName());
        preencherInput(campoPassword, signINDTO.getPassword());

        By dayBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(1) > div > select > option:nth-child(" + (signINDTO.getDayOfBirth()) + ")");
        clicarEsperandoSerClicavel(dayBy);
        By monthBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(2) > div > select > option:nth-child(" + (signINDTO.getMonthOfBirth()) + ")");
        clicarEsperandoSerClicavel(monthBy);
        By yearBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(3) > div > select > option:nth-child(" + (signINDTO.getYearOfBirth()) + ")");
        clicarEsperandoSerClicavel(yearBy);

        clicar(btnRegister);

        ArrayList<String> mensagens = new ArrayList<>();

        String mensagemSucesso = lerTexto(msgCreateSuccess);
        String logadoComo = lerTexto(loggedAs);

        mensagens.add(mensagemSucesso);
        mensagens.add(logadoComo);

        return mensagens;
    }

    public String registrarUsuarioInvalido(SignInDTO signINDTO) {
        if (signINDTO.getTitle() == "1") {
            clicarEsperandoSerClicavel(radioBtnTitleMr);
        } else {
            clicarEsperandoSerClicavel(radioBtnTitleMrs);
        }
        preencherInput(campoFirstName, signINDTO.getFirstName());
        preencherInput(campoLastName, signINDTO.getLastName());
        clearTexto(campoEmail);
        preencherInput(campoEmail, "emailinvalido");
        preencherInput(campoPassword, signINDTO.getPassword());

        By dayBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(1) > div > select > option:nth-child(" + (signINDTO.getDayOfBirth()) + ")");
        clicarEsperandoSerClicavel(dayBy);
        By monthBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(2) > div > select > option:nth-child(" + (signINDTO.getMonthOfBirth()) + ")");
        clicarEsperandoSerClicavel(monthBy);
        By yearBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(3) > div > select > option:nth-child(" + (signINDTO.getYearOfBirth()) + ")");
        clicarEsperandoSerClicavel(yearBy);

        clicarEsperandoSerClicavel(btnRegister);

        return lerTexto(msgError);
    }

    public String registrarUsuarioInvalidoJaExistente(SignInDTO signInDTO) {
        if (signInDTO.getTitle() == "1") {
            clicarEsperandoSerClicavel(radioBtnTitleMr);
        } else {
            clicarEsperandoSerClicavel(radioBtnTitleMrs);
        }
        preencherInput(campoFirstName, signInDTO.getFirstName());
        preencherInput(campoLastName, signInDTO.getLastName());
        clearTexto(campoEmail);
        preencherInput(campoEmail, "baereraphael@gmail.com");
        preencherInput(campoPassword, signInDTO.getPassword());

        By dayBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(1) > div > select > option:nth-child(" + (signInDTO.getDayOfBirth()) + ")");
        clicarEsperandoSerClicavel(dayBy);
        By monthBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(2) > div > select > option:nth-child(" + (signInDTO.getMonthOfBirth()) + ")");
        clicarEsperandoSerClicavel(monthBy);
        By yearBy = By.cssSelector("#account-creation_form > div.account_creation > div:nth-child(7) > div >div:nth-child(3) > div > select > option:nth-child(" + (signInDTO.getYearOfBirth()) + ")");
        clicarEsperandoSerClicavel(yearBy);

        clicarEsperandoSerClicavel(btnRegister);

        return lerTexto(msgError);
    }

    public String registrarUsuarioComTodosOsCamposVazios() {
        clicarEsperandoSerClicavel(btnRegister);

        return lerTexto(divError);
    }
}
