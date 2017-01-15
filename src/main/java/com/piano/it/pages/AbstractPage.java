package com.piano.it.pages;

import com.piano.it.exceptions.CommonTestException;
import com.piano.it.step_defs.AbstractStepDefs;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.lang.reflect.Constructor;

public abstract class AbstractPage {

    private final WebDriver driver;
    private final int DEFAULT_TIMEOUT = 10;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),this);
    }

    public abstract void waitUntilPageCompletelyLoaded();

    public abstract String getUrl();

    public abstract boolean isCorrectPageOpened();

    public WebDriver getDriver() {
        return driver;
    }

    protected void waitForElementVisible(WebElement webElement, int timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementVisible(WebElement webElement){
        waitForElementVisible(webElement, DEFAULT_TIMEOUT);
    }

    protected void waitForElementClickable(int timeout, WebElement... webElements){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        for (WebElement webElement: webElements){
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }
    }

    protected void waitForElementClickable(WebElement... webElements){
        waitForElementClickable(DEFAULT_TIMEOUT, webElements);
    }


    protected  <T extends AbstractPage> T initPage(Class<T> clazz){
        try {
            try {
                Constructor<T> constructor = clazz.getConstructor(WebDriver.class);
                T page = constructor.newInstance(driver);
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

    protected <T extends AbstractPage> T openPage(Class<T> clazz){
        if(!AbstractStepDefs.currentPage.getClass().equals(clazz)){
            T page = initPage(clazz);
            driver.get(page.getUrl());
            page.waitUntilPageCompletelyLoaded();
            if(!page.isCorrectPageOpened()){
                throw new CommonTestException("Incorrect page opened. Expected: " + clazz.getSimpleName()
                        + ". But found: " + driver.getCurrentUrl());
            }
            return page;
        } else {
            return initPage(clazz);
        }
    }

    public void goBack(){
        getDriver().navigate().back();
    }

    private void setCurrentPage(AbstractPage abstractPage){
        if(AbstractStepDefs.currentPage == null || AbstractStepDefs.currentPage != abstractPage){
            AbstractStepDefs.currentPage = abstractPage;
        }
    }

    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor)getDriver()).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                return true;
            }
        };

        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
