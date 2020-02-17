package base;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import pages.MainPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {

    protected Configuration config;
    private WebDriver driver;
    private final Logger log = Logger.getLogger(BasePage.class);
    private WebDriverWait webDriverWait;
    private static String space = " ";
    private static String non_space = "";
    private String winHandleBefore = null;
    private final static int WAIT_TIME = 30;

    public BasePage(WebDriver driver) {
        this.config = Configuration.getInstance();
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 5, 500);
    }

    public void navigateTo() {
        if (!driver.getCurrentUrl().equals(config.getWebURL())) {
            driver.navigate().to(config.getWebURL());
        }
    }

    public MainPage navigateAndGoMainPage() throws IllegalAccessException, InstantiationException {
        navigateTo();
        return new MainPage(DriverFactory.class.newInstance().getDriver().get());

    }

    public String getAttribute(By by,String string){
       return driver.findElement(by).getAttribute(string);
    }


    protected void threadSleep(int time) {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void threadSleepLong(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void subWindowHandler() {
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
    }

    public void parentWindowHandler() {
        String parentWindowHandler = driver.getWindowHandle();
        driver.switchTo().window(parentWindowHandler);
    }

    public void windowHandler(String title) {
        String mainwindow = driver.getWindowHandle();
        Set<String> handle = driver.getWindowHandles();
        for (String handles : handle) {
            if (!mainwindow.equals(handles)) {
                try {
                    String text = driver.switchTo().window(handles).getPageSource();
                    if (text.contains(title)) {
                        System.out.println("Text found");
                        driver.close();
                        break;
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public void fillInputField(By by, String text, int... index) {
        fillInputField(by, text, false, index);
    }

    private void fillInputField(By by, String text, boolean pressEnter, int... index) {
        WebElement element;

        try {
            element = findElement(by, index);
            if (element.isEnabled()) {
                scrollToCenter(element);
                element.clear();
                element.sendKeys(text);

                if (pressEnter) {
                    element.sendKeys(Keys.ENTER);
                }
            }
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }
    }

    protected WebElement findElement(By by, int... index) {
        WebElement element = null;

        if (index.length == 0) {
            try {
                element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            } catch (Exception e) {
                return null;
            }
        } else if (index[0] >= 0) {
            try {
                element = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).get(index[0]);
            } catch (Exception e) {
                return null;
            }
        }
        highLightElement(element);

        return element;
    }

    protected String getWebElementText(By by, int... index) {
        try {
            return findElement(by, index).getText();
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
            return null;
        }
    }


    protected List<String> getWebElementsText(By by) {
        ArrayList<String> list = new ArrayList<>();

        for (WebElement el : findElementList(by)) {
            highLightElement(el);
            list.add(el.getText());
        }

        return list;
    }


    public String getAttribute(By by, String attributeName, int... index) {
        return findElement(by, index).getAttribute(attributeName);
    }

    private void highLightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "color: red; border: 1px dashed red;");
    }

    protected boolean elementExist(By by, int index) {
        try {
            WebElement el = findElement(by, index);
            return el.isDisplayed();
        } catch (NullPointerException e) {
            return false;
        }
    }

    protected List<WebElement> findElementList(By by) {
        try {
            return driver.findElements(by);
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
            return null;
        }
    }

    protected void executeJSE(String js) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(js);
    }

    protected void executeJsWithIndex(By by, String message, int... index) {
        org.apache.log4j.BasicConfigurator.configure();
        WebElement element = findElement(by, index);
        scrollToCenter(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        logger(message);
    }

    public void waitUntilExpectedElement(By by, int... index) {
        WebElement element;
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            element = findElement(by, index);
            highLightElement(element);
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }
    }


    protected boolean isElementClickableEnable(By by, int... index) {
        WebElement element;
        boolean returnStatement = false;

        try {
            element = findElement(by, index);
            if (element == null) {
                throw new RuntimeException("ELEMENT (" + by + (index.length > 0 ? index[0] : "")
                        + ") NOT EXIST; AUTOMATION DATAS MAY BE INVALID!");
            } else {
                if (element.isEnabled()) {
                    returnStatement = true;
                }
            }
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }

        return returnStatement;
    }

    protected boolean isElementClickable(By by, int... index) {
        WebElement element;
        boolean returnStatement = false;

        try {
            element = findElement(by, index);
            if (element == null) {
                throw new RuntimeException("ELEMENT (" + by + (index.length > 0 ? index[0] : "")
                        + ") NOT EXIST; AUTOMATION DATAS MAY BE INVALID!");
            } else {
                try {
                    element.click();
                    returnStatement = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }

        return returnStatement;
    }


    protected boolean isButtonActive(By by, String classAttribute, int... index) {
        boolean returnStatement = false;
        WebElement button = findElement(by, index);
        String getAttribute = button.getAttribute(classAttribute);

        if (getAttribute == null) {
            returnStatement = true;
        } else if (getAttribute.equals("true")) {
            returnStatement = false;
        }

        return returnStatement;
    }


    protected void logger(String message) {
        if (message != null) {
            log.info(message);
        }
    }

    protected void scrollToCenter(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
    }

    protected void waitUntilExpectedElementClickable(WebElement element) {
        try {
            highLightElement(element);
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NullPointerException e) {
            Assert.fail("Nullpointer Exception for:" + element);
            e.getMessage();
        }
    }

    public void clickObject(By by, String message, int... index) {
        WebElement element;
        org.apache.log4j.BasicConfigurator.configure();

        try {
            element = findElement(by, index);
            if (element == null) {
                throw new RuntimeException("ELEMENT (" + by + (index.length > 0 ? index[0] : "")
                        + ") NOT EXIST; AUTOMATION DATAS MAY BE INVALID!");
            } else {
                scrollToCenter(element);
                waitUntilExpectedElementClickable(element);
                element.click();
                logger("---------------" + message + "---------------");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }
    }

    public void clickElement(By by, String message, int... index) {
        WebElement element;
        org.apache.log4j.BasicConfigurator.configure();
        element = findElement(by, index);
        scrollToCenter(element);
        waitUntilExpectedElementClickable(element);
        element.click();
        logger("---------------" + message + "---------------");
    }

    public void checkTitle(String title) {
        Assert.assertTrue(driver.getTitle().contains(title), "Title bulunamadı.");
    }

    private void waitForJQuery() {
        (new WebDriverWait(driver, WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            }
        });
    }

    private List<WebElement> getObjectByPresentOfElementList(By by) {
        waitForJQuery();
        return (new WebDriverWait(driver, WAIT_TIME))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> getElementsBy(By by) {
        return getObjectByPresentOfElementList(by);
    }

    protected static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");

        return string;
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl().trim();
    }


    protected void scrollToElement(By by, int... index) {
        WebElement element = findElement(by, index);
        scrollToElement(element);
    }

    protected void scrollToElement(WebElement element) {
        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
    }

    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(jsStmt, true);
    }

    protected Object executeJS(String jsStmt, boolean wait) {
        return wait ? getJSExecutor().executeScript(jsStmt, "") : getJSExecutor().executeAsyncScript(jsStmt, "");
    }

    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    private void hoverElement(By by, String message, boolean click, int... index) {
        Actions action = new Actions(driver);
        action.moveToElement(findElement(by, index)).build().perform();
        if (click) {
            clickObject(by, message, index);
        }
    }

    public void hoverElement(By by, String message, int... index) {
        threadSleep(3);
        hoverElement(by, message, false, index);
    }

    protected boolean isElementDisplayed(By by, int... index) {
        boolean found = false;

        try {
            if (findElement(by, index) != null && findElement(by, index).isDisplayed())
                found = true;
        } catch (NullPointerException e) {
            found = false;
        }

        return found;
    }

    public boolean isElementExistPageDocument(By by, int... index) {
        boolean found = false;

        try {
            if (findElement(by, index) != null)
                found = true;
        } catch (NullPointerException e) {
            found = false;
        }

        return found;
    }

    protected boolean isElementExists(By by, int... index) {
        String jsStmt = index.length == 0 || index[0] < 0 ? String.format("return $('%s').size()>0", by)
                : String.format("return $('%s').size()>0 && $('%s').eq(%d).size()>0", by, by,
                index[0]);
        Object result = executeJS(jsStmt, true);

        return result != null && "true".equalsIgnoreCase(result.toString());
    }

    protected boolean isElementInView(By by, int... index) {
        String jsStmt = index.length == 0 || index[0] < 0
                ? String.format("return $('%s').size()>0;", by, by)
                : String.format("return $('%s').size()>0;", by, by, index[0]);
        Object result = executeJS(jsStmt, true);

        return result != null && "true".equalsIgnoreCase(result.toString());
    }

    protected String elementFound(By by, int forFirstIndex, int forICount, String expectedValue) {
        ArrayList<String> actualList;
        actualList = (ArrayList<String>) getWebElementsText(by);
        String returnValue = null;

        for (int i = forFirstIndex; i < actualList.size(); i = i + forICount) {
            if (actualList.get(i).equals(expectedValue)) {
                returnValue = expectedValue;
                break;
            }
        }

        System.out.println("actualList: " + actualList);
        System.out.println("returnValue: " + returnValue);

        return returnValue;
    }

    protected String getNowTime(String dateTypeFormat) {
        Date nowTime = new Date();
        DateFormat dateFormat = null;

        switch (dateTypeFormat) {
            case "yyyy/MM/dd":
                dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case "dd/MM/yyyy":
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                break;
            case "yyyy-MM-dd":
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case "dd-MM-yyyy":
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                break;
            case "yyyy.MM.dd":
                dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                break;
            case "dd.MM.yyyy":
                dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                break;
        }

        assert dateFormat != null;
        return dateFormat.format(nowTime);
    }

    public WebDriver getDriver(){
        return driver;
    }
}
