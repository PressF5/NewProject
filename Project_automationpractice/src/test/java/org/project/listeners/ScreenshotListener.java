package org.project.listeners;

import com.automation.remarks.video.recorder.VideoRecorder;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.project.base.BaseTest;
import org.project.utils.ReadWriteProperty;
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
        File videoFiles = new File("target" + File.separator + "Videos");
        boolean flag = true;
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

        while(flag)
        for(int i = 0; i < videoFiles.list().length; i++){
            String str = videoFiles.list()[i];
            if(str.substring(0, testResult.getMethod().getMethodName().length()).contains(testResult.getMethod().getMethodName())) {
                flag = false;
                System.out.println("Видео найдено");
                videoByte = Files.toByteArray(new File("target" + File.separator + "Videos" + File.separator + str));
            }
            System.out.println("Бегаем по циклу " + str);
        }

        System.out.println(videoByte);
        Allure.getLifecycle().addAttachment("Video:", "video/mp4", "mp4", videoByte);
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && testResult.isSuccess()) {
            takeScreenshot(testResult);

            ReadWriteProperty.write(((BaseTest) testResult.getInstance()).getMethodName(),
                    Allure.getLifecycle().getCurrentTestCase().get());


//            try {
//                takeVideo(testResult);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            File video = VideoRecorder.getLastRecording();
//            System.out.println("|||||" + video.getAbsolutePath() + "|||||");
//            try {
//                Allure.getLifecycle().addAttachment("Video:", "video/mp4", "mp4", Files.toByteArray(video));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
