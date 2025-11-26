package org.nhihuynh.pageObjects.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.nhihuynh.utils.IOSAction;
import org.nhihuynh.utils.WaitUtil;

public class IOSBasePage {
    protected AppiumDriver driver;
    protected WaitUtil waitUtil;
    protected IOSAction iosAction;

    public IOSBasePage(AppiumDriver driver) {
        this.driver = driver;
        // Every page object that's created will
        // automatically get its own WaitUtil instance.
        this.waitUtil = new WaitUtil(driver);
        this.iosAction = new IOSAction((IOSDriver) driver);
    }
}
