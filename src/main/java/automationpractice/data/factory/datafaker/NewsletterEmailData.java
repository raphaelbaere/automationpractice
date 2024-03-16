package automationpractice.data.factory.datafaker;

import automationpractice.data.dto.NewsletterEmailDTO;
import automationpractice.utils.DataFakerGenerator;

public class NewsletterEmailData {
    DataFakerGenerator faker = new DataFakerGenerator();

    public NewsletterEmailDTO getDTOcomEmailValido() {
        return NewsletterEmailDTO.builder()
                .email(faker.emailFaker())
                .build();
    }

    public NewsletterEmailDTO getDTOcomEmailExistente() {
        return NewsletterEmailDTO.builder()
                .email("cleito@mail.com")
                .build();
    }
    public NewsletterEmailDTO getDTOcomEmailVazio() {
        return NewsletterEmailDTO.builder()
                .email("")
                .build();
    }
}
