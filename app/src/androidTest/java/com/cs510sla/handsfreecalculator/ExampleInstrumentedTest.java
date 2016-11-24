package com.cs510sla.handsfreecalculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context appContext;
    @Before
    public void init(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
    }
    @Test
    public void useAppContext() throws Exception {


        assertEquals("com.cs510sla.handsfreecalculator", appContext.getPackageName());
    }
    @Test
    public void testSetAndGet(){
        /*
        appContext.
        appContext.setAnswerText("1+2*3/4.5%6+-7");
        String res = "";
        res = calc.getAnswerText();
        Assert.assertEquals("1+2*3/4.5%6+-7", res);
    }
    @Test
    public void testSetAndClear(){
        calc.setAnswerText("1+2*3/4.5%6+-7");
        String res = "";
        calc.clearText();
        res = calc.getAnswerText();
        Assert.assertEquals("", res);
    */}

}
