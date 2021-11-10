package org.project.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.project.base.BasePage;
import org.project.constants.UrlType;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage extends BasePage {
    private SelenideElement title = $("h1");

    public AccountPage open() {
        Selenide.open(UrlType.ACCOUNT_URL.getEndpoint());
        return this;
    }

    public AccountPage checkTitle() {
        title.shouldHave(text("MY ACCOUNT"));
        return this;
    }
}
