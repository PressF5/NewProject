package org.project.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Locale;

public class BaseTest {

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(String browser) {
        Configuration.browser = browser.toLowerCase(Locale.ROOT);
        Configuration.timeout = 10_000;
        Configuration.screenshots = true;
        Configuration.reportsFolder = "target/screenshot";
        ScreenShooter.captureSuccessfulTests = true;
    }

    @AfterMethod
    public void afterMethod() {
        WebDriverRunner.closeWebDriver();
    }
}
