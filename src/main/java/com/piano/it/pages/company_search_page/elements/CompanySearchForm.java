package com.piano.it.pages.company_search_page.elements;

import com.piano.it.exceptions.CommonTestException;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

@FindBy(id = "company-search-form")
public class CompanySearchForm extends HtmlElement {

    @FindBy(id = "q")
    private TextInput companyNameInput;

    @FindBy(id = "l")
    private TextInput locationInput;

    @FindBy(xpath = ".//button[contains(@class,'advanced-search')]")
    private Button openAdvancedSearchButton;

    @FindBy(xpath = ".//button[@class='search-btn']")
    private Button simpleSearchButton;

    @FindBy(xpath = ".//button[contains(@class,'js-submit')]")
    private Button advancedSearchButton;

    @FindBy(xpath = ".//button[contains(@class,'js-cancel')]")
    private Button cancelAdvancedSearch;

    @FindBy(xpath = "//*[contains(@id,'tagEditor-tl')]")
    private TextInput tagsInput;

    @FindBy(xpath = "//*[@class='pac-item']")
    private List<HtmlElement> searchProposals;

    public void enterCompanyName(String companyName) {
        companyNameInput.clear();
        companyNameInput.sendKeys(companyName);
    }

    public void enterLocations(String locations) {
        locationInput.clear();
        locationInput.sendKeys(locations);
    }

    public void clickSearch() {
        if (simpleSearchButton.isEnabled()) {
            simpleSearchButton.click();
        } else if (advancedSearchButton.isEnabled()) {
            advancedSearchButton.click();
        } else {
            throw new CommonTestException("Search buttons are disabled or not displayed.");
        }
    }

    public void openAdvancedSearch() {
        if (!advancedSearchIsOpened()) {
            openAdvancedSearchButton.click();
        }
    }

    public void closeAdvancedSearch() {
        if (advancedSearchIsOpened()) {
            openAdvancedSearchButton.click();
        }
    }

    public boolean advancedSearchIsOpened() {
        return advancedSearchButton.isDisplayed();
    }

    public void enterTags(String tags) {
        tagsInput.clear();
        tagsInput.sendKeys(tags);
    }

    public void cleanForm() {
        companyNameInput.clear();
        locationInput.clear();
        if (advancedSearchIsOpened()) {
            tagsInput.clear();
        }
    }

    public boolean areLocationProposalsAvailableFor(String companyNamePart) {
        if (searchProposals.size() == 0) return false;
        for (HtmlElement proposal : searchProposals) {
            if (!proposal.getText().toLowerCase().contains(companyNamePart.toLowerCase())) return false;
        }
        return true;
    }
}
