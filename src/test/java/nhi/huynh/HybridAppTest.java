package nhi.huynh;

import org.nhihuynh.pageObjects.android.CartPage;
import org.nhihuynh.pageObjects.android.FormPage;
import org.nhihuynh.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HybridAppTest extends BaseTest{

    @Test
    public void PurchaseAmountTest() throws InterruptedException {
        FormPage formPage = new FormPage(this.driver);
        formPage.setCountry("Armenia");
        formPage.setGender("Female");
        formPage.setNameField("nhihuynh");
        formPage.letShop();


        ProductCatalogue productCatalogue = new ProductCatalogue(this.driver);
        productCatalogue.addProductToCartByIndex(0);
        productCatalogue.addProductToCartByIndex(1);
        productCatalogue.viewCart();

        CartPage cartPage = new CartPage(this.driver);


        Assert.assertEquals(cartPage.getTotalPurchaseAmount(), cartPage.calculateTotalPrice());
        cartPage.clickTermBtn();
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
