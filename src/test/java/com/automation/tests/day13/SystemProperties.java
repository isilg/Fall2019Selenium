package com.automation.tests.day13;

import org.testng.annotations.Test;

public class SystemProperties {

    @Test
    public void test(){
        //Absolute path of pom.xml
        //  /Users/lsilavunduk/Desktop/IdeaProjects/Fall2019Selenium/pom.xml
        //  System.getProperty("user.dir") + "pom.xml"
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));
        //Flexible path to downloads folder
        // System.getProperty("user.home") + "/Downloads" ->finds downloads folder for every computer
        System.out.println(System.getProperty("user.home"));

        String pathToDownloads = System.getProperty("user.home")+"/Downloads";
        System.out.println(pathToDownloads);

        System.out.println(System.getProperty("os.arch"));

    }
}
