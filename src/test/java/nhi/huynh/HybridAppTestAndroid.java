package nhi.huynh;

import io.appium.java_client.android.Activity;
import org.nhihuynh.pageObjects.android.CartPage;
import org.nhihuynh.pageObjects.android.FormPage;
import org.nhihuynh.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class HybridAppTestAndroid extends AndroidBaseTest {

    @BeforeMethod
    public void preSetUp() {
//        wait for the UI to be fully loaded before interacting.
        driver.startActivity(new Activity(
                "com.androidsample.generalstore",
                "com.androidsample.generalstore.MainActivity"
        ));

//        not wait for the UI to be fully loaded before interacting. -> causing NoFoundSuchElement
//        ((JavascriptExecutor) this.driver).executeScript("mobile: startActivity", ImmutableMap.of(
//                "intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
//        ));


    }

    @Test
    public void PurchaseAmountTest() throws InterruptedException {
        FormPage formPage = new FormPage(this.driver);
        formPage.setGender("Female");
        formPage.setCountry("Armenia");

        formPage.setNameField("nhihuynh");
        ProductCatalogue productCatalogue = formPage.submitForm();

        productCatalogue.addProductToCartByIndex(0);
        productCatalogue.addProductToCartByIndex(1);
        CartPage cartPage = productCatalogue.viewCart();

        Assert.assertEquals(cartPage.getTotalPurchaseAmount(), cartPage.calculateTotalPrice());
        cartPage.acceptTermBtn();
        cartPage.selectSendingMail();
        cartPage.confirmOrder();

//        wait.until(d -> {
//            Set<String> contexts = driver.getContextHandles();
//            System.out.println("Available contexts: " + contexts);
//            return contexts.stream().anyMatch(c -> c.contains("WEBVIEW"));
//        });
//
//
//
//        driver.context("WEBVIEW");
//        driver.findElement(By.name("q")).sendKeys("huynh thi nhi");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        driver.context("NATIVE_APP");

    }

    @Test
    public void fillFormTest() throws InterruptedException {
        FormPage formPage = new FormPage(this.driver);
        formPage.setCountry("Armenia");
        formPage.setGender("Female");
        formPage.setNameField("nhihuynh");
        ProductCatalogue productCatalogue = formPage.submitForm();

        productCatalogue.addProductToCartByIndex(0);
        productCatalogue.addProductToCartByIndex(1);
        CartPage cartPage = productCatalogue.viewCart();

        Assert.assertEquals(cartPage.getTotalPurchaseAmount(), cartPage.calculateTotalPrice());

    }
}
