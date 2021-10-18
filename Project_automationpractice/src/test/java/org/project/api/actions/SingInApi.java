package org.project.api.actions;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.project.constants.UrlType;
import org.project.utils.CookieUtils;
import org.project.utils.PropertyUtil;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SingInApi {

    public Cookies getCookiesToSingUP(){
        Map<String, String> queryAndFormParam = PropertyUtil.convertPropertyFileToMap("properties/singin.properties");
        return given().
                    params(queryAndFormParam).
               when().
                    post(UrlType.BASE_URL.getEndpoint()).
               then().
                    statusCode(302).
                    log().all().
                    extract().
                    response().
                    getDetailedCookies();
    }

    public void signIn(WebDriver driver){
        for(Cookie cookie: CookieUtils.convertRestAssuredCookiesToSeleniumCookies(getCookiesToSingUP()))
        driver.manage().addCookie(cookie);
    }
}
