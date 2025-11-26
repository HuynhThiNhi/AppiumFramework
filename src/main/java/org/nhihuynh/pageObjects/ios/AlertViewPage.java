package org.nhihuynh.pageObjects.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViewPage extends IOSBasePage{

    public AlertViewPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Confirm / Cancel\"`]")
    private WebElement confirmMenuItem;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"Confirm\"`]")
    private WebElement acceptPopup;

    /*
       predicate use in filtering based on label, name, value, When combining multiple conditions
       [c]: case-insensitive search
       * */
//        driver.findElement(AppiumBy.iOSNsPredicateString("name == 'Confirm'")).click();
//        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'Confirm'")).click();
//        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND value BEGINSWITH[c] 'Confirm'")).click();
    @iOSXCUITFindBy(iOSNsPredicate = "value BEGINSWITH[c] 'A message'")
    private WebElement confirmMsg;

    public String getConfirmMsg() {
        this.confirmMenuItem.click();
        return this.confirmMsg.getText();
    }

    public void acceptMsg() {
        this.acceptPopup.click();
    }

}
