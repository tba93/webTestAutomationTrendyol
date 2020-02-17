package pages;

import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoutiquePage extends BasePage {

    private By tabImage = By.xpath("//*[@id=\"browsing-gw-homepage\"]//*[@class='image-container']//img[1]");


    public BoutiquePage(WebDriver driver) {
        super(driver);
    }

    /**Kadın butiğini ve imajını kontrol eder.*/
    public MainPage checkWomanTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Kadın, Moda, Giyim, Stil, Giyim Markaları | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Kadın butiği imajı bulunamadı.");
        }
        System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Erkek butiğini ve imajını kontrol eder.*/
    public MainPage checkManTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Erkek Giyim, Erkek Kıyafetleri, Erkek Modası | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Erkek butiği imajı bulunamadı.");
        }
        System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Çocuk butiğini ve imajını kontrol eder.*/
    public MainPage checkChildrenTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Hamile Giyim, Bebek Kıyafetleri, Çocuk Giyim | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Çocuk butiği imajı bulunamadı.");
        } System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Ayakkabı ve Çanta butiğini ve imajını kontrol eder.*/
    public MainPage checkShoesTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Ayakkabı Al, Ayakkabı Markaları, Çanta Markaları | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Ayakkabı ve Çanta butiğini ve imajı bulunamadı.");
        } System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Saat ve Aksesuar butiği imajını kontrol eder.*/
    public MainPage checkClockTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Kol Saati, Erkek Saat, Bayan Saat ve Gözlük Al | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Saat ve Aksesuar butiği imajı bulunamadı.");
        }   System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Kozmetik butiğini ve imajını kontrol eder.*/
    public MainPage checkCosmeticTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Online Kozmetik, Makyaj Ürünleri, Bakım Ürünleri | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Kozmetik butiği imajı bulunamadı.");
        }  System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Ev ve Yaşam butiğini ve imajını kontrol eder.*/
    public MainPage checkHomeTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Ev Aksesuarları, Ev Eşyaları, Ev ve Yaşam | Trendyol");
        if(getAttribute(tabImage,"src")==""){
            logger("Ev ve Yaşam butiği imajı bulunamadı.");
        } System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Elektronik butiğini ve imajını kontrol eder.*/
    public MainPage checkElectronicsTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");
        if(getAttribute(tabImage,"src")==""){
            logger("Elektronik butiği imajı bulunamadı.");
        }  System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Market butiğini ve imajını kontrol eder.*/
    public MainPage checkMarketTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");
        if(getAttribute(tabImage,"src")==""){
            logger("Market butiği imajı bulunamadı.");
        }  System.out.println(getAttribute(tabImage,"src"));
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Kadın butiğini ve ürün listesine gider.*/
    public ProductListingPage clickListingPage() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        checkTitle("Kadın, Moda, Giyim, Stil, Giyim Markaları | Trendyol");
        clickObject(tabImage,"<Kadın> butiği resmine tıklandı.");
       return new ProductListingPage(DriverFactory.class.newInstance().getDriver().get());
    }

}
