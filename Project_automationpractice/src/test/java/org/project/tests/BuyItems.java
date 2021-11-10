package org.project.tests;

import org.project.annotations.Login;
import org.project.base.BaseTest;
import org.project.pages.ItemsPage;
import org.project.pages.Summary;
import org.testng.annotations.Test;

public class BuyItems extends BaseTest {

    private Summary common() {
        ItemsPage itemsPage = new ItemsPage();
        return itemsPage.
                open().
                clickAddButton().
                checkQuantityItems(1).
                clickProceedToCheckoutButton().
                checkQuantityItems(1);
    }

    @Test
    public void buyItemsWithoutAuth() {
        common().
                clickProceedToCheckoutButtonLoginNotYet().
                checkHeadLoginAccount("ALREADY REGISTERED?").
                checkHeadCreateAccount("CREATE AN ACCOUNT");
    }

    @Test
    @Login
    public void buyItemsWithAuth() {
        common().
                clickProceedToCheckoutButtonLoginAlready().
                checkFirstNameLastName("Bob Smit").
                checkAddress("qwerty123").
                checkCityStateNamePostcode("New York, New York 00000").
                checkCountry("United States").
                checkPhone("12345678");
    }

}
