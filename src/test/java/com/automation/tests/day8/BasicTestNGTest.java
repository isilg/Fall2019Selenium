package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.*;

public class BasicTestNGTest {

    //runs only once before @BeforeClass and @BeforeMethod
    @BeforeTest
    public void beforeTest(){
        System.out.println("BEFORE TEST");
    }

    //runs only once after @AfterClass and @AfterMethod
    @AfterTest
    public void afterTest(){
        System.out.println("AFTER TEST");
    }

    //It runs only once in the class, before @BeforeMethod and before any test(runs once before everything in the class)
    @BeforeClass
    public void beforeClass(){
        System.out.println("BEFORE CLASS");
    }

    //runs only once at the end of the class
    @AfterClass
    public void afterClass(){
        System.out.println("AFTER CLASS");
    }

    //runs before every method
    @BeforeMethod
    public void setup(){
        System.out.println("BEFORE METHOD");
    }

    @AfterMethod
    public void teardown(){
        System.out.println("AFTER METHOD");
    }


    @Test
    public void test1(){
        System.out.println("TEST 1");
        String expected = "apple";
        String actual = "apple";
        Assert.assertEquals(actual, expected);
    }



    @Test(description = "Verify if method can reverse a string")
    public void test2(){
        System.out.println("TEST 2");
        int num1 = 5;
        int num2 = 10;
        //Assertion. It is also called har assertion because if assertion fails, it stops execution due to execution
        Assert.assertTrue(num1 < num2);
    }

}
