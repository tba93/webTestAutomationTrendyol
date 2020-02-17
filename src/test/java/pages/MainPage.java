package pages;

import base.BasePage;
import base.DriverFactory;
import base.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static String email=Configuration.getInstance().getWebUsername();
    private static String password=Configuration.getInstance().getWebPassword();

    private By myAccountMenuButton = By.id("accountBtn");
    private By womanTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/kadin']");
    private By manTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/erkek']");
    private By childrenTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/cocuk']");
    private By shoesAndBagTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/ayakkabi--canta']");
    private By clockTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/saat--aksesuar']");
    private By cosmeticTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/kozmetik']");
    private By homeAndLifeTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/ev--yasam']");
    private By electronicsTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/elektronik']");
    private By marketTab = By.xpath("//div[@id='navigation-wrapper']//ul[@class='main-nav']//a[@href='/butik/liste/supermarket']");
    private By fancyBox = By.xpath("//a[contains(@title, 'Close') or contains(@title, 'Kapat')]");
    private By fancyBoxAfterLogin = By.className("modal-close");
    private By userEmail = By.id("email");
    private By userPassword = By.id("password");
    private By loginSubmitButton = By.id("loginSubmit");
    private By getUserName = By.xpath("//*[@id=\"logged-in-container\"]/div[1]/div[1]");

    /**
     * trendyol.com sayfasının title'ını kontrol eder.
     */
    public MainPage checkTitle(){
        checkTitle("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");
        return this;
    }

    /**
     * İlk Popup'ı kapatır.
     */
    public MainPage closePopUp(){
       threadSleep(3);
        try {
            clickObject(fancyBox, "<Popup kapat > butonuna tıklandı.");
        }catch (Exception e){
            e.getMessage();
        }
        return this;
    }

    /**
     * Login Sonrası Popup'ı kapatır.
     */
    public MainPage closePopUpAfterLogin(){
        threadSleep(3);

        try {
            clickObject(fancyBoxAfterLogin, "<Popup kapat > butonuna tıklandı.");
        }catch (Exception e){
            e.getMessage();
        }
        return this;
    }

    /**
     * Local için
     * Hesabım butonuna tıklanır
     */
    public MainPage clickMyAccountMenuButton(){
        waitUntilExpectedElement(myAccountMenuButton);
        clickObject(myAccountMenuButton, "<Hesabım> butonuna tıklandı.");
        return this;
    }

    /**
     * kullanıcı emaili ve şifreyi doldurur.
     */
    public MainPage fillLoginDataAndClickLoginButton(){
        threadSleep(3);
        waitUntilExpectedElement(userEmail);
        waitUntilExpectedElement(userPassword);
        fillInputField( userEmail, email );
        fillInputField(userPassword, password);
        clickObject(loginSubmitButton, "Giriş yapa tıklandı.");
        return this;
    }

    /**
     * kullanıcının login olup olmadığını emailini kontrol eder.
     */
    public MainPage checkUser(){
        threadSleep(4);
        hoverElement(myAccountMenuButton, "<Hesabım> butonunun üstüne gelindi.");
        System.out.println(getWebElementsText(getUserName));
        Assert.assertTrue(getWebElementsText(getUserName).contains(email));
        return this;
    }
    /**Kadın butiği tabına tıklar*/
    public BoutiquePage clickWomanTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(womanTab,"<Kadın> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Erkek butiği tabına tıklar*/
    public BoutiquePage clickManTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(manTab,"<Erkek> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Çocuk butiği tabına tıklar*/
    public BoutiquePage clickChildrenTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(childrenTab,"<Çocuk> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Ayakkabı ve Çanta butiği tabına tıklar*/
    public BoutiquePage clickShoesTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(shoesAndBagTab,"<Ayakkabı ve Çanta> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Saat ve Aksesuar butiği tabına tıklar*/
    public BoutiquePage clickClockTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(clockTab,"<Saat ve Aksesuar> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Kozmetik butiği tabına tıklar*/
    public BoutiquePage clickCosmeticTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(cosmeticTab,"<Kozmetik> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Ev ve Yaşam butiği tabına tıklar*/
    public BoutiquePage clickHomeTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(homeAndLifeTab,"<Ev ve Yaşam> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Elektronik butiği tabına tıklar*/
    public BoutiquePage clickElectronicsTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(electronicsTab,"<Elektronik> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
    /**Market butiği tabına tıklar*/
    public BoutiquePage clickMarketTab() throws IllegalAccessException, InstantiationException {
        threadSleep(1);
        clickObject(marketTab,"<Market> butiğine tıklandı.");
        return new BoutiquePage(DriverFactory.class.newInstance().getDriver().get());
    }
}
