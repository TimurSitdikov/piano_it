package com.piano.it.step_defs;

import com.piano.it.bo.Company;
import com.piano.it.pages.company_search_page.CompanySearchMainPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class SearchStepDefs extends AbstractStepDefs {

    @When("^I search for company(?: from|):$")
    public void iSearchForCompanyCompany(List<Company> companies) {
        openPage(CompanySearchMainPage.class).searchFor(companies.get(0));
    }

    @Then("^I check that results contain company:$")
    public void iCheckThatResultsContainCompany(List<Company> companies) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        boolean companyFound = true;
        do {
            List<Company> companiesFromPage = mainPage.getCompanies();
            for (Company company : companies) {
                if (!companiesFromPage.contains(company)) {
                    companyFound = false;
                }
            }
            mainPage.nextPage();
        } while (mainPage.hasNextResultPage());
        Assert.assertTrue(companyFound, "Company: " + companies.get(0) + " not found.");
    }

    @Then("^I check that companies in result are from: (.*)$")
    public void iCheckThatCompaniesInResultAreFromLocation(List<String> expectedLocations) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        SoftAssert softAssert = new SoftAssert();
        do {
            List<Company> companiesFromPage = mainPage.getCompanies();
            for (Company companyFromPage : companiesFromPage) {
                boolean contains = false;
                for(String expectedLocation: expectedLocations){
                    if(companyFromPage.getLocations().toLowerCase().contains(expectedLocation.toLowerCase())){
                        contains = true;
                        break;
                    }
                }
                softAssert.assertTrue(contains,
                        "Locations of the company - " + companyFromPage.getLocations()
                                + " don't contain expected locations: " + expectedLocations + "\n");
            }
            mainPage.nextPage();
        } while (mainPage.hasNextResultPage());
        softAssert.assertAll();
    }
}
