package com.tester.cases;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
/**
 * @date 2021.6.25
 * @author  Yxl
 * AutoInsApk
 * **/

public class AutoInsApk {
    static class ApkFilter implements FilenameFilter{
        public boolean isApk(String file){
            if(file.toLowerCase().endsWith(".apk")){
                return true;
            }else {
                return false;
            }

        }

        @Override
        public boolean accept(File dir, String name) {
            return isApk(name);
        }
    }
    private static String PATH = "C:\\Users\\Administrator\\Desktop";
    private static String INSTALL = "adb install ";

    public static void main(String[] args) throws IOException {
        File dec = new File(PATH);
        String [] apk = dec.list(new ApkFilter());
        System.out.println(apk[0]);
        String apkfile = PATH + "\\" +apk[0];
        try{
            Runtime.getRuntime().exec(INSTALL + apkfile);
        }catch (IIOException e){
            e.printStackTrace();
        }
    }

}
