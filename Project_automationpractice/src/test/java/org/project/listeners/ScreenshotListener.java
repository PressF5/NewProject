package org.project.listeners;

import com.google.common.io.Files;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.project.base.BaseTest;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ScreenshotListener implements IInvokedMethodListener {
    private void takeScreenshot(ITestResult testResult) {
        WebDriver driver = ((BaseTest) testResult.getInstance()).getDriver();
        Allure.getLifecycle().addAttachment("Screenshot and URL: " + driver.getCurrentUrl(), "image/png", "png",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    private void takeVideo(ITestResult testResult) throws IOException {
        File videoFiles = new File("video");
        boolean flag = true;
        byte[] videoByte = null;
        for(int i = 0; i < videoFiles.list().length && flag; i++){
            String str = videoFiles.list()[i];
            if(str.substring(0, testResult.getMethod().getMethodName().length()).contains(testResult.getMethod().getMethodName())) {
                flag = false;
                videoByte = Files.toByteArray(new File("video" + File.separator + str));
            }
        }
        Allure.getLifecycle().addAttachment("Video:", "video/mp4", "mp4", videoByte);
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && !testResult.isSuccess()){
            takeScreenshot(testResult);
            try {
                takeVideo(testResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
