package com.cagf.tool.hbm2action;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 生成Dao文件
 */
public class GenerateActionFile {

    public static final String ADD = "Add";
    public static final String DEL = "Del";
    public static final String UPDATEP = "UpdateP";
    public static final String UPDATE = "Update";
    public static final String LIST = "List";
    public static String OUTPUTDIR = null;

    public GenerateActionFile() {
    }


    public void generate(String outputDir, String templateDir, String[] templateName, List<String> hbmNameList) throws IOException, ClassNotFoundException {
        OUTPUTDIR = outputDir;
        //准备好数据
        //公共数据
/*        String ACTIONPACKAGENAME = null;
        String IMPORTBEANSNAME = null;
        String IMPORTSERVICEINTERFACE = null;
        String CLASSNAME = null;
        String classname = null;

        //Add、Update数据
        String DECLAREPROPERTIES = null;
        String PREPAREDATA = null*/;


        //首先将模板文件解析为String
        //然后循环hbm文件
        //对于每个hbm生成对应的dao文件
//        String templateContent = getFileData(templateFile);
        for (int i = 0; i < hbmNameList.size(); i++) {

            String hbmContent = getFileData(hbmNameList.get(i));
            //解析类的权限定名，获取所需值
            String fullClassName = getClassFullName(hbmContent); //类的全名
            String className = getClassName(fullClassName); //类名
            String basicPackageName = getBasicPackageName(fullClassName); //基础包名,action、model、dao.impl、service、service.impl，都在该基础之上扩展
            String beanPackageName = getClassPackageName(fullClassName); //model包名
            String actionPackageName = getActionPackageName(fullClassName); //action包名
            String classPackagePath = getClassPackagePath(actionPackageName); //action包名路径
            String importServiceInterface = getImportServiceInterface(basicPackageName);
            String lowerClassName = getLowerClassName(className);
            String declareproperties = getPropertiesAndMethod(fullClassName, (StringBuilder sb, Field f) -> {
                sb.append("\t").append("private ").append(f.getType().getName()).append(" ").append(f.getName()).append(";").append("\n");
            });
            String getterAndSetter = getPropertiesAndMethod(fullClassName, (StringBuilder sb, Field f) -> {
                sb.append("\n").append("\t").append("public ").append(f.getType().getName()).append(" ").append("get" + FirstLetterUpper(f.getName())).append("()").append(" {").append("\n").append("\t")
                .append("\t").append("return ").append(f.getName()).append(";").append("\n").append("\t").append("}")
                .append("\n")
                .append("\t").append("public ").append("void ").append("set" + FirstLetterUpper(f.getName())).append("(").append(f.getType().getName()).append(" ").append(f.getName()).append(")").append(" {").append("\n").append("\t")
                .append("\t").append("this.").append(f.getName()).append(" = ").append(f.getName()).append(";")
                .append("\n").append("\t").append("}");
            });
            String prepareData = getPropertiesAndMethod(fullClassName, (StringBuilder sb, Field f) -> {
                sb.append("\t\t").append("bean.set").append(FirstLetterUpper(f.getName())).append("(").append(f.getName()).append(")").append(";").append("\n");
            });


            //打印输出
            System.out.println("===========================PART ACTION=====================================");
            System.out.println("action_fullClassName: " + fullClassName);
            System.out.println("action_className: " + className);
            System.out.println("action_classPackageName: " + beanPackageName);
            System.out.println("action_daoPackageName: " + actionPackageName);
            System.out.println("action_classPackagePath: " + classPackagePath);

            //创建输出目录文件
            File file = new File(outputDir + "/" + classPackagePath);

            file.mkdirs();

            //获取到5个模板
            for (int j = 0; j < templateName.length; j++) {
                String fileData = getFileData(templateDir + templateName[j]);
                    System.out.println(templateName[j]);
                switch (templateName[j]) {
                    case "/AddActionTemplate.java":
                        templateReplcaeAndFlush(fileData, actionPackageName, beanPackageName, importServiceInterface, className, lowerClassName, declareproperties, prepareData, ADD, getterAndSetter);
                        break;
                    case "/DelActionTemplate.java":
                        templateReplcaeAndFlush(fileData, actionPackageName, beanPackageName, importServiceInterface, className, lowerClassName, DEL);
                        break;
                    case "/UpdatePActionTemplate.java":
                        templateReplcaeAndFlush(fileData, actionPackageName, beanPackageName, importServiceInterface, className, lowerClassName, UPDATEP);
                        break;
                    case "/UpdateActionTemplate.java":
                        templateReplcaeAndFlush(fileData, actionPackageName, beanPackageName, importServiceInterface, className, lowerClassName, declareproperties, prepareData, UPDATE, getterAndSetter);
                        break;
                    case "/ListActionTemplate.java":
                        templateReplcaeAndFlush(fileData, actionPackageName, beanPackageName, importServiceInterface, className, lowerClassName, LIST);
                        break;   
                }
            }
        }

    }



    private String FirstLetterUpper(String name) {
        String upperLetter = name.substring(0, 1).toUpperCase();
        return upperLetter + name.substring(1);
    }

    private String getPropertiesAndMethod(String fullClassName, BiConsumer<StringBuilder, Field> bc) throws ClassNotFoundException {
        Class cls = Class.forName(fullClassName);
        StringBuilder sb = new StringBuilder();
        Field[] field = cls.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            bc.accept(sb,f);
        }
        return sb.toString();
    }
/*

    private String getDeclareProperties(String fullClassName) throws ClassNotFoundException {
        Class cls = Class.forName(fullClassName);
        StringBuilder sb = new StringBuilder();
        Field[] field = cls.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            sb.append("private ").append(f.getType().getName()).append(f.getName()).append("\n");
        }
        return sb.toString();
    }
*/

    private String getLowerClassName(String className) {
        String lowerLetter = className.substring(0, 1).toLowerCase();
        return lowerLetter + className.substring(1);
    }

    private String getBasicPackageName(String fullClassName) {
        int modelPos = fullClassName.lastIndexOf(".");
        int basicPackageNamePos = fullClassName.lastIndexOf(".", modelPos - 1);
        return fullClassName.substring(0, basicPackageNamePos);
    }

    private String getImportServiceInterface(String basicPackageName) {
        return basicPackageName + ".service.*";
    }

    private void templateReplcaeAndFlush(String fileData, String actionPackageName, String beanPackageName, String importServiceInterface, String s, String lowerClassName, String declareproperties, String prepareData, String actionPrefix, String getterAndSetter) throws IOException {
        String newAction = fileData.replace("@ACTIONPACKAGENAME@", actionPackageName).replace("@IMPORTBEANSNAME@", beanPackageName)
                .replace("@IMPORTSERVICEINTERFACE@", importServiceInterface)
                .replace("@CLASSNAME@", s)
                .replace("@classname@", lowerClassName)
                .replace("@DECLAREPROPERTIES@", declareproperties)
                .replace("@PREPAREDATA@", prepareData)
                .replace("@GETTER/SETTER@", getterAndSetter);

        output(OUTPUTDIR + "/" + getClassPackagePath(actionPackageName) + "/" + actionPrefix + s + ".java", newAction);
    }

    private void templateReplcaeAndFlush(String fileData, String actionPackageName, String beanPackageName, String importServiceInterface, String s, String lowerClassName, String actionPrefix) throws IOException {
        String newAction = fileData.replace("@ACTIONPACKAGENAME@", actionPackageName).replace("@IMPORTBEANSNAME@", beanPackageName)
                .replace("@IMPORTSERVICEINTERFACE@", importServiceInterface)
                .replace("@CLASSNAME@", s)
                .replace("@classname@", lowerClassName);
        output(OUTPUTDIR + "/" + getClassPackagePath(actionPackageName) + "/" + actionPrefix + s + ".java", newAction);
    }

    public void output(String filePath, String content) throws IOException {
        FileWriter out = new FileWriter(new File(filePath));
        out.write(content);
        out.close();
    }


    private String getClassPackagePath(String actionPackageName) {
        return actionPackageName.replace(".", "/");
    }

    private String getActionPackageName(String fullClassName) {
        int proDotPos = fullClassName.lastIndexOf(".");
        int finishDotPos = fullClassName.lastIndexOf(".", proDotPos - 1);
        return fullClassName.substring(0, finishDotPos) + ".action";
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
