package com.cagf.tool.hbm2xml.spring;

import com.cagf.tool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateSpring {

    public static String outputDir = null;
    public static String fileName = null;

    public static List<String> springFileList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        parseArgs(args);

        System.out.println("springFileList: " + springFileList);

        Helper.getInstance().generateSpringInfrastructure(outputDir, fileName, springFileList);

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
                springFileList.add(arg);
            }
        }
    }
}
