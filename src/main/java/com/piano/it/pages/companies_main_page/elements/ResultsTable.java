package com.piano.it.pages.companies_main_page.elements;

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

    @FindBy(xpath = ".//*[contains(@class,'test-pagination-next')]")
    private Button nextPageButton;

    public List<Company> getCompanies(){
        List<Company> companies = new ArrayList<>();
        for(CompanyLabelElement companyLabelElement: companyLabelElements){
            companies.add(companyLabelElement.getCompany());
        }
        return companies;
    }

    public void clickNext(){
        nextPageButton.click();
    }

    public boolean isNextButtonDisplayed() {
        return nextPageButton.exists() && nextPageButton.isDisplayed();
    }
}
