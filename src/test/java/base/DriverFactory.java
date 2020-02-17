package base;


import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private DriverFactory instance;
    protected static ThreadLocal<WebDriver> driver;
    private static Set<WebDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static Configuration configurationGet = Configuration.getInstance();
    private static DesiredCapabilities capabilities;
    private final static Logger logger = Logger.getLogger(Class.class);
    public static String excelDataPathForPlatform;
    Map<String, Object> prefs = new HashMap<String, Object>();

    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private SafariOptions safariOptions;
    private InternetExplorerOptions internetExplorerOptions;

    void chromeOptions() {

        chromeOptions = new ChromeOptions();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("start-maximized");
    }

    void firefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("test-type");
        firefoxOptions.addArguments("disable-popup-blocking");
        firefoxOptions.addArguments("ignore-certificate-errors");
        firefoxOptions.addArguments("disable-translate");
        firefoxOptions.addArguments("start-maximized");
    }

    void safariOptions() {
        safariOptions = new SafariOptions();
    }

    void internetExplorerOptions() {
        internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
        internetExplorerOptions.requireWindowFocus();
    }

    void driverSettingsLocal() {
        if (configurationGet.getEnvironment().equals("webChrome")) {
            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.getCurrent());

            driver = ThreadLocal.withInitial(() -> {
                ChromeDriver chromeDriver = new ChromeDriver(capabilities);
                drivers.add(chromeDriver);
                return chromeDriver;
            });
        } else if (configurationGet.getEnvironment().equals("webFirefox")) {
            capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.getCurrent());

            driver = ThreadLocal.withInitial(() -> {
                FirefoxDriver firefoxDriver = new FirefoxDriver(capabilities);
                drivers.add(firefoxDriver);
                return firefoxDriver;
            });
        } else if (configurationGet.getEnvironment().equals("webSafari")) {
            capabilities = DesiredCapabilities.safari();
            capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setBrowserName("safari");
            capabilities.setPlatform(Platform.getCurrent());

            driver = ThreadLocal.withInitial(() -> {
                SafariDriver safariDriver = new SafariDriver(capabilities);
                drivers.add(safariDriver);
                return safariDriver;
            });
        } else if (configurationGet.getEnvironment().equals("webInternetExplorer")) {
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("se:ieOptions", internetExplorerOptions);
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setBrowserName("internetExplorer");
            capabilities.setPlatform(Platform.getCurrent());

            driver = ThreadLocal.withInitial(() -> {
                InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver(capabilities);
                drivers.add(internetExplorerDriver);
                return internetExplorerDriver;
            });
        }
    }

    private void selectPath(Platform platform) {
        String browser;

        if ("CHROME".equalsIgnoreCase(capabilities.getBrowserName())) {
            browser = "webdriver.chrome.driver";
            switch (platform) {
                case MAC:
                    System.setProperty(browser, "properties/driver/chromedriver_mac");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "/src/test/resources/";
                    break;
                case WIN10:
                case WIN8:
                case WIN8_1:
                case WINDOWS:
                case VISTA:
                    System.setProperty(browser, "properties/driver/chromedriver_windows.exe");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    break;
                case LINUX:
                    System.setProperty(browser, "properties/driver/chromedriver_linux");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "//src//test//resources//";
                    break;
                default:
                    logger.info("PLATFORM DOES NOT EXISTS");
                    break;
            }
        } else if ("FIREFOX".equalsIgnoreCase(capabilities.getBrowserName())) {
            browser = "webdriver.gecko.driver";
            switch (platform) {
                case MAC:
                    System.setProperty(browser, "properties/driver/geckodriver_mac");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "//src//test//resources//";
                    break;
                case WIN10:
                case WIN8:
                case WIN8_1:
                case WINDOWS:
                case VISTA:
                    System.setProperty(browser, "properties/driver/geckodriver_windows.exe");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    break;
                case LINUX:
                    System.setProperty(browser, "properties/driver/geckodriver_linux");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "//src//test//resources//";
                    break;
                default:
                    logger.info("PLATFORM DOES NOT EXISTS");
                    break;
            }
        } else if ("SAFARI".equalsIgnoreCase(capabilities.getBrowserName())) {
            browser = "webdriver.SAFARI.driver";
            if (platform == platform.MAC) {
                System.setProperty(browser, "usr/bin/safaridriver");
                excelDataPathForPlatform = System.getProperty("user.dir") + "//src//test//resources//";
            } else {
                logger.info("PLATFORM DOES NOT EXISTS");
            }
        } else if ("INTERNETEXPLORER".equalsIgnoreCase(capabilities.getBrowserName())) {
            browser = "webdriver.IEDriverServer.driver";
            switch (platform) {
                case WIN10:
                case WIN8:
                case WIN8_1:
                case WINDOWS:
                case VISTA:
                    System.setProperty(browser, "properties/driver/IEDriverServer.exe");
                    excelDataPathForPlatform = System.getProperty("user.dir") + "\\src\\test\\resources\\";
                    break;
            }
        }
    }

    protected void commonBeforeMethodSetProperty() {
        selectPath(capabilities.getPlatform());
    }

    protected void commonBeforeMethodImplicitlyWait() {
        driver.get().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    protected void commonBeforeMethodMaximize() {
        if (capabilities.getPlatform().toString().equals("MAC")) {
            //driver.get().manage().window().fullscreen();
            driver.get().manage().window().setSize(new Dimension(1280, 900));
        } else {
            driver.get().manage().window().maximize();
        }
    }

    private String getIP() {
        String IP = "";

        List<InetAddress> addrList = new ArrayList<InetAddress>();
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        InetAddress localhost = null;

        try {
            localhost = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        while (interfaces.hasMoreElements()) {
            NetworkInterface ifc = interfaces.nextElement();
            Enumeration<InetAddress> addressesOfAnInterface = ifc.getInetAddresses();

            while (addressesOfAnInterface.hasMoreElements()) {
                InetAddress address = addressesOfAnInterface.nextElement();

                if (!address.equals(localhost) && !address.toString().contains(":")) {
                    addrList.add(address);
                    IP = address.getHostAddress();
                }
            }
        }
        return IP;
    }

    public ThreadLocal<WebDriver> getDriver() {
        return driver;
    }

    public Set<WebDriver> getDrivers() {
        return drivers;
    }

    public void setDrivers(WebDriver driver) {
        drivers.add(driver);
    }

    public DriverFactory getInstance(){
        return instance;
    }
}


