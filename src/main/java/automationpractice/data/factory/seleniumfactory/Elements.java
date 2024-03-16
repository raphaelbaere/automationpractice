package automationpractice.data.factory.seleniumfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Elements extends SeleniumService {
    // Método para pegar um elemento
    public static WebElement element(By by) {
        return driver.findElement(by);
    }

    // Método para esperar um elemento
    public static void esperarElemento(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void esperarElementoSerVisivel(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static void esperarElementoSerClicavel(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public static void scrollarAteOElemento(By by) {
        WebElement elemento = element(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);
        actions.moveToElement(elemento).build().perform();
    }

    public static void hoverNoElemento(By by) {
        WebElement elemento = element(by);
        actions.moveToElement(elemento).perform();
    }


    // Método para validar um elemento na tela
    public static Boolean validarElementoNaTela(By by) {
        boolean validator = driver.findElement(by).isDisplayed();
        return validator;
    }
}
