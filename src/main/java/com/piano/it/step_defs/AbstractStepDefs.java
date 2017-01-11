package com.piano.it.step_defs;

import com.piano.it.driver.DriverManager;
import com.piano.it.pages.AbstractPage;
import com.piano.it.pages.companies_main_page.CompanySearchMainPage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class AbstractStepDefs {

    private WebDriver driver;
    private final String SEARCH_PAGE_URL = "https://stackoverflow.com/jobs/companies";


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
                return constructor.newInstance(getDriver());
            } catch (NoSuchMethodException e) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T extends AbstractPage> T openPage(Class<T> clazz) {
        T page = initPage(clazz);
        getDriver().get(page.getUrl());
        page.waitUntilPageCompletelyLoaded();
        return page;
    }

    protected CompanySearchMainPage openCompaniesSearchPage(){
        return openPage(CompanySearchMainPage.class);
//        if(!SEARCH_PAGE_URL.equals(getDriver().getCurrentUrl())){
//            return openPage(CompanySearchMainPage.class);
//        } else {
//            return initPage(CompanySearchMainPage.class);
//        }
    }
}
