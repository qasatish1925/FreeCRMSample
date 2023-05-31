package Base;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PathConstants {

    public static final String betaProp = System.getProperty("user.dir") + "/src/main/java/Resources/beta.properties";
    public static final String prodProp = System.getProperty("user.dir") + "/src/main/java/Resources/prod.properties";
    public static final String browserStackProp = System.getProperty("user.dir") + "/src/main/java/Resources/prodBrowserStack.properties";
    public static final String AUTOMATE_USERNAME = "dummyusername";
    public static final String AUTOMATE_ACCESS_KEY = "dummypassword";
    public static final String browserStackURL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static OutputStream eisBeta;
    static {
        try {
            eisBeta = new FileOutputStream(System.getProperty("user.dir") + "/../target/allure-results/environment.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static OutputStream eisProd;
    static {
        try {
            eisProd = new FileOutputStream(System.getProperty("user.dir") + "/../target/allure-results/environment.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
