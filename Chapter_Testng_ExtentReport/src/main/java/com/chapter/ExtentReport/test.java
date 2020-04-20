package com.chapter.ExtentReport;

import jdk.swing.interop.SwingInterOpUtils;

public class test {
    public static void main(String[] args)
    {
        String str = "runoob";
        str = str.toUpperCase();
        str += "wwwrunoobcom";
        String string = str.substring(2,13);
        string = string + str.charAt(5);;
        System.out.println(string);

        double a = Double.valueOf(Integer.valueOf("50",10));
        System.out.println(a);

        String q ="666";
        System.out.println(Integer.parseInt(q,16));

        System.out.println(q.equals(a));
    }

}
