package org.nhihuynh.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidBasePage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement cartTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> products;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPurchaseAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termBtn;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeTermOfServiceBtn;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement sendingMailCheckbox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement confirmOrderBtn;


    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        try {
            waitUtil.waitForElementToBeVisible(this.cartTitle);
        } catch (TimeoutException e) {
            // If the header doesn't appear, we aren't on the right page.
            // Fail fast with a clear error message.
            throw new IllegalStateException("Failed to load Cart Page. Header 'My Cart' not found.");
        }

    }

    public double calculateTotalPrice() {
        double amount = 0;
        for (int i = 0; i < this.products.size(); i++) {
            amount += Double.parseDouble(this.products.get(i).getText().substring(1));
        }
        return amount;
    }

    public double getTotalPurchaseAmount() {
        this.totalPurchaseAmount.getText();
        return Double.parseDouble(totalPurchaseAmount.getText().substring(2));
    }

    public void acceptTermBtn() {
        this.androidAction.longPressAction(termBtn);
        this.closeTermOfServiceBtn.click();
    }

    public void selectSendingMail() {
        this.sendingMailCheckbox.click();
    }

    public void confirmOrder() {
        this.confirmOrderBtn.click();
    }

}
