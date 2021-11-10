package org.project.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.project.base.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class Summary extends BasePage<Summary> {
    private SelenideElement fldQuantity = $(By.xpath("//input[contains(@class, 'cart_quantity_input form-control')]"));

    public Summary checkQuantityItems(int actualQuantity) {
        fldQuantity.shouldHave(Condition.value(String.valueOf(actualQuantity)));
        return this;
    }

    public LoginPage clickProceedToCheckoutButtonLoginNotYet() {
        getComElements().clickProceedToCheckoutButton();
        return page(LoginPage.class);
    }

    public Address clickProceedToCheckoutButtonLoginAlready() {
        getComElements().clickProceedToCheckoutButton();
        return page(Address.class);
    }

    //aka login page
    //aka already login page

}
