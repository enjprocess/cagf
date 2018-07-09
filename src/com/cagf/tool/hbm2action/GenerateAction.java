package com.cagf.tool.hbm2action;

import com.cagf.tool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateAction {

    public static final String ADD_ACTION_TEMPLATE_SUFFIX = "/AddActionTemplate.java";
    public static final String DEL_ACTION_TEMPLATE_SUFFIX = "/DelActionTemplate.java";
    public static final String UPDATEP_ACTION_TEMPLATE_SUFFIX = "/UpdatePActionTemplate.java";
    public static final String UPDATE_ACTION_TEMPLATE_SUFFIX = "/UpdateActionTemplate.java";
    public static final String LIST_ACTION_TEMPLATE_SUFFIX = "/ListActionTemplate.java";


    public static final String[] templateName = {ADD_ACTION_TEMPLATE_SUFFIX,DEL_ACTION_TEMPLATE_SUFFIX,UPDATEP_ACTION_TEMPLATE_SUFFIX
    ,UPDATE_ACTION_TEMPLATE_SUFFIX,LIST_ACTION_TEMPLATE_SUFFIX};

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String outputDir = null;
        String templateDir = null;
        List<String> hbmNameList = new ArrayList<>();
        //解析args获得参数
        for (String arg : args) {
            if (arg.startsWith(Constants.OUTPUT_DIR)) {
                outputDir = arg.substring(Constants.OUTPUT_DIR.length() + 1);
            } else if (arg.startsWith(Constants.TEMPLATE_DIR)) {
                templateDir = arg.substring(Constants.TEMPLATE_DIR.length() + 1);
            } else {
                hbmNameList.add(arg);
            }
        }

        //生成一个对象来处理代码生成
        GenerateActionFile gdf = new GenerateActionFile();
        gdf.generate(outputDir,templateDir, templateName, hbmNameList);
    }

}
