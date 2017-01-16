package com.piano.it.pages.company_page;

import com.piano.it.pages.AbstractPage;
import com.piano.it.pages.company_search_page.CompanySearchMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.ArrayList;
import java.util.List;

public class CompanyPage extends AbstractPage {

    @FindBy(xpath = "//*[contains(@class,'post-tag ')]")
    private List<HtmlElement> tags;

    @FindBy(xpath = "//*[contains(@class,'returntolist')]")
    private Link backToListLink;

    @FindBy(xpath = "//*[@data-company-section='company-info']/h1")
    private HtmlElement companyName;

    @FindBy(xpath = "//*[@class='container']")
    private HtmlElement contentContainer;

    public CompanyPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageCompletelyLoaded() {

    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public boolean isCorrectPageOpened() {
        waitForJSandJQueryToLoad();
        return backToListLink.isDisplayed();
    }

    public String getCompanyName() {
        if (companyName.exists() && companyName.isDisplayed()) {
            return companyName.getText().trim();
        } else {
            return "Company name not found";
        }
    }

    public int getTagsAmount() {
        return tags.size();
    }

    public String getTag(int i) {
        return tags.get(i).getText().trim();
    }

    public List<String> getTags() {
        List<String> tagsStringList = new ArrayList<>();
        for (HtmlElement tag : tags) {
            tagsStringList.add(tag.getText().trim());
        }
        return tagsStringList;
    }

    public CompanySearchMainPage goBackToList() {
        backToListLink.click();
        waitForJSandJQueryToLoad();
        return initPage(CompanySearchMainPage.class);
    }

    public boolean pageContainsKeyword(String expectedName) {
        return contentContainer.getText().toLowerCase().contains(expectedName.toLowerCase());
    }
}
