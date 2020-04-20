package com.chapter.springboot.Explanatorybasis;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
/**
 * suite套件   在类之前执行
 * **/
public class TestNG_Suite {
    @BeforeSuite
    public void befor(){
        System.out.println("suite is start");
    }

    @AfterSuite
    public void after(){
        System.out.println("suite is stop");
    }

}
