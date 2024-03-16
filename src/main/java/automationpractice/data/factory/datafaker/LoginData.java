package automationpractice.data.factory.datafaker;

import org.apache.commons.lang3.StringUtils;
import automationpractice.data.dto.LoginDTO;
import automationpractice.utils.DataFakerGenerator;

public class LoginData {

    DataFakerGenerator dataFakerGenerator = new DataFakerGenerator();

    public LoginDTO loginDadosValidos() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("timequatro@email.com");
        loginDTO.setSenha("Senhadificil@123");
        return loginDTO;
    }

    public LoginDTO loginDadosDinamicos() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(dataFakerGenerator.emailFaker());
        loginDTO.setSenha(dataFakerGenerator.senhaFaker());
        return loginDTO;
    }

    public LoginDTO loginCamposVazios() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(StringUtils.EMPTY);
        loginDTO.setSenha(StringUtils.EMPTY);
        return loginDTO;
    }

    public LoginDTO loginEmailInvalido() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("timequatro@qaqwaqaqaqa");
        loginDTO.setSenha(dataFakerGenerator.senhaFaker());
        return loginDTO;
    }

    public LoginDTO loginComEmailValidoSemSenha() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("timequatro@email.com");
        loginDTO.setSenha(StringUtils.EMPTY);
        return loginDTO;
    }
}
