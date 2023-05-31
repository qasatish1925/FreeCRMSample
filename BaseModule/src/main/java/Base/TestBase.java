package Base;

import Utilities.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class TestBase extends PathConstants
{
    public static Properties prop;
    public static WebDriver driver;
    public static String env = System.getProperty("env");
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
    public static FileInputStream fis = null;
    public static DesiredCapabilities caps;
    public static Utils ut = new Utils();

    public TestBase()
    {
        prop = new Properties();
        try{
                if(env.equalsIgnoreCase("beta"))
            {
                fis = new FileInputStream(betaProp);
            }
            else if(env.equalsIgnoreCase("prod"))
            {
                fis = new FileInputStream(prodProp);
            }
            else if(env.equalsIgnoreCase("browserstack"))
            {
                fis = new FileInputStream(browserStackProp);
            }
            prop.load(fis);
        }
        catch (IOException e) {
            e.printStackTrace();
            }
    }

    public static void initialization()
    {
        String browserName = prop.getProperty("browser");
        String url = prop.getProperty("url");
        caps = new DesiredCapabilities();
        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("browerStackChrome"))
        {
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("browser", "Chrome");
            caps.setCapability("browser_version", "latest");
            caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
            caps.setCapability("build", "BStack Build Number 1");
            caps.setCapability("project", "Sample sandbox project");
            try {
                driver = new RemoteWebDriver(new URL(browserStackURL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
            driver.get(url);
            tDriver.set(driver);
    }

    public static synchronized WebDriver getDriver() {

        return tDriver.get();
    }

    public static void cleanup()
    {
        driver.quit();;
    }
}
