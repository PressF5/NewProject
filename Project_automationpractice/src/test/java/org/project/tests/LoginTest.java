package org.project.tests;

import org.project.base.BaseTest;
import org.project.dataproviders.DataProviders;
import org.project.pages.LoginPage;
import org.project.pojo.User;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest extends BaseTest {

    private LoginPage commonPart(User user) {
        return page(LoginPage.class).
                    open().
                    enterEmailAddress(user.getEmail()).
                    enterPassword(user.getPassword());
    }

    @Test(dataProvider = "getValidEmailAddressAndPassword", dataProviderClass = DataProviders.class)
    public void validLoginTest(User user) {
        commonPart(user).clickSignIn().checkTitle();
    }

    @Test(dataProvider = "getInvalidEmailAddressAndPassword", dataProviderClass = DataProviders.class)
    public void invalidLoginTest(User user) {
        LoginPage loginPage = commonPart(user);
            loginPage.clickSignIn();
            loginPage.checkSingInError(user.getError());
    }

}
