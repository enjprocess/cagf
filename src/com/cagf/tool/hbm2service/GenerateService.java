package com.cagf.tool.hbm2service;

import com.cagf.tool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateService {

    public static final String DaoTemplateSuffix = "/ServiceTemplate.java";

    public static void main(String[] args) throws IOException {
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
        GenerateServiceFile gsf = new GenerateServiceFile();
        gsf.generate(outputDir, templateDir + DaoTemplateSuffix, hbmNameList);
    }
}
