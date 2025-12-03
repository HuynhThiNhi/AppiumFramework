package nhi.huynh;

import com.fasterxml.jackson.core.type.TypeReference;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.nhihuynh.models.User;
import org.nhihuynh.pageObjects.android.CartPage;
import org.nhihuynh.pageObjects.android.ProductCatalogue;
import org.nhihuynh.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class HybridAppTestAndroid extends BaseTest {

    @BeforeMethod
    public void preSetUp() {
//        wait for the UI to be fully loaded before interacting.
        ((AndroidDriver)driver).startActivity(new Activity(
                "com.androidsample.generalstore",
                "com.androidsample.generalstore.MainActivity"
        ));

//        not wait for the UI to be fully loaded before interacting. -> causing NoFoundSuchElement
//        ((JavascriptExecutor) this.driver).executeScript("mobile: startActivity", ImmutableMap.of(
//                "intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
//        ));


    }

    @Test(dataProvider = "getData")
    public void PurchaseAmountTest(User user) throws InterruptedException {
        formPage.setCountry(user.getCountry());
        formPage.setGender(user.getName());
        formPage.setNameField(user.getName());
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

    @Test(dataProvider = "getData")
    public void fillFormTest(User user) throws InterruptedException {
        formPage.setCountry(user.getCountry());
        formPage.setGender(user.getName());
        formPage.setNameField(user.getName());
        ProductCatalogue productCatalogue = formPage.submitForm();

        productCatalogue.addProductToCartByIndex(0);
        productCatalogue.addProductToCartByIndex(1);
        CartPage cartPage = productCatalogue.viewCart();

        Assert.assertEquals(cartPage.getTotalPurchaseAmount(), cartPage.calculateTotalPrice());

    }

    @DataProvider
    public  Object[][] getData() {
        List<User> dataList = JsonUtil.getJsonData(System.getProperty("user.dir") + "/src/main/resources/testdata/eCommerce.json", new TypeReference<List<User>>() {
        });

        // Convert List to 2D Array (Required by TestNG)
        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
}
