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
        assertEquals(result,test.add(numbers));
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
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_twoNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2,3";
        // when
        int result = 2+(3);
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_multipleNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2,3,4,43,23,41";
        // when
        int result = 2+(3)+4+43+(23)+41;
        //then
        assertEquals(result,test.add(numbers));
    }


    @Test
    public void test_newLineDelimiter_multipleNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "2\n3,4,43,23\n41";
        // when
        int result = 2+(3)+4+43+(23)+41;
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_userDefinedDelimiter_multipleNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "//hello1\n2hello13hello143hello123";
        // when
        int result = 2+(3)+43+(23);
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_negativeNumbersException_userDefinedDelimiter_multipleNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "//hello1\n2hello1-3hello1-43hello1-23";
        // when
        int result = -1;
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_negativeNumbersException_oneNumberString()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "-10";
        // when
        int result = -1;
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_ignoreMoreThanThousand()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "//;\n2;1000;5;2333;1001";
        // when
        int result = 2+1000+5;
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_delimitersOfAnyLength()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "//[hai]\n1hai2hai3";
        // when
        int result = 6;
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_multipleDelimitersOfAnyLength()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "//[feng][shui]\n1feng2feng3shui32";
        // when
        int result = 1+2+3+32;
        //then
        assertEquals(result,test.add(numbers));
    }

    @Test
    public void test_finalMultipleDelimitersOfAnyLength()
    {
        // given
        StringCalculator test = new StringCalculator();
        String numbers = "//[feng][shui][%][@@]\n1feng2feng3shui32%3@@7%5@@8shui1005";
        // when
        int result = 1+2+3+32+3+7+5+8;
        //then
        assertEquals(result,test.add(numbers));
    }
}

