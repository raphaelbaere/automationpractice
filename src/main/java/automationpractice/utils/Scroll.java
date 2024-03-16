package automationpractice.utils;

import automationpractice.data.factory.seleniumfactory.SeleniumService;
import org.openqa.selenium.JavascriptExecutor;

public class Scroll extends SeleniumService {

    private static JavascriptExecutor js = (JavascriptExecutor) driver;

    /**
     * Scroll down de toda a página
     */
    public static void scrollDownPage() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scroll down determinada distância na página
     *
     * @param distance distancia, em px, a ser percorrida
     */
    public static void scrollDown(String distance) {
        js.executeScript(String.format("window.scrollTo(0, %s)", distance));
    }

    /**
     * Scroll up de toda a página
     */
    public static void scrollTopPage(String distance) {
        js.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Scroll top determinada distância na página
     *
     * @param distance distancia, em px, a ser percorrida
     */
    public static void scrollTop(String distance) {
        js.executeScript(String.format("window.scrollTo(window.scrollY, %s)", distance));
    }
}