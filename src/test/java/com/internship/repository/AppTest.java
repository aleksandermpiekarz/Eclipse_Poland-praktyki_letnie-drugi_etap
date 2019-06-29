package com.internship.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_nullString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "";
        // when
        int result = 0;
        //then
        assertEquals(test.add(numbers),result);
    }

    @Test
    public void test_oneNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2";
        // when
        int result = 2;
        //then
        assertEquals(test.add(numbers),result);
    }

    @Test
    public void test_twoNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2,-3";
        // when
        int result = 2+(-3);
        //then
        assertEquals(test.add(numbers),result);
    }

    @Test
    public void test_multipleNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2,-3,4,43,-23,41";
        // when
        int result = 2+(-3)+4+43+(-23)+41;
        //then
        assertEquals(test.add(numbers),result);
    }

    @Test
    public void test_newLineDelimiter_multipleNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2\n-3,4,43,-23\n41";
        // when
        int result = 2+(-3)+4+43+(-23)+41;
        //then
        assertEquals(test.add(numbers),result);
    }
}
