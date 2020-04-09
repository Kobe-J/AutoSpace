package com.chapter.ExtentReport;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentReport_test {

        @Test
        public void case1(){
            System.out.println("case1");
        }

        @Test
        public void case2(){
            System.out.println("case2");
        }

        @Test(expectedExceptions = RuntimeException.class)
        public void case3(){
            throw  new RuntimeException();
        }

        @Test(expectedExceptions = RuntimeException.class)
        public void case4(){
            throw  new RuntimeException();
        }

        @Test
        public void log(){
            Reporter.log("日志demo");
        }
}
