package org.project.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.project.base.BasePage;
import org.project.commonElements.CommonElements;
import org.project.constants.UrlType;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ItemsPage extends BasePage<ItemsPage> {
    private SelenideElement btnAdd = $(By.xpath("//button[@name='Submit']"));
    private SelenideElement quantityItems = $(By.xpath("//div[@class='layer_cart_product_info']//span[@id='layer_cart_product_quantity']"));

    public ItemsPage open() {
        Selenide.open(UrlType.BLOUSE_URL.getEndpoint());
        return this;
    }

    public ItemsPage clickAddButton() {
        return clickButton(btnAdd, "Add to cart");
    }

    public Summary clickProceedToCheckoutButton() {
        getComElements().clickProceedToCheckoutButton();
        return page(Summary.class);
    }

    public ItemsPage checkQuantityItems(int actualQuantity) {
        return checkQuantityItems(quantityItems, actualQuantity);
    }
//Сделать аннотацию логин для подключение cookies над классом и методом
}
