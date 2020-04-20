package com.chapter.springboot.Explanatorybasis.Case;

import org.testng.Assert;
import org.testng.annotations.Test;


public class case_test3 {

    //忽略这个测试方法 enabled=false是忽略此方法 反之则不忽略执行此方法
    @Test(enabled = false)
    public void abnormal(){
        Assert.assertEquals(1,2);
    }

    @Test(enabled = true)
    public void test1(){
        System.out.println("测试");
    }

    //异常测试   注解后面的参数意思是需要得到一个异常才会测试通过
    @Test(expectedExceptions = RuntimeException.class)
    public void RuntimeException_loser(){
        Assert.assertEquals(1,2);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void RuntimeException_success(){
        System.out.println("aa");
        throw new RuntimeException();
    }

    @Test
    public void father(){
        System.out.println("被son方法依赖");
    }

    @Test(dependsOnMethods = {"father"})
    public void son(){
         System.out.println("必须依赖father方法，并且father方法必须执行成功，son才能执行成功");
     }


}
