package com.cagf.tool.hbm2xml.struts;

import com.cagf.tool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateStruts {

    public static String outputDir = null;
    public static String fileName = null;

    public static List<String> hbmNameList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        parseArgs(args);

        Helper.getInstance().generateSpringInfrastructure(outputDir, fileName, hbmNameList);

    }

    private static void parseArgs(String[] args) {
        //解析args获得参数
        for (String arg : args) {
            if (arg.startsWith(Constants.OUTPUT_DIR)) {
                outputDir = arg.substring(Constants.OUTPUT_DIR.length() + 1);
            } else if (arg.startsWith(Constants.FILENAME)) {
                System.out.println("arg:" + arg);
                fileName = arg.substring(Constants.FILENAME.length() + 1);
            } else {
                hbmNameList.add(arg);
            }
        }
    }
}
