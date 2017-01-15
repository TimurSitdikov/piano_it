package com.piano.it.background;

import com.piano.it.driver.DriverManager;
import cucumber.api.java.After;

public class TestBackground {

    @After
    public void tearDown() {
        DriverManager.quitAllDrivers();
    }

}
