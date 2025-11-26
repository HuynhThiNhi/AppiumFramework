package org.nhihuynh.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidBasePage {
//    AndroidDriver driver;

    /**
     * removing line  super(driver) causing error "There is no parameterless constructor available in 'AndroidAction'" because
     * Java tries to call the parent constructor automatically, like this: super();
     * But your parent class (androidAction) does not have a constructor with no arguments.
     * */
    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
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

    public void setCountry(String name) {
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
