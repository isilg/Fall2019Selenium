package com.automation.tests.day8;

import org.testng.annotations.Test;
import org.testng.Assert;

public class UnitTestPractice {
    public static void main(String[] args) {
        //unit test
        //to check our method works properly
        //if assertion fails, that means our method doesn't work correctly
        //so we have to fix it
       String expected ="cba";
       String toReverse = "abc";
       String actual = reverseString(toReverse);    //actual comes from method
       //Assertion
       verifyEquals(expected, actual);


    }
    //annotation
    //description - is not working for junit, make sure that you use testng
    @Test(description = "Verify if method can reverse a string")
    public void test(){
        String expected ="elppa";
        String actual = reverseString("apple");
        Assert.assertEquals(actual,expected);
    }

    @Test(description = "Verify if method can reverse a string")
    public void test2(){
        String expected = "rac";
        String actual = reverseString("car");
        Assert.assertEquals(actual, expected);
    }


    public static boolean verifyEquals(String expected, String actual){
        if (expected.equals(actual)){
            System.out.println("TEST PASSED");
            return true;
        }else{
            System.out.println("TEST FAILED!!");
            System.out.println("Expected: "+expected);
            System.out.println("Actual: "+actual);
            return false;
        }
    }

    /**
     * This method stands for reversing strings.
     * @param str to reverse
     * @return reversed String
     */
    public static String reverseString(String str){
        String reversed="";
        for (int i=str.length()-1; i>=0 ;i--){
            reversed += str.charAt(i);
        }
        return reversed;
    }
}
