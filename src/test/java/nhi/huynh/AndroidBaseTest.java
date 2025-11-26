package nhi.huynh;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.nhihuynh.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidBaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {

        /*
         * Start/stop appium server programmatically
         * */
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4724)
                .build();

        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nhihuynh");
        options.setApp("/Users/nhihuynh/qa/AppiumClient/src/test/resources/General-Store.apk");
        options.setCapability("chromedriverExecutable", System.getProperty("user.dir").concat("/src/test/resources/chromedriver-mac-arm64/chromedriver"));
        options.setCapability("chromedriverUseSystemExecutable", false);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4724"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(this.driver);
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
        service.stop();
    }
}
