package org.nhihuynh.pageObjects.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.nhihuynh.utils.AndroidAction;
import org.nhihuynh.utils.WaitUtil;

public class BasePage {
    protected AppiumDriver driver;
    protected WaitUtil waitUtil;
    protected AndroidAction androidAction;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        // Every page object that's created will
        // automatically get its own WaitUtil instance.
        this.waitUtil = new WaitUtil(driver);
        this.androidAction = new AndroidAction((AndroidDriver) driver);
    }

}
