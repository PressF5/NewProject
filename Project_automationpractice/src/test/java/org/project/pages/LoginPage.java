package org.project.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.project.base.BasePage;
import org.project.constants.UrlType;

public class LoginPage extends BasePage {
    SelenideElement fldEmailAddress = $("#email");
    SelenideElement fldPassword = $("#passwd");
    SelenideElement btnSignIn = $("#SubmitLogin");
    SelenideElement errorSingInForm = $(By.xpath("//div[@class='alert alert-danger' and not(@id)]//li"));

    public LoginPage open() {
        Selenide.open(UrlType.AUTHENTICATION_URL.getEndpoint());
        return this;
    }

    public LoginPage enterEmailAddress(String emailAddress) {
        fldEmailAddress.shouldBe(visible, enabled).val(emailAddress);
        return this;
    }

    public LoginPage enterPassword(String password) {
        fldPassword.shouldBe(visible, enabled).val(password);
        return this;
    }

    public AccountPage clickSignIn() {
        btnSignIn.shouldBe(visible, text("Sign in")).click();
        return page(AccountPage.class);
    }

    public LoginPage checkSingInError(String textError) {
        errorSingInForm.shouldHave(text(textError));
        return this;
    }
}
