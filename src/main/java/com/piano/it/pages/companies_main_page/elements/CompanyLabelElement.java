package com.piano.it.pages.companies_main_page.elements;

import com.piano.it.bo.Company;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
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

    public String getLocationName(){
        return locationNameElement.getText().trim();
    }

    public List<String> getTags(){
        List<String> tags = new ArrayList<>();
        for(HtmlElement tagElement: tagsListElement){
            tags.add(tagElement.getText().trim());
        }
        return tags;
    }

    public Company getCompany(){
        Company company = new Company();
        company.setLocation(getLocationName());
        company.setName(getCompanyName());
        company.setTags(getTags());
        return company;
    }
}
