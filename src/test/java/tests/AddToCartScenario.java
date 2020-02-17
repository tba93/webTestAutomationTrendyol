package tests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.annotations.Test;
import pages.MainPage;

public class AddToCartScenario extends BaseTest {


    /**
     Senaryo :

     -Sisteme kayıtlı bir kullanıcı ile login olunur.
     -Login olunup olunmadığı Hesabım sekmesinden kontrol edilir.
     -Login olduktan sonra ana sayfadaki tab’lere tıklanıp yüklenmeyen butik imajları kontrol edilir.
     -Kadın butiğine girilir.
     -Listeleme sayfası açılır ve ilk gelen 20 ürünün imajları listeye çekilerek kontrol edilir.
     -Listeleme sayfasındaki herhangi bir ürüne tıklanır.
     -Ardından Ürün Detaya gidilir.
     -Ürün için numara seçilir ve sepete ekle butonuna basılır.
     -Sepete eklenen ürünün doğru olup olmadığı kontrol edilir.

     * */
    @Test
    public void loginTestPass() throws IllegalAccessException, InstantiationException {

        MainPage start = new MainPage(DriverFactory.class.newInstance().getDriver().get());

          start
                .navigateAndGoMainPage()
                .checkTitle()
                .closePopUp()
                .clickMyAccountMenuButton()
                .fillLoginDataAndClickLoginButton()
                .closePopUpAfterLogin()
                .checkUser()
                .clickWomanTab()
                .checkWomanTab()
                .clickManTab()
                .checkManTab()
                .clickChildrenTab()
                .checkChildrenTab()
                .clickShoesTab()
                .checkShoesTab()
                .clickClockTab()
                .checkClockTab()
                .clickCosmeticTab()
                .checkCosmeticTab()
                .clickHomeTab()
                .checkHomeTab()
                .clickElectronicsTab()
                .checkElectronicsTab()
                .clickMarketTab()
                .checkMarketTab()
                .clickWomanTab()
                .clickListingPage()
                .checkIcons()
                .clickProduct()
                .chooseNumAndAddToCart()
                .clickCart()
                .checkItem()
                ;
    }
}

