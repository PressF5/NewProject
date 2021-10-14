package org.project.base;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.http.Cookie;
import org.project.constants.UrlType;
import org.project.utils.PropertyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class BaseTest {

    public static void main(String[] args) {
        //System.out.println(UrlType.BASE_URL.getEndpoint());

//        Map<String, String> queryAndFormParam = Map.of("controller", "authentication",
//                                                       "email", "bogdantkachenko.dn@gmail.com",
//                                                       "passwd", "qwerty123",
//                                                       "back", "my-account",
//                                                       "SubmitLogin", "");
        Map<String, String> queryAndFormParam = PropertyUtil.convertPropertyFileToMap("properties/singin.properties");

        Map<String, String> cookies = given().
                params(queryAndFormParam).
//                    queryParam("controller", "authentication").
//                    formParam("email", "bogdantkachenko.dn@gmail.com").
//                    formParam("passwd", "qwerty123").
//                    formParam("back", "my-account").
//                    formParam("SubmitLogin", "").
                when().
                    post(UrlType.BASE_URL.getEndpoint()).
                then().
                    statusCode(302).
                    log().all().
                    extract().
                    response().
                    cookies();

        for(Map.Entry<String, String> cookie : cookies.entrySet())
            System.out.println(cookie.getKey() + ": " + cookie.getValue());

        List<Cookie> restAssuredCookies = new ArrayList<>();
        restAssuredCookies = cookies.asList();
        List<org.openqa.selenium.Cookie> seleniumCookies = new ArrayList<>();
        for(io.restassured.http.Cookie cookie: restAssuredCookies){
            seleniumCookies.add(new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(),
                    cookie.getPath(), cookie.getExpiryDate(), cookie.isSecured(), cookie.isHttpOnly(),
                    cookie.getSameSite()));
        }
//Довести до ума куки потом уже собирать тесты
        for()
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }


}
