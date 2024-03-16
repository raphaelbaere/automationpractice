package automationpractice.page;

import org.openqa.selenium.By;

public class WomenCategoryPage extends BasePage {
    public static final By textWomanTitleValidation = By.cssSelector("#center_column > h1 > span.cat-name");
    public static final By buttonSelectProductByImage = By.cssSelector("#center_column > ul > li:nth-child(1) > div > div.left-block > div > a.product_img_link > img");
    public static final By buttonMore = By.cssSelector("div#center_column li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line.hovered > div > div.right-block > div.button-container > a > span");

    public String getTitlePageText() {
        return lerTexto(textWomanTitleValidation);
    }

    public void clickProductOnImage() {
        clicar(buttonSelectProductByImage);
    }

    public void clickAddMoreProduct() {
        scrollarAoElemento(buttonSelectProductByImage);
        hoverOnElement(buttonSelectProductByImage);
        clicar(buttonMore);
    }
}
