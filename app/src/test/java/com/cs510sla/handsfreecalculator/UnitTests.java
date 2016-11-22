package com.cs510sla.handsfreecalculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.cs510sla.handsfreecalculator.CalcActivity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    CalcActivity calc;
    @Before
    public void init(){
        calc = new CalcActivity();
    }

    @Test
    public void filterTestPass1(){
        String res = "";
        res = calc.filterInput("4 divided by 2");
        Assert.assertEquals("4/2", res);
    }
    @Test
    public void filterTestPass2(){
        String res = "";
        res = calc.filterInput("4 divide 2");
        Assert.assertEquals("4/2", res);
    }
    @Test
    public void filterTestPass3(){
        String res = "";
        res = calc.filterInput("4 times 2");
        Assert.assertEquals("4*2", res);
    }
    @Test
    public void filterTestPass4(){
        String res = "";
        res = calc.filterInput("4 x 2");
        Assert.assertEquals("4*2", res);
    }
    @Test
    public void filterTestPass5(){
        String res = "";
        res = calc.filterInput("4 plus 2");
        Assert.assertEquals("4+2", res);
    }
    @Test
    public void filterTestPass6(){
        String res = "";
        res = calc.filterInput("4 plus .5");
        Assert.assertEquals("4+0.5", res);
    }
    @Test
    public void filterTestPass7(){
        String res = "";
        res = calc.filterInput("4 plus 0.5");
        Assert.assertEquals("4+0.5", res);
    }
    @Test
    public void filterTestPass8(){
        String res = "";
        res = calc.filterInput("4 modulo 3");
        Assert.assertEquals("4%3", res);
    }
    @Test
    public void filterTestPass9(){
        String res = "";
        res = calc.filterInput("4 mod 3");
        Assert.assertEquals("4%3", res);
    }
    @Test
    public void filterTestPass10(){
        String res = "";
        res = calc.filterInput("4 multiplied by 3");
        Assert.assertEquals("4*3", res);
    }
    @Test
    public void filterTestPass11(){
        String res = "";
        res = calc.filterInput("4 minus 3");
        Assert.assertEquals("4-3", res);
    }
    @Test
    public void filterTestPass12(){
        String res = "";
        res = calc.filterInput("4 subtract 3");
        Assert.assertEquals("4-3", res);
    }
    @Test
    public void filterTestPass13(){
        String res = "";
        res = calc.filterInput("4 ÷ 3");
        Assert.assertEquals("4/3", res);
    }
    @Test
    public void filterTestPass14(){
        String res = "";
        res = calc.filterInput("4 ÷ 3");
        Assert.assertEquals("4/3", res);
    }
    @Test
    public void filterTestPass15(){
        String res = "";
        res = calc.filterInput("4 plus negative 3");
        Assert.assertEquals("4+-3", res);
    }
    @Test
    public void filterTestPass16(){
        String res = "";
        res = calc.filterInput("4 times neg 3");
        Assert.assertEquals("4*-3", res);
    }
    @Test
    public void filterTestPass17(){
        String res = "";
        res = calc.filterInput("4 plus 3 point 4");
        Assert.assertEquals("4+3.4", res);
    }
    @Test
    public void testSetAndGet(){
        calc.setAnswerText("1+2*3/4.5%6+-7");
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
    }
}