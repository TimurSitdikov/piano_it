package com.piano.it.background;

import com.piano.it.driver.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestBackground {

    @After
    public void tearDown(){
        DriverManager.quitAllDrivers();
    }

}
