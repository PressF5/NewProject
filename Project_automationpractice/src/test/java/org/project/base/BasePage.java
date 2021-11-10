package org.project.base;

import com.codeborne.selenide.SelenideElement;
import org.project.commonElements.CommonElements;

import static com.codeborne.selenide.Condition.*;

public class BasePage<T extends BasePage<T>> {

    protected CommonElements<T> getComElements() {
        return new CommonElements<T>();
    }

    protected T clickButton(SelenideElement element, String textButton) {
        element.shouldBe(visible, enabled, text(textButton)).click();
        return (T)this;
    }

    protected T enterIntoField(SelenideElement element, String text) {
        element.shouldBe(visible, enabled).val(text);
        return (T)this;
    }

    protected T checkQuantityItems(SelenideElement element, int actualQuantity) {
        element.shouldHave(text(String.valueOf(actualQuantity)));
        return (T)this;
    }

    protected T checkElementText(SelenideElement element, String text) {
        element.shouldHave(text(String.valueOf(text)));
        return (T)this;
    }
}
