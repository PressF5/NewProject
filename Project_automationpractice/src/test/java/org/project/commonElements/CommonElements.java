package org.project.commonElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.project.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class CommonElements<T extends BasePage<T>> extends BasePage<T>{

    private SelenideElement btnProceedToCheckout = $(By.xpath("(//a[@title='Proceed to checkout'])[last()]"));

    public T clickProceedToCheckoutButton() {
        clickButton(btnProceedToCheckout, "Proceed to checkout");
        return (T)this;
    }

}
