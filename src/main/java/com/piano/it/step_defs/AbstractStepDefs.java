package com.piano.it.step_defs;

import com.piano.it.driver.DriverManager;
import com.piano.it.pages.AbstractPage;
import com.piano.it.pages.company_search_page.CompanySearchMainPage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class AbstractStepDefs {

    private WebDriver driver;
    public static AbstractPage currentPage;


    protected WebDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver();
        }
        return driver;
    }

    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected <T extends AbstractPage> T initPage(Class<T> clazz) {
        try {
            try {
                Constructor<T> constructor = clazz.getConstructor(WebDriver.class);
                T page = constructor.newInstance(getDriver());
                setCurrentPage(page);
                return page;
            } catch (NoSuchMethodException e) {
                T page = clazz.newInstance();
                setCurrentPage(page);
                return page;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T extends AbstractPage> T openPage(Class<T> clazz) {
        if (currentPage == null || currentPage.getClass() != clazz) {
            T page = initPage(clazz);
            getDriver().get(page.getUrl());
            page.waitUntilPageCompletelyLoaded();
            return page;
        } else {
            return (T) currentPage;
        }

    }

    protected CompanySearchMainPage openCompanySearchPage() {
        return openPage(CompanySearchMainPage.class);
    }

    private void setCurrentPage(AbstractPage abstractPage) {
        if (currentPage == null
                || abstractPage.getClass() != currentPage.getClass()) {
            currentPage = abstractPage;
        }
    }
}
