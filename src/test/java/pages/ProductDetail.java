package pages;

import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetail extends BasePage {

    public ProductDetail(WebDriver driver) {
        super(driver);
    }

    private By addToCart=By.xpath("//*[@id=\"product-detail-app\"]//*[@class=\"pr-in-btn add-to-bs\"]");
    private By chooseNum=By.xpath("//*[@id=\"product-detail-app\"]//*[@class=\"vrn-item\"]/span[1]");
    private By cart=By.id("myBasketListItem");
    private By product=By.xpath("//*[@id=\"product-detail-app\"]//*[@class=\"pr-in-nm\"]");

    static String productName= "";

    /**Ürün numarasını seçer ve ekler.*/
        public ProductDetail chooseNumAndAddToCart() throws IllegalAccessException, InstantiationException {
            threadSleep(1);
            productName = getWebElementText(product);
            System.out.println("Product name" + getWebElementsText(product));
            clickObject(addToCart,"<Sepete ekle> butonuna tıklandı.");
            clickObject(chooseNum,"Numara seçmeye tıklandı.");
        return new ProductDetail(DriverFactory.class.newInstance().getDriver().get());
        }

    /**Sepetime tıklar.*/
    public Cart clickCart() throws IllegalAccessException, InstantiationException {
            threadSleep(3);
            clickObject(cart,"<Sepet> e tıklandı");
            try {
                clickObject(cart,"<Sepet> e tıklandı");
            }catch (Exception e){
                e.getMessage();
            }
            return new Cart(DriverFactory.class.newInstance().getDriver().get());
        }


}
