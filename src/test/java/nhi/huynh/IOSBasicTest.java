package nhi.huynh;

import org.nhihuynh.pageObjects.ios.AlertViewPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSBasicTest extends IOSBaseTest{
    @Test
    public void BasicsTest() {

        AlertViewPage alertViewPage = this.homePage.clickOnAlertViews();
        String actualMsg = alertViewPage.getConfirmMsg();
        Assert.assertEquals(actualMsg, "A message should be a short, complete sentence");
        alertViewPage.acceptMsg();
    }
}
