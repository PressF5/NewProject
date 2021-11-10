package org.project.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.project.base.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class Address extends BasePage<Address> {
    private SelenideElement commonParent = $(By.xpath("//ul[@id='address_invoice']"));
    private SelenideElement firstNameLastName = commonParent.$(By.xpath("./li[@class='address_firstname address_lastname']"));
    private SelenideElement address = commonParent.$(By.xpath("./li[@class='address_address1 address_address2']"));
    private SelenideElement city = commonParent.$(By.xpath("./li[@class='address_city address_state_name address_postcode']"));
    private SelenideElement country = commonParent.$(By.xpath("./li[@class='address_country_name']"));
    private SelenideElement phone = commonParent.$(By.xpath("./li[@class='address_phone_mobile']"));


    public Address checkFirstNameLastName(String firstNameAndLastName) {
        return checkElementText(firstNameLastName,firstNameAndLastName);
    }

    public Address checkAddress(String addresses) {
        return checkElementText(address,addresses);
    }

    public Address checkCityStateNamePostcode(String cityStateNamePostcode) {
        return checkElementText(city,cityStateNamePostcode);
    }

    public Address checkCountry(String countryName) {
        return checkElementText(country,countryName);
    }

    public Address checkPhone(String phoneNumber) {
        return checkElementText(phone,phoneNumber);
    }
}