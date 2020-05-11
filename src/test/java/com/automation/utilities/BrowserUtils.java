package com.automation.utilities;

public class BrowserUtils {

//    Type ->Thread.sleep(1000*sec); ->higlight(choose it) -> option + command + T

    public static void wait(int sec){

        try {
            Thread.sleep(1000*sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
