package com.piano.it.step_defs;

import com.piano.it.bo.Company;
import com.piano.it.pages.companies_main_page.CompanySearchMainPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.List;

public class SearchStepDefs extends AbstractStepDefs {

    @When("^I search for company:$")
    public void iSearchForCompanyCompany(Company company) {
        openPage(CompanySearchMainPage.class).searchFor(company);
    }

    @Then("^I check that results contain company:$")
    public void iCheckThatResultsContainCompany(Company company) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        boolean companyFound = false;
        while (mainPage.hasNextResultPage()){
            List<Company> companies = mainPage.getCompanies();
            if(companies.contains(company)){
                companyFound = true;
                break;
            }
            mainPage.nextPage();
        }
        Assert.assertTrue(companyFound, "Company: " + company + " not found in search results.");

    }

    @Then("^I check that companies in result are from: (.*)$")
    public void iCheckThatCompaniesInResultAreFromLocation(String location) {
    }
}
