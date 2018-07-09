package com.cagf.tool.hbm2xml.service;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Helper {

    public static final Helper helper = new Helper();
    private Namespace n1;
    private Namespace n2;

    private Helper() {

    }

    public void generateSpringInfrastructure(String output, String filename, List<String> hibernateMappings) throws IOException {


        Document document = new Document();

        //设置根元素
        Element root = setRootElement(document);
        root = registerServiceImpl(root, hibernateMappings);

        //输出
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        XMLOutputter out = new XMLOutputter(format);
        FileWriter fileWriter = new FileWriter(output + "/" + filename);
        out.output(document, fileWriter);
        fileWriter.close();
    }


    private String getHbmFileName(String mapping) {
        int index = mapping.lastIndexOf("\\");
        return mapping.substring(index + 1);
    }

    private Element registerServiceImpl(Element root, List<String> hibernateMappings) throws IOException {

        for (int i = 0; i < hibernateMappings.size(); i++) {

            String fullClassName = getClassFullName(getFileData(hibernateMappings.get(i)));
            String className = getClassName(fullClassName);
            String basicPackageName = getBasicPackageName(fullClassName);

            String daoBeanIdName = getDaoBeanIdName(className);

            //准备写入xml的数据
            String serviceBeanId = getServiceBeanId(className);
            String classValue = getServiceBeanClass(basicPackageName, className);
            Element bean = new Element("bean", n1).setAttribute("id", serviceBeanId)
                    .setAttribute("class", classValue)
                    .setAttribute("scope", "singleton");
            Element property = new Element("property", n1).setAttribute("name", daoBeanIdName);
            Element ref = new Element("ref", n1).setAttribute("bean", daoBeanIdName);

            property.addContent(ref);
            bean.addContent(property);
            root.addContent(bean);
        }

        return root;
    }

    private String getDaoBeanIdName(String className) {
        return getLowerClassName(className) + "Dao";
    }

    private String getServiceBeanClass(String basicPackageName, String className) {
        return basicPackageName + ".service.impl." + className + "ServiceImpl";
    }

    private String getServiceBeanId(String className) {
        String lowerClassName = getLowerClassName(className);
        return lowerClassName + "Service";
    }



    /**
     * 设置根元素
     * @param document
     * @return
     */
    private Element setRootElement(Document document) {
        n1 = Namespace.getNamespace("http://www.springframework.org/schema/beans");
        n2 = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        Element root = new Element("beans", n1);
        //指定根元素的namespace

        //指定其它用到的namespace
        root.addNamespaceDeclaration(n2);
        root.setAttribute("schemaLocation", "http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd", n2);

        document.addContent(root);
        return root;
    }

    public static Helper getInstance() {
        return helper;
    }

    private String getFileData(String templateFile) throws IOException {
        File template = new File(templateFile);
        FileReader fileReader = new FileReader(template);
        char[] content = new char[(int)template.length()];
        fileReader.read(content);
        return String.valueOf(content);
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

    private String getBasicPackageName(String fullClassName) {
        int modelPos = fullClassName.lastIndexOf(".");
        int basicPackageNamePos = fullClassName.lastIndexOf(".", modelPos - 1);
        return fullClassName.substring(0, basicPackageNamePos);
    }

    private String getLowerClassName(String className) {
        String lowerLetter = className.substring(0, 1).toLowerCase();
        return lowerLetter + className.substring(1);
    }


}
