package com.tester.cases;

import java.text.ParseException;

public class WeChatApplication {
    public static void main(String[] args) throws ParseException {
        System.out.println("Start Success !");
        Execute execute = new Execute();
        execute.everyday("Kobe","08:35:30","ZY:早间打卡");
        execute.everyday("shine","23:59:59","生日快乐~ 开心健康！");
    }
}
