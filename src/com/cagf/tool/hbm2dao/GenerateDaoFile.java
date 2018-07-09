package com.cagf.tool.hbm2dao;

import java.io.*;
import java.util.List;

/**
 * 生成Dao文件
 */
public class GenerateDaoFile {

    public GenerateDaoFile() {
    }

    public void generate(String outputDir, String templateFile, List<String> hbmNameList) throws IOException {
        //首先将模板文件解析为String
        //然后循环hbm文件
        //对于每个hbm生成对应的dao文件
        String templateContent = getFileData(templateFile);
        for (int i = 0; i < hbmNameList.size(); i++) {

            String hbmContent = getFileData(hbmNameList.get(i));
            //解析类的权限定名，获取所需值
            String fullClassName = getClassFullName(hbmContent);
            String className = getClassName(fullClassName);
            String beanPackageName = getClassPackageName(fullClassName);
            String daoPackageName = getDaoPackageName(fullClassName);
            String classPackagePath = getClassPackagePath(daoPackageName);

            //打印输出
            System.out.println("fullClassName: " + fullClassName);
            System.out.println("className: " + className);
            System.out.println("classPackageName: " + beanPackageName);
            System.out.println("daoPackageName: " + daoPackageName);
            System.out.println("classPackagePath: " + classPackagePath);

            //创建输出目录文件
            File file = new File(outputDir + "/" + classPackagePath);

            file.mkdirs();

            //在内存中生成新的Dao
            String newDao = templateContent.replaceAll("@PACKAGE@", daoPackageName).replaceAll("@IMPORTBEANS@", beanPackageName).replaceAll("@CLASSNAME@", className);
            System.out.println(newDao);
            //将内存中的Dao输出到目录中
            FileWriter out = new FileWriter(new File(outputDir, classPackagePath + "/" + className + "Dao.java"));
            out.write(newDao);
            out.close();
        }

    }



    private String getClassPackagePath(String daoPackageName) {
        return daoPackageName.replace(".", "/");
    }

    private String getDaoPackageName(String fullClassName) {
        int proDotPos = fullClassName.lastIndexOf(".");
        int finishDotPos = fullClassName.lastIndexOf(".", proDotPos - 1);
        return fullClassName.substring(0, finishDotPos) + ".dao";
    }

    private String getClassPackageName(String fullClassName) {
        int proDotPos = fullClassName.lastIndexOf(".");
        return fullClassName.substring(0, proDotPos) + ".*";

    }

    private String getClassName(String fullClassName) {
        return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    }

    private String getClassFullName(String templateContent) {
        int namePos = templateContent.indexOf("name");
        int startQuotePos = templateContent.indexOf("\"", namePos);
        int endQuotePos = templateContent.indexOf("\"", startQuotePos + 1);

        return templateContent.substring(startQuotePos + 1, endQuotePos);
    }

    private String getFileData(String templateFile) throws IOException {
        File template = new File(templateFile);
        FileReader fileReader = new FileReader(template);
        char[] content = new char[(int)template.length()];
        fileReader.read(content);
        return String.valueOf(content);
    }
}
