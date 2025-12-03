package org.nhihuynh.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class FormPage extends AndroidBasePage {
//    AndroidDriver driver;

    /**
     * removing line  super(driver) causing error "There is no parameterless constructor available in 'AndroidAction'" because
     * Java tries to call the parent constructor automatically, like this: super();
     * But your parent class (androidAction) does not have a constructor with no arguments.
     * */
    public FormPage(AppiumDriver driver) {
        super(driver);
//        this.driver = driver;
        // THE CRITICAL LINE:
        // We use AppiumFieldDecorator to tell PageFactory how to read
        // @AndroidFindBy and @iOSXCUITFindBy annotations.
        // 2. The Decorator handles the logic.
        // It checks: "Is this driver Android? Use @AndroidFindBy. Is it iOS? Use @iOSXCUITFindBy."
        // ✅ Waits up to 5 seconds for the element to exist in DOM
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement spinnerCountry;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submitFormBtn;


    public void setNameField(String name) {
        nameField.sendKeys(name);
    }

    public void setCountry(String name) throws InterruptedException {
        spinnerCountry.click();
        androidAction.scrollToText(name);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + name + "']")).click();
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("Female")) {
            femaleOption.click();
        } else {
            maleOption.click();
        }
    }

    public ProductCatalogue submitForm() {
        submitFormBtn.click();
        return new ProductCatalogue((AndroidDriver) this.driver);
    }

}
