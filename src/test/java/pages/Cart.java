package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Cart extends BasePage {
    public Cart(WebDriver driver) {
        super(driver);
    }
    private By item=By.xpath("//*[@id=\"basketContent\"]//*[@class=\"description basketlist-productinfo-description\"]");

    /**Ürünün eklenip eklenmediğini kontrol eder.*/
    public Cart checkItem(){
        threadSleep(1);
        System.out.println("PRO : "+ProductDetail.productName);
        System.out.println("PRO : "+getWebElementText(item));
        Assert.assertTrue(getWebElementText(item).contains(ProductDetail.productName),"Ürün aynı değil");
        return this;
    }
}
