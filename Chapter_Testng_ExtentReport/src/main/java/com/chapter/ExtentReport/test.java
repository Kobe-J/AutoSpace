package com.chapter.ExtentReport;

public class test {
    public static void main(String[] args)
    {
        String str = "runoob";
        str = str.toUpperCase();
        str += "wwwrunoobcom";
        String string = str.substring(2,13);
        string = string + str.charAt(5);;
        System.out.println(string);
    }
}
