package org.project.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.project.base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements ITestListener {
    private void takeScreenshot(ITestResult testResult) {
        Object baseTest = testResult.getInstance();
        WebDriver driver = ((BaseTest) baseTest).getDriver();
        Allure.addAttachment("Screenshot:\n" + testResult.getTestClass().getName() + "." +
                        testResult.getMethod().getMethodName() + "\n" +
                        driver.getCurrentUrl(),
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }
}
