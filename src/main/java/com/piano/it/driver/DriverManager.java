package com.piano.it.driver;

import com.piano.it.pages.AbstractPage;
import com.piano.it.step_defs.AbstractStepDefs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DriverManager {

    private static Map<DriverType, WebDriver> driverList = new HashMap<DriverType, WebDriver>();
    private static final DriverType DEFAULT_DRIVER_TYPE = DriverType.CHROME;

    public static WebDriver getDriver(DriverType driverType) {
        if (driverList.containsKey(driverType)) {
            return driverList.get(driverType);
        } else {
            switch (driverType) {
                case CHROME:
                    driverList.put(driverType, new ChromeDriver());
                    break;
                case FIREFOX:
                    driverList.put(driverType, new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Unknown type of driver: " + driverType);
            }
            return getDriver(driverType);
        }
    }

    public static WebDriver getDriver() {
        return getDriver(DEFAULT_DRIVER_TYPE);
    }

    public static void quitDriver(DriverType driverType) {
        if (driverList.get(driverType) != null) {
            driverList.get(driverType).quit();
            driverList.remove(driverType);
        }
    }

    public static void quitDriver() {
        quitDriver(DEFAULT_DRIVER_TYPE);
    }

    public static void quitAllDrivers() {
        for (Iterator<Map.Entry<DriverType, WebDriver>> iterator = driverList.entrySet().iterator();
             iterator.hasNext(); ) {
            iterator.next().getValue().quit();
            iterator.remove();
        }
        AbstractStepDefs.currentPage = null;
    }
}
