package com.cagf.tool.hbm2serviceimpl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 生成Dao文件
 */
public class GenerateServiceImplFile {

    public GenerateServiceImplFile() {
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
            String serviceImplPackageName = getServicePackageName(fullClassName);
            String servicePackageName = serviceImplPackageName.substring(0, serviceImplPackageName.lastIndexOf("."));
            String classPackagePath = getClassPackagePath(serviceImplPackageName);
            String lowerClassName = className.substring(0, 1).toLowerCase() + className.substring(1);

            String daoPackageName = servicePackageName.substring(0, servicePackageName.lastIndexOf("."));



            //打印输出
            System.out.println("fullClassName: " + fullClassName);
            System.out.println("className: " + className);
            System.out.println("classPackageName: " + beanPackageName);
            System.out.println("daoPackageName: " + serviceImplPackageName);
            System.out.println("classPackagePath: " + classPackagePath);

            //创建输出目录文件
            File file = new File(outputDir + "/" + classPackagePath);

            file.mkdirs();

            //在内存中生成新的Dao
            String newDao = templateContent.replaceAll("@PACKAGE@", serviceImplPackageName).replaceAll("@IMPORTBEANS@", beanPackageName).replaceAll("@CLASSNAME@", className).replaceAll("@IMPORTSERVICEINTERFACE@", servicePackageName + ".*").replaceAll("@classname@", lowerClassName)
                    .replaceAll("@IMPORTDAOINTERFACE@", daoPackageName + ".dao.*");
            System.out.println(newDao);
            //将内存中的Dao输出到目录中
            FileWriter out = new FileWriter(new File(outputDir, classPackagePath + "/" + className + "ServiceImpl.java"));
            out.write(newDao);
            out.close();
        }

    }



    private String getClassPackagePath(String daoPackageName) {
        return daoPackageName.replace(".", "/");
    }

    private String getServicePackageName(String fullClassName) {
        int proDotPos = fullClassName.lastIndexOf(".");
        int finishDotPos = fullClassName.lastIndexOf(".", proDotPos - 1);
        return fullClassName.substring(0, finishDotPos) + ".service.impl";
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
