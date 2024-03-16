package automationpractice.test;


import automationpractice.data.factory.seleniumfactory.SeleniumService;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    SeleniumService seleniumFactory = new SeleniumService();

    @Before
    public void abrirNavegador() {
        seleniumFactory.initBrowser("http://www.automationpractice.pl/index.php");
    }

    @After
    public void fecharNavegador() {
        seleniumFactory.tearDown();
    }
}
