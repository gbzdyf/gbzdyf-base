package com.gbzdyf.utils;

import java.io.*;

/**
 * Created by y on 2016/11/14.
 */
public class FileSystemClassLoader extends ClassLoader{

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (null == classData) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            FileInputStream ins = new FileInputStream(path);
            ByteArrayOutputStream aos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRed = 0;
            while ((bytesNumRed = ins.read(buffer)) != -1) {
                aos.write(bytesNumRed);
            }
            return aos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
    }


}
