package com.piano.it.background;

import com.piano.it.driver.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestBackground {

    private boolean start = false;

    @Before
    public void setUp(){
        if(!start) start = true;
    }

    @After
    public void tearDown(){
        DriverManager.quitAllDrivers();
    }
}
