package nhi.huynh;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.nhihuynh.drivers.DriverFactory;
import org.nhihuynh.pageObjects.android.FormPage;
import org.nhihuynh.utils.AppiumUtil;
import org.nhihuynh.utils.ConfigUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public AppiumDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        Properties properties = ConfigUtil.loadConfig();
        service = AppiumUtil.startAppiumService(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
        this.driver = DriverFactory.getDriver("Android", properties.getProperty("android_device_name"), service.getUrl(), System.getProperty("user.dir") + properties.getProperty("android_app"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(this.driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

        if (this.service != null) {
            this.service.stop();
        }

    }

}
