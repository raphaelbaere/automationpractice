package automationpractice.test;


import automationpractice.data.factory.seleniumfactory.SeleniumService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    SeleniumService seleniumFactory = new SeleniumService();

    @BeforeEach
    public void abrirNavegador() {
        seleniumFactory.initBrowser("http://www.automationpractice.pl/index.php");
    }

    @AfterEach
    public void fecharNavegador() {
        seleniumFactory.tearDown();
    }
}
