package com.piano.it.pages.company_search_page.elements;

import com.piano.it.bo.Company;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.List;

@FindBy(xpath = ".//*[@class='list companies']")
public class ResultsTable extends HtmlElement {

    @FindBy(xpath = "./div")
    private List<CompanyLabelElement> companyLabelElements;

    @FindBy(xpath = "//*[contains(@class,'test-pagination-next')]")
    private Button nextPageButton;

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        for (CompanyLabelElement companyLabelElement : companyLabelElements) {
            Company company = new Company();
            company.setLocations(companyLabelElement.getLocations());
            company.setName(companyLabelElement.getCompanyName());
            for (int i = 0; i < companyLabelElement.getTagsAmount(); i++) {
                company.addTag(companyLabelElement.getTag(i));
            }
            companies.add(company);
        }
        return companies;
    }

    public void clickNext() {
        if (nextButtonIsDisplayed()) {
            nextPageButton.click();
        }
    }

    public boolean nextButtonIsDisplayed() {
        return nextPageButton.exists() && nextPageButton.isDisplayed();
    }
}
