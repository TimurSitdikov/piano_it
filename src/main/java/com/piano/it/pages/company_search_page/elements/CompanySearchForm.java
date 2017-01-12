package com.piano.it.pages.company_search_page.elements;

import com.piano.it.exceptions.CommonTestException;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

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

    @FindBy(xpath = "//*[contains(@class,'tag-editor ')]")
    private TextInput tagsInput;

    public void enterCompanyName(String companyName){
        companyNameInput.clear();
        companyNameInput.sendKeys(companyName);
    }

    public void enterLocations(String locations){
        locationInput.clear();
        locationInput.sendKeys(locations);
    }

    public void clickSearch(){
        if(simpleSearchButton.isEnabled()){
            simpleSearchButton.click();
        } else {
            throw new CommonTestException("Simple search button is disabled or not displayed.");
        }
    }

    public void openAdvancedSearch(){
        if(!advancedSearchIsOpened()){
            openAdvancedSearchButton.click();
        }
    }

    public void closeAdvancedSearch(){
        if(advancedSearchIsOpened()){
            openAdvancedSearchButton.click();
        }
    }

    public boolean advancedSearchIsOpened(){
        return advancedSearchButton.isDisplayed();
    }

    public void enterTags(String tags){
        tagsInput.clear();
        tagsInput.sendKeys(tags);
    }
}
