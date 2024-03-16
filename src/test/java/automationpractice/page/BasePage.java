package automationpractice.page;
import automationpractice.data.factory.seleniumfactory.Elements;

import org.openqa.selenium.By;

public class BasePage extends Elements {

    public static void preencherInput(By by, String text) {
        esperarElemento(by);
        element(by).sendKeys(text);
    }

    public static void clicar(By by) {
        esperarElemento(by);
        element(by).click();
    }

    public static void clicarEsperandoSerClicavel(By by) {
        esperarElemento(by);
        esperarElementoSerClicavel(by);
        element(by).click();
    }
    public static void clicarCheckbox(By by) {
        esperarElemento(by);
        element(by).click();
    }
    static void scrollarAoElemento(By by) {
        scrollarAteOElemento(by);
    }
    protected static void clearTexto(By by) {
        esperarElemento(by);
        element(by).clear();
    }

    public static String lerTexto(By by) {
        esperarElemento(by);
        return element(by).getText();
    }
    public static String lerTextoVisivel(By by) {
        esperarElementoSerVisivel(by);
        return element(by).getText();
    }

    public static void tab(By by) {
        esperarElemento(by);
        element(by).sendKeys("\t");
    }

    protected static void delayCaptcha(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected static boolean validator(By by){
        return validarElementoNaTela(by);
    }

    public static void hoverOnElement(By by) {
        hoverNoElemento(by);
    }
}
