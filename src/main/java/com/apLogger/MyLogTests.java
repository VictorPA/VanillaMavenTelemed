package com.apLogger;

import com.google.gwt.thirdparty.guava.common.annotations.VisibleForTesting;
import org.apache.log4j.ConsoleAppender;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * Created by Victor on 28/06/2016.
 */
public class MyLogTests {

    private Logger logger;
    private String myString;

    @Before
    public void initialize() {

        myString = "coucou";
    }


    @Test
    public void testLogCreate(){
        Logger.getLogger(null);
    }


}
