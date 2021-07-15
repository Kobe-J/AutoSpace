package com.tester.cases;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Objects;

/**
 * @date 2021.6.25
 * @author  Yxl
 * AutoInsApk
 * **/

public class AutoInsApk {
    static class ApkFilter implements FilenameFilter{
        public boolean isApk(String file){
            return file.toLowerCase().endsWith(".apk");
        }

        @Override
        public boolean accept(File dir, String name) {
            return isApk(name);
        }
    }
    private static final String PATH = "C:\\Users\\Administrator\\Desktop";
    private static final String INSTALL = "adb install ";

    public static void main(String[] args) throws IOException {
        File dec = new File(PATH);
        String [] apk = dec.list(new ApkFilter());
        System.out.println(Objects.requireNonNull(apk)[0]);
        String apkfile = PATH + "\\" +apk[0];
        try{
            Runtime.getRuntime().exec(INSTALL + apkfile);
        }catch (IIOException e){
            e.printStackTrace();
        }
        //Desktop.getDesktop().open(new File("C:\\Users\\Administrator\\Desktop"));
        File file = new File("C:\\Users\\Administrator\\Desktop\\hello.txt");
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
    }

}
