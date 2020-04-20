package com.chapter.springboot.Explanatorybasis.Case;

import org.testng.annotations.*;


public class case_test2 {


    @Test
    public void test(){
        System.out.println("test");
    }

    @Test
    public void test1(){
        System.out.println("test1");

    }

    //在每个测试方法执行前后都执行一次
    @BeforeMethod
    public  void BeforeMethod(){ System.out.println("BeforeMethod"); }
    @AfterMethod
    public  void AfterMethod(){
        System.out.println("AfterMethod");
    }

    //在测试类执行前后执行一次
    @BeforeTest
    public  void Befortest(){
        System.out.println("BeforeTest");
    }
    @AfterTest
    public  void Aftertest(){
        System.out.println("BeforeTest");
    }


}
