package org.nhihuynh.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends BasePage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement btnCart;

    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addProductToCartByIndex(int idx) {
        if (this.addToCart.size() <= idx) {
            throw new RuntimeException("idx is out of product's size");
        }
        this.addToCart.get(idx).click();
    }

    public void viewCart() {
        this.btnCart.click();
    }



}
