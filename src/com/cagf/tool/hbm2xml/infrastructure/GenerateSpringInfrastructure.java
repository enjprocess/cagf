package com.cagf.tool.hbm2xml.infrastructure;

import com.cagf.tool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateSpringInfrastructure {

    public static String outputDir = null;
    public static String fileName = null;
    public static String dbPropertyName = null;
    public static String hibernatePropertyName = null;

    public static List<String> hbmNameList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        parseArgs(args);

        Helper.getInstance().generateSpringInfrastructure(outputDir, fileName, dbPropertyName, hibernatePropertyName, hbmNameList);

    }

    private static void parseArgs(String[] args) {
        //解析args获得参数
        for (String arg : args) {
            if (arg.startsWith(Constants.OUTPUT_DIR)) {
                outputDir = arg.substring(Constants.OUTPUT_DIR.length() + 1);
            } else if (arg.startsWith(Constants.FILENAME)) {
                System.out.println("arg:" + arg);
                fileName = arg.substring(Constants.FILENAME.length() + 1);
            } else if (arg.startsWith(Constants.DB_PROPERTY)) {
                dbPropertyName = arg.substring(Constants.DB_PROPERTY.length() + 1);
            } else if (arg.startsWith(Constants.HIBERNATE_PROPERTY)) {
                hibernatePropertyName = arg.substring(Constants.HIBERNATE_PROPERTY.length() + 1);
            } else {
                hbmNameList.add(arg);
            }
        }
    }
}
