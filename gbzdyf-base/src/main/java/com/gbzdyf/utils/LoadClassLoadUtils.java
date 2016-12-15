package com.gbzdyf.utils;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by y on 2016/11/14.
 */
public class LoadClassLoadUtils {

    static String rootPath = "F:\\gbzdyf-base\\src\\main\\java";

    public static void main(String[] args) {

        ClassLoader classLoader = LoadClassLoadUtils.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.getClass().getName());
            classLoader = classLoader.getParent();
        }
        System.out.println(classLoader);
        System.out.println("----------------------------------------------------");



        FileSystemClassLoader fsc1 = new FileSystemClassLoader(rootPath);
        FileSystemClassLoader fsc2 = new FileSystemClassLoader(rootPath);

        String className = "com.gbzdyf.utils.Sample";
        try {
            Class<?> class1 = fsc1.loadClass(className);
            Object obj1 = class1.newInstance();

            Class<?> class2 = fsc2.loadClass(className);
            Object obj2 = class2.newInstance();

            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.getPath());
        }
        System.out.println("-----------------------");
        System.out.println(System.getProperty("sun.boot.class.path"));


    }

}
