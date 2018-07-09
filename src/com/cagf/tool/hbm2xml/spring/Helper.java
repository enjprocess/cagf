package com.cagf.tool.hbm2xml.spring;

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

    public static Helper getInstance() {
        return helper;
    }

    public void generateSpringInfrastructure(String output, String filename, List<String> springFileList) throws IOException {


        Document document = new Document();

        //设置根元素
        Element root = setRootElement(document);
        root = registerResources(root, springFileList);

        //输出
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        XMLOutputter out = new XMLOutputter(format);
        FileWriter fileWriter = new FileWriter(output + "/" + filename);
        out.output(document, fileWriter);
        fileWriter.close();
    }

    private Element registerResources(Element root, List<String> springFileList) throws IOException {

        for (int i = 0; i < springFileList.size(); i++) {

            Element importElement = new Element("import", n1).setAttribute("resource", getFileName(springFileList.get(i)));
            root.addContent(importElement);
        }

        return root;
    }

    private String getFileName(String s) {
        int i = s.lastIndexOf("\\");
        return s.substring(i + 1);
    }

    /**
     * 设置根元素
     *
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



}
