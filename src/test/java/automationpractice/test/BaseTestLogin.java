package automationpractice.test;


import automationpractice.data.factory.seleniumfactory.SeleniumService;
import org.junit.After;
import org.junit.Before;

public class BaseTestLogin {

    SeleniumService seleniumFactory = new SeleniumService();

    @Before
    public void abrirNavegador() {
        seleniumFactory.initBrowser("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    @After
    public void fecharNavegador() {
        seleniumFactory.tearDown();
    }
}
