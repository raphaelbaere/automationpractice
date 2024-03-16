package automationpractice.test;


import automationpractice.data.factory.seleniumfactory.SeleniumService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestLogin {

    SeleniumService seleniumFactory = new SeleniumService();

    @BeforeEach
    public void abrirNavegador() {
        seleniumFactory.initBrowser("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    @AfterEach
    public void fecharNavegador() {
        seleniumFactory.tearDown();
    }
}
