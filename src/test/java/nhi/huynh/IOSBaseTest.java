package nhi.huynh;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.nhihuynh.pageObjects.ios.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSBaseTest {
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws MalformedURLException {

        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4724)
                .build();

        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14 Pro"); // must be required
        options.setApp("/Users/nhihuynh/qa/AppiumClient/src/test/resources/UIKitCatalog.app"); // // must be required
//        options.setApp("/Users/nhihuynh/qa/AppiumClient/src/test/resources/TestApp 3.app");
        options.setAutomationName("XCUITest");
        options.setPlatformVersion("16.4");  // must be required
        driver = new IOSDriver(new URL("http://127.0.0.1:4724"), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(this.driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
