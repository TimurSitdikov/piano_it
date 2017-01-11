package com.piano.it.step_defs;

import com.piano.it.bo.Company;
import com.piano.it.pages.companies_main_page.CompanySearchMainPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.List;

public class SearchStepDefs extends AbstractStepDefs {

    @When("^I search for company:$")
    public void iSearchForCompanyCompany(List<Company> companies) {
        openPage(CompanySearchMainPage.class).searchFor(companies.get(0));
    }

    @Then("^I check that results contain company:$")
    public void iCheckThatResultsContainCompany(List<Company> companies) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        boolean companyFound = false;
        do {
            List<Company> companiesFromPage = mainPage.getCompanies();
            if(companiesFromPage.contains(companies.get(0))){
                companyFound = true;
                break;
            }
            mainPage.nextPage();
        } while (mainPage.hasNextResultPage());
        Assert.assertTrue(companyFound, "Company: " + companies.get(0) + " not found.");
    }

    @Then("^I check that companies in result are from: (.*)$")
    public void iCheckThatCompaniesInResultAreFromLocation(String location) {
        System.out.println("1");
    }
}
