package org.project.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class BaseTest {

    @BeforeSuite
    public void beforeSuit() {
        System.setProperty("video.folder", "target" + File.separator + "Videos");
        System.setProperty("recorder.type", "FFMPEG");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = 10_000;
        Configuration.savePageSource = false;
        Configuration.screenshots = false;

    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(String browser) {
        Configuration.browser = browser.toLowerCase(Locale.ROOT);
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        WebDriverRunner.closeWebDriver();
        try {
            takeVideo(testResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("After test " + new File("target" + File.separator + "Videos").list()[0]);
    }

    @AfterSuite
    public void afterSuite() throws IOException {System.out.println("After suite " + new File("target" + File.separator + "Videos").list()[0]); FileUtils.deleteDirectory(new File("build")); }

    public WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    private void takeVideo(ITestResult testResult) throws IOException {
        File videoFiles = new File("target" + File.separator + "Videos");
        byte[] videoByte = null;

//        int countTemporaryFiles = 0;
//        while(countTemporaryFiles >= 0) {
//            countTemporaryFiles = -1;
//            System.out.println("Начинаем бегать по циклу while " + countTemporaryFiles);
//            for (int i = 0; i < videoFiles.list().length; i++) {
//                if (videoFiles.list()[i].substring(0, "temporary".length()).contains("temporary")) ;
//                countTemporaryFiles++;
//            }
//        }
//        System.out.println("Прошли цикл while");
        for(int i = 0; i < videoFiles.list().length; i++){
            String str = videoFiles.list()[i];
            if(str.substring(0, testResult.getMethod().getMethodName().length()).contains(testResult.getMethod().getMethodName())) {
                videoByte = Files.toByteArray(new File("target" + File.separator + "Videos" + File.separator + str));
            }
        }
        System.out.println(videoByte);
        //Добавлять видео прогона на прямую в json файл allure
        Allure.getLifecycle().addAttachment("Video:", "video/mp4", "mp4", videoByte);
    }

}
