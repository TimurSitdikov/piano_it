package com.piano.it.step_defs;

import cucumber.api.java.en.Given;

public class CommonStepDefs extends AbstractStepDefs {

    @Given("^I open main page$")
    public void iOpenMainPage() {
        openCompanySearchPage();
    }
}
