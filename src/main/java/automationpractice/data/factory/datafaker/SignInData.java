package automationpractice.data.factory.datafaker;

import automationpractice.data.dto.SignInDTO;
import automationpractice.utils.DataFakerGenerator;

public class SignInData {
    DataFakerGenerator dataFakerGenerator = new DataFakerGenerator();

    public SignInDTO signInDadosValidosDinamicos() {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setTitle(dataFakerGenerator.titleFaker());
        signInDTO.setFirstName(dataFakerGenerator.firstNameFaker());
        signInDTO.setLastName(dataFakerGenerator.lastNameFaker());
        signInDTO.setEmail(dataFakerGenerator.emailFaker());
        signInDTO.setPassword(dataFakerGenerator.senhaFaker());
        signInDTO.setDayOfBirth(dataFakerGenerator.dayOfBirthFaker());
        signInDTO.setMonthOfBirth(dataFakerGenerator.monthOfBirthFaker());
        signInDTO.setYearOfBirth(dataFakerGenerator.yearOfBirthFaker());
        return signInDTO;
    }
}
