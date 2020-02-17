package base;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;
    private Properties configProps = new Properties();
    private String environment;
    private String webURL;
    private String webUsername;
    private String webPassword;

    public static Configuration getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
    }

    public Configuration() {

        InputStream is = null;
        try {
            is = ClassLoader.getSystemResourceAsStream("config.properties");
            Reader reader = new InputStreamReader(is, "UTF-8");
            configProps.load(reader);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            this.environment = System.getProperties().getProperty("environment");
            this.webURL = configProps.getProperty("web.URL");
            this.webUsername = configProps.getProperty("web.Username");
            this.webPassword = configProps.getProperty("web.Password");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getEnvironment() {
        return environment;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getWebUsername() {
        return webUsername;
    }
    public String getWebPassword() {
        return webPassword;
    }

}
