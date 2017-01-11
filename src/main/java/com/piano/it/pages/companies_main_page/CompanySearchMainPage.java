package com.piano.it.pages.companies_main_page;

import com.piano.it.bo.Company;
import com.piano.it.pages.AbstractPage;
import com.piano.it.pages.companies_main_page.elements.CompanySearchForm;
import com.piano.it.pages.companies_main_page.elements.ResultsTable;
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

    }

    @Override
    public String getUrl() {
        return "https://stackoverflow.com/jobs/companies";
    }

    public void searchFor(Company company) {
        if(!company.getName().isEmpty()) companySearchForm.enterCompanyName(company.getName());
        if(!company.getLocation().isEmpty()) companySearchForm.enterLocationName(company.getLocation());
        if(!company.getTags().isEmpty()){
            companySearchForm.openAdvancedSearch();
            companySearchForm.enterTags(company.getTags());
        }
        companySearchForm.clickSearch();
    }

    public List<Company> getCompanies() {
        return resultsTable.getCompanies();
    }

    public boolean hasNextResultPage(){
        return resultsTable.isNextButtonDisplayed();
    }

    public void nextPage(){
        resultsTable.clickNext();
    }
}
