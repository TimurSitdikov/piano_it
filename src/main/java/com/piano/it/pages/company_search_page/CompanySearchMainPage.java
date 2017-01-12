package com.piano.it.pages.company_search_page;

import com.piano.it.bo.Company;
import com.piano.it.pages.AbstractPage;
import com.piano.it.pages.company_search_page.elements.CompanySearchForm;
import com.piano.it.pages.company_search_page.elements.ResultsTable;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CompanySearchMainPage extends AbstractPage {

    ResultsTable resultsTable;

    CompanySearchForm companySearchForm;

    public CompanySearchMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageCompletelyLoaded() {
        waitForJSandJQueryToLoad();
    }

    @Override
    public String getUrl() {
        return "https://stackoverflow.com/jobs/companies";
    }

    public void searchFor(Company company) {
        if(company.getName() != null && !company.getName().isEmpty()) companySearchForm.enterCompanyName(company.getName());
        if(company.getLocations()!= null && !company.getLocations().isEmpty()) companySearchForm.enterLocations(company.getLocations());
        if(company.getTags()!= null && !company.getTags().isEmpty()){
            companySearchForm.openAdvancedSearch();
            companySearchForm.enterTags(company.getTags());
        }
        companySearchForm.clickSearch();
        waitForJSandJQueryToLoad();
    }

    public List<Company> getCompanies() {
        waitForJSandJQueryToLoad();
        return resultsTable.getCompanies();
    }

    public boolean hasNextResultPage(){
        return resultsTable.nextButtonIsDisplayed();
    }

    public void nextPage(){
        resultsTable.clickNext();
        waitForJSandJQueryToLoad();
    }
}