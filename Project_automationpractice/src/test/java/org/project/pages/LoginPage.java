package org.project.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.project.base.BasePage;
import org.project.constants.UrlType;

public class LoginPage extends BasePage<LoginPage> {
    private SelenideElement fldEmailAddress = $("#email");
    private SelenideElement fldPassword = $("#passwd");
    private SelenideElement btnSignIn = $("#SubmitLogin");
    private SelenideElement errorSingInForm = $(By.xpath("//div[@class='alert alert-danger' and not(@id)]//li"));

    private SelenideElement headCreateAccount = $(By.xpath("//form[@id='create-account_form']//h3"));
    private SelenideElement headLoginAccount = $(By.xpath("//form[@id='login_form']//h3"));

    public LoginPage open() {
        Selenide.open(UrlType.AUTHENTICATION_URL.getEndpoint());
        return this;
    }

    public LoginPage checkHeadCreateAccount(String textHead) {
        headCreateAccount.shouldHave(text(textHead));
        return this;
    }

    public LoginPage checkHeadLoginAccount(String textHead) {
        headLoginAccount.shouldHave(text(textHead));
        return this;
    }

    public LoginPage enterEmailAddress(String emailAddress) {
        return enterIntoField(fldEmailAddress, emailAddress);
    }

    public LoginPage enterPassword(String password) {
        return enterIntoField(fldPassword, password);
    }

    public AccountPage clickSignIn() {
        clickButton(btnSignIn, "Sign in");
        return page(AccountPage.class);
    }

    public LoginPage checkSingInError(String textError) {
        errorSingInForm.shouldHave(text(textError));
        return this;
    }
}
