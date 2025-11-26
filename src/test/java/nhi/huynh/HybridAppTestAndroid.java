package nhi.huynh;

import org.nhihuynh.pageObjects.android.CartPage;
import org.nhihuynh.pageObjects.android.FormPage;
import org.nhihuynh.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HybridAppTestAndroid extends AndroidBaseTest {

    @Test
    public void PurchaseAmountTest() throws InterruptedException {
        FormPage formPage = new FormPage(this.driver);
        formPage.setCountry("Armenia");
        formPage.setGender("Female");
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
}
