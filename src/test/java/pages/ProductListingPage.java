package pages;

import base.BasePage;
import base.DriverFactory;
import edu.emory.mathcs.backport.java.util.LinkedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProductListingPage extends BasePage {
    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

    private By images = By.xpath("//*[@id=\"boutique-detail-app\"]//*[@class=\"image-container\"]//img[1]");

    /**Ürünlerin imajlarını kontrol eder.*/
    public ProductListingPage checkIcons() throws IllegalAccessException, InstantiationException {
            threadSleep(1);
            List<WebElement> imageList=getDriver().findElements(images) ;
        System.out.println(imageList.size());
        Assert.assertTrue(imageList.size()==20,"İmaj sayısı doğru değil.");
        return this;
    }

    /**Ürün detaya gider.*/
    public ProductDetail clickProduct() throws IllegalAccessException, InstantiationException {
        clickObject(images,"Ürüne tıklandı.");
        return new ProductDetail(DriverFactory.class.newInstance().getDriver().get());
    }
}

