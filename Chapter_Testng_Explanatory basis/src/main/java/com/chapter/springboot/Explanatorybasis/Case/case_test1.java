package com.chapter.springboot.Explanatorybasis.Case;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

//方法分组测试
public class case_test1 {

    @Test(groups = "start")
    public void starttest1(){ System.out.println("test1 is start"); }
    @Test(groups = "start")
    public void starttest2(){
        System.out.println("test2 is start");
    }
    @Test(groups = "start")
    public void starttest3(){
        System.out.println("test3 is start");
    }


    @Test(groups = "stop")
    public void stoptest1(){
        System.out.println("test1 is stop");
    }

    @Test(groups = "stop")
    public void stoptest2(){
        System.out.println("test2 is stop");
    }

    @Test(groups = "stop")
    public void stoptest3(){
        System.out.println("test3 is stop");
    }

    @BeforeGroups("start")
    public void BeforeGroups(){
        System.out.println("这个方法在start组之前执行");
    }

    @AfterGroups("start")
    public void AfterGroups(){
        System.out.println("这个方法在start组之后执行");
    }

    @BeforeGroups("stop")
    public void BeforeGroups_stop(){
        System.out.println("这个方法在stop组之前执行");
    }

    @AfterGroups("stop")
    public void AfterGroups_stop(){
        System.out.println("这个方法在stop组之后执行");
    }

}
