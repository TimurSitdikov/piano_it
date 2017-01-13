package com.piano.it.pages.company_search_page.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

public class CompanyLabelElement extends HtmlElement{

    @FindBy(xpath = ".//*[@class='title']")
    private HtmlElement companyNameElement;

    @FindBy(xpath = ".//*[@class='location']")
    private HtmlElement locationNameElement;

    @FindBy(xpath = ".//*[contains(@class,'post-tag')]")
    private List<HtmlElement> tagsListElement;

    public String getCompanyName(){
        return companyNameElement.getText().trim();
    }

    public String getLocations(){
        return locationNameElement.getText().trim();
    }

    public int getTagsAmount(){
        return tagsListElement.size();
    }

    public String getTag(int i){
        return tagsListElement.get(i).getText().trim();
    }

    public void openCompanyPage(){
        companyNameElement.click();
    }
}
