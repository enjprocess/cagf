package com.cagf.tool.hbm2jsp;

import com.cagf.tool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateJSP {

    public static String outputDir = null;

    public static String templateDir = null;

    public static String filename = null;

    public static String addJspTemplateFileName = null;
    
    public static String updatePTemplateFileName = null;

    public static List<String> hbmNameList = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        parseArgs(args);


        Helper.getInstance().generateJSPFile(outputDir, hbmNameList, templateDir + "/" + filename);
        Helper.getInstance().generateAddJSPFile(outputDir, hbmNameList, templateDir + "/" + addJspTemplateFileName);
        Helper.getInstance().generateUpdatePJSPFile(outputDir, hbmNameList, templateDir + "/" + updatePTemplateFileName);

    }

    private static void parseArgs(String[] args) {
        //解析args获得参数
        for (String arg : args) {
            if (arg.startsWith(Constants.OUTPUT_DIR)) {
                outputDir = arg.substring(Constants.OUTPUT_DIR.length() + 1);
            } else if (arg.startsWith(Constants.TEMPLATE_DIR)) {
                templateDir = arg.substring(Constants.TEMPLATE_DIR.length() + 1);
            } else if (arg.startsWith(Constants.FILENAME)) {
                filename = arg.substring(Constants.FILENAME.length() + 1);
            } else if (arg.startsWith(Constants.ADD_TEMPLATE)) {
                addJspTemplateFileName = arg.substring(Constants.ADD_TEMPLATE.length() + 1);
            } else if (arg.startsWith(Constants.UPDATEP_TEMPLATE)) {
                updatePTemplateFileName = arg.substring(Constants.UPDATEP_TEMPLATE.length() + 1);
            } else {
                hbmNameList.add(arg);
            }
        }
    }
}
