package com.piano.it.step_defs;

import com.piano.it.bo.Company;
import com.piano.it.pages.company_page.CompanyPage;
import com.piano.it.pages.company_search_page.CompanySearchMainPage;
import com.piano.it.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class SearchStepDefs extends AbstractStepDefs {

    private final int PAGES_TO_CHECK_LIMIT = 5;

    @When("^I search for company(?: from|)(| but not click submit):$")
    public void iSearchForCompanyCompany(String dontClickSearch, List<Company> companies) {
        openPage(CompanySearchMainPage.class).searchFor(companies.get(0), !dontClickSearch.isEmpty());
    }

    @Then("^I check that names of companies in result are like: (.*)$")
    public void iCheckThatResultsContainCompany(String expectedName) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        int numberOfPagesWithResults = getNumberOfPagesToCheck(mainPage);
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < numberOfPagesWithResults; i++) {
            List<Company> companiesFromPage = mainPage.getCompanies();
            for (Company companyFromPage : companiesFromPage) {
                boolean resultIsCorrect = true;
                if(!companyFromPage.getName().toLowerCase()
                        .contains(expectedName.toLowerCase())){
                    CompanyPage companyPage = mainPage.clickOnCompany(companyFromPage);
                    resultIsCorrect = companyPage.pageContainsKeyword(expectedName);
                    companyPage.goBackToList();
                }
                softAssert.assertTrue(resultIsCorrect,
                        "Company: " + companyFromPage.getName()
                                + "  does not meet the search criteria: " + expectedName);
            }
            mainPage.nextPage();
        }
        softAssert.assertAll();
    }

    @Then("^I check that companies in result are from: (.*)$")
    public void iCheckThatCompaniesInResultAreFromLocation(List<String> expectedLocations) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        SoftAssert softAssert = new SoftAssert();
        int numberOfPagesWithResults = getNumberOfPagesToCheck(mainPage);
        for (int i = 0; i < numberOfPagesWithResults; i++) {
            List<Company> companiesFromPage = mainPage.getCompanies();
            for (Company companyFromPage : companiesFromPage) {
                boolean contains = false;
                for (String expectedLocation : expectedLocations) {
                    if (companyFromPage.getLocations().toLowerCase().contains(expectedLocation.toLowerCase())) {
                        contains = true;
                        break;
                    }
                }
                softAssert.assertTrue(contains,
                        "Locations of the company - " + companyFromPage.getLocations()
                                + " don't contain expected locations: " + expectedLocations + "\n");
            }
            mainPage.nextPage();
        }
        softAssert.assertAll();
    }

    @Then("^I check that companies in result have tags: (.*)$")
    public void iCheckThatCompaniesInResultHaveTagsTags(List<String> expectedTagsList) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        SoftAssert softAssert = new SoftAssert();
        int numberOfPages = getNumberOfPagesToCheck(mainPage);
        for (int i = 0; i < numberOfPages; i++) {
            for (int j = 0; j < mainPage.getAmountOfCompaniesOnPage(); j++) {
                CompanyPage companyPage = mainPage.clickOnCompany(j);
                boolean atLeastOneTagFound = false;
                List<String> actualTags = companyPage.getTags();
                for (String expectedTag : expectedTagsList) {
                    if (TestUtils.containsCaseInsensitive(actualTags, expectedTag)) {
                        atLeastOneTagFound = true;
                        break;
                    }
                }
                softAssert.assertTrue(atLeastOneTagFound,
                        "Tags from list: " + expectedTagsList + " not found for company:" + companyPage.getCompanyName());
                companyPage.goBackToList();
            }
            mainPage.nextPage();
        }
        softAssert.assertAll();
    }

    @Then("^I check that location proposals are available for: (.*)$")
    public void iCheckThatSearchProposalsAreAvailableForWiki(String locationNamePart) {
        CompanySearchMainPage mainPage = openPage(CompanySearchMainPage.class);
        Assert.assertTrue(mainPage.areLocationProposalsAvailableFor(locationNamePart),
                "Search proposals are not available for " + locationNamePart);
    }

    private int getNumberOfPagesToCheck(CompanySearchMainPage mainPage) {
        return mainPage.getAmountOfPagesWithSearchResults() > PAGES_TO_CHECK_LIMIT ?
                PAGES_TO_CHECK_LIMIT : mainPage.getAmountOfPagesWithSearchResults();
    }
}
