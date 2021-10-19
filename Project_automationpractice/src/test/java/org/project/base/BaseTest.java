package org.project.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class BaseTest {

    @BeforeSuite
    public void beforeSuit() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = 10_000;
        Configuration.screenshots = false;
    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(String browser) {
        Configuration.browser = browser.toLowerCase(Locale.ROOT);
    }

    @AfterMethod
    public void afterMethod() {
        WebDriverRunner.closeWebDriver();
    }

    public WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    @AfterSuite
    public void afterSuite() throws IOException { FileUtils.deleteDirectory(new File("build")); }
}
