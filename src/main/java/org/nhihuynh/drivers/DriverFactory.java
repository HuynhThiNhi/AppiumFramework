package org.nhihuynh.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    /**
     * Creates the driver based on the platform.
     * * @param platformName "ANDROID" or "IOS"
     * @param deviceName   The name of the device (e.g., "Pixel_7_Pro", "iPhone 14 Pro")
     * @return AppiumDriver
     */
    public static AppiumDriver getDriver(String platformName, String deviceName, URL url, String projectPath) throws MalformedURLException {

        if (platformName.equalsIgnoreCase("ANDROID")) {
            return getAndroidDriver(url, deviceName, projectPath);
        } else if (platformName.equalsIgnoreCase("IOS")) {
            return getIOSDriver(url, deviceName, projectPath);
        }

        throw new RuntimeException("Invalid Platform Name: " + platformName);
    }

    // --- Internal Private Methods to keep code clean ---

    private static AndroidDriver getAndroidDriver(URL url, String deviceName, String projectPath) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);

        // Dynamic Path: Works on any machine
        options.setApp(projectPath);

        // Chromedriver Setup
        options.setChromedriverExecutable(System.getProperty("user.dir") + "/src/test/resources/chromedriver-mac-arm64/chromedriver");
        options.setCapability("chromedriverUseSystemExecutable", false);

        // Optional: Add a timeout to prevent flakiness during startup
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        return new AndroidDriver(url, options);
    }

    private static IOSDriver getIOSDriver(URL url, String deviceName, String projectPath) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);

        // iOS requires Platform Version usually, but modern Appium
        // can often detect it from the deviceName or UDID.
        // If you need strict versioning, pass it as a parameter.
        options.setPlatformVersion("16.4");

        options.setAutomationName("XCUITest");

        // Dynamic Path
        options.setApp(projectPath);
        // options.setApp(projectPath + "/src/test/resources/TestApp 3.app");

        // WDA Timeout helps if the iPhone takes a long time to install the WebDriverAgent
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        return new IOSDriver(url, options);
    }
}
