package org.nhihuynh.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtil {
    public static AppiumDriverLocalService startAppiumService(String address, int port) {
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withIPAddress(address)
                .usingPort(port)
                .build();

        service.start();
        return service;
    }
}
