package automationpractice.page;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import automationpractice.data.dto.LoginDTO;
import automationpractice.data.factory.datafaker.LoginData;
import automationpractice.utils.Scroll;

public class SummaryCartPage extends BasePage {
    public static final By titleSumarioCarrinho = By.cssSelector("#order_step > li.step_current.first > span");
    public static final By buttonToSign = By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium");
    public static final By buttonToPayment = By.cssSelector("#form > p > button");
    public static final By buttonPayWithBankWire = By.cssSelector("#HOOK_PAYMENT > div:nth-child(1) > div > p > a");
    public static final By buttonConfirmOrder = By.cssSelector("#cart_navigation > button");
    public static final By checkboxAgreeTerms = By.cssSelector("input#cgv");
    public static final By textAbaShippingValidation = By.cssSelector("#carrier_area > h1");
    public static final By buttonToShipping = By.cssSelector("#center_column > form > p > button");
    public static final By textAbaAuthentication = By.cssSelector("#center_column > h1");
    public static final By textConfirmedOrder = By.cssSelector("#center_column > p.alert.alert-success");
    LoginPage loginPage = new LoginPage();
    LoginData loginData = new LoginData();

    public String getTitleSumarioCarrinho() {
        return lerTexto(titleSumarioCarrinho);
    }
    public void clickProceedToSignInSection() {
        clicar(buttonToSign);
    }
    public void clickProceedToShippingSection() {
        clicar(buttonToShipping);
    }
    public void clickProceedToPaymentSection() {
        clicar(buttonToPayment);
    }
    public void clickAgreeTerms() {
        clicarCheckbox(checkboxAgreeTerms);
    }

    public boolean estaNaAbaAuthentication() {
        return validator(textAbaAuthentication);
    }

    public String estaNaAbaShipping() {
        return lerTexto(textAbaShippingValidation);
    }

    public void clickPagarComCartao() {
        clicar(buttonPayWithBankWire);
    }

    public void clickConfirmarOrder() {
        clicar(buttonConfirmOrder);
    }

    public String getMessageOrderConfirmed() {
        return lerTextoVisivel(textConfirmedOrder);
    }

    public void fecharCarrinhoCompras() {
        String titleSumarioCarrinho = getTitleSumarioCarrinho();
        Assertions.assertEquals("01. Summary", titleSumarioCarrinho.trim());

        scrollarAteOElemento(buttonToSign);
        clickProceedToSignInSection();
        boolean loginValidator = estaNaAbaAuthentication();
        Assertions.assertTrue(loginValidator);

        LoginDTO data = loginData.loginDadosValidos();
        loginPage.fazerLogin(data.getEmail(), data.getSenha());

        scrollarAteOElemento(buttonToShipping);
        clickProceedToShippingSection();
        String validation = estaNaAbaShipping();
        Assertions.assertEquals("SHIPPING:", validation);

        scrollarAteOElemento(checkboxAgreeTerms);
        clickAgreeTerms();

        scrollarAteOElemento(buttonToPayment);
        clickProceedToPaymentSection();

        scrollarAteOElemento(buttonPayWithBankWire);
        clickPagarComCartao();

        scrollarAteOElemento(buttonConfirmOrder);
        clickConfirmarOrder();

        String validationTextOrder = getMessageOrderConfirmed();
        Assertions.assertEquals("Your order on My Shop is complete.", validationTextOrder);

    }
}
