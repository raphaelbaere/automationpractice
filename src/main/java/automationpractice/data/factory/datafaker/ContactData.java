package automationpractice.data.factory.datafaker;
import automationpractice.utils.DataFakerGenerator;
import automationpractice.data.dto.ContactDTO;

public class ContactData {

    DataFakerGenerator dataFakerGenerator = new DataFakerGenerator();

    public ContactDTO contatoComDadosValido() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail(dataFakerGenerator.emailFaker());
        contactDTO.setMsg(dataFakerGenerator.mensagemFaker());
        return contactDTO;
    }

    public ContactDTO contatoComMensagemInvalida() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail(dataFakerGenerator.emailFaker());
        contactDTO.setMsg("");
        return contactDTO;
    }

    public ContactDTO contatoComEmailInvalido() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail("emailinvalido.com");
        contactDTO.setMsg(dataFakerGenerator.mensagemFaker());
        return contactDTO;
    }

}
