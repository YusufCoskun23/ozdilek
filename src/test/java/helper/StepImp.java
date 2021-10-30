package helper;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class StepImp extends BaseTest {
    public Logger log = Logger.getLogger(String.valueOf(BaseTest.class));
    public StepImp(){
        PageFactory.initElements(appiumDriver,this);
        wait=new WebDriverWait(appiumDriver,30);
    }

    @Step("<element>'ine sahip olan alan <text>'i içeriyor mu kontrol et")
    public void elementTextControlWithId(String element, String text) throws Exception {
        WebElement control=appiumDriver.findElement(By.id(element));
        String elementText=control.getText();
        Assert.assertEquals(elementText,text);
        log.info(text + "kontrolu basarili");
    }

    @Step("<saniye> saniye bekle")
    public void wait(int saniye) throws Exception {
        TimeUnit.SECONDS.sleep(saniye);
    }

    @Step("<element>'ine tikla")
    public void clickWithId(String element) throws Exception {
        appiumDriver.findElement(By.id(element)).click();
        log.info("elemente tiklandi");
    }

    @Step("<element>'ine xpath ile tikla")
    public void clickWithXpath(String element) throws Exception {
        appiumDriver.findElement(By.xpath(element)).click();
        log.info("elemente tiklandi");
    }

    @Step("<element> xpathine sahip olan alan <text>'i içeriyor mu kontrol et")
    public void elementTextControlWitXpath(String element, String text) throws Exception {
        WebElement control=appiumDriver.findElement(By.xpath(element));
        String elementText=control.getText();
        Assert.assertEquals(elementText,text);
        log.info(text + "kontrolu basarili");
    }

    @Step("<int> kere asagiya scroll yap")
    public void scrollDown(int scrollNumber) {
        int x = appiumDriver.manage().window().getSize().width / 2;
        int start_y = (int) (appiumDriver.manage().window().getSize().height * 0.8);
        int end_y = (int) (appiumDriver.manage().window().getSize().height * 0.2);
        for (int i = 1; i <= scrollNumber; i++) {
            scrollFunction(x, start_y, end_y);
        }
    }

    public void scrollFunction(int x, int start_y, int end_y) {
        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point(x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, end_y))
                .release().perform();
    }

    @Step("<by> ile rastgele urun sec ve tikla")
    public void randomProductSelect(String by) {
        Random rand = new Random();
        MobileElement product=findListByID(by).get(rand.nextInt(findListByID(by).size()));
        log.info(product.getText()+ "adindaki rastgele urun secildi");
        product.click();
    }

    @Step("<by> elementlerini bul")
    public List<MobileElement> findListByID(String by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(by)));
        return appiumDriver.findElements(By.id(by));
    }

    @Step("<element> elementi sayfada bulunuyor mu kontrol et")
    public void displayElementControl(String element) throws Exception {
       boolean control= appiumDriver.findElement(By.id(element)).isDisplayed();
        Assert.assertEquals(control,true);
        log.info("Element sayfada bulunuyor");
    }
    @Step("<element> elementli alana <text> textini yaz")
    public void sendKeysElement(String element,String text) throws Exception {
        appiumDriver.findElement(By.id(element)).sendKeys(text);
        log.info(text +"degeri alana yazildi");
    }

}