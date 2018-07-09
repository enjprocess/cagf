package com.cagf.tool.hbm2xml.web;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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

    public void generateSpringInfrastructure(String output, String filename) throws IOException {


        Document document = new Document();

        //设置根元素
        Element root = setRootElement(document);
        root  = registerFilterPart(root);
        root = registerFilterMappingPart(root);
        root = registerListenerPart(root);


        //输出
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        XMLOutputter out = new XMLOutputter(format);
        FileWriter fileWriter = new FileWriter(output + "/" + filename);
        out.output(document, fileWriter);
        fileWriter.close();
    }

    private Element registerListenerPart(Element root) {
        Element listener = new Element("listener", n1);
        Element listenerClass = new Element("listener-class", n1)
                .setText("org.springframework.web.context.ContextLoaderListener");

        listener.addContent(listenerClass);
        root.addContent(listener);
        return root;
    }

    private Element registerFilterMappingPart(Element root) {
        //准备各个元素
        Element filterMapping = new Element("filter-mapping", n1);
        Element filterName = new Element("filter-name", n1);
        Element urlPattern = new Element("url-pattern", n1);

        //对于各个元素进行细节设置
        filterName.setText("struts2");
        urlPattern.setText("/*");

        //安装各个元素
        filterMapping.addContent(filterName);
        filterMapping.addContent(urlPattern);
        root.addContent(filterMapping);

        return root;
    }

    private Element registerFilterPart(Element root) {
        //准备各个元素
        Element filter = new Element("filter", n1);
        Element filterName = new Element("filter-name", n1);
        Element filterClass = new Element("filter-class", n1);

        //对于各个元素进行细节设置
        filterName.setText("struts2");
        filterClass.setText("org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter");

        //安装各个元素
        filter.addContent(filterName);
        filter.addContent(filterClass);
        root.addContent(filter);

        return root;
    }



    /**
     * 设置根元素
     *
     * @param document
     * @return
     */
    private Element setRootElement(Document document) {
        n1 = Namespace.getNamespace("http://java.sun.com/xml/ns/javaee");
        n2 = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        Element root = new Element("web-app", n1);
        //指定根元素的namespace

        //指定其它用到的namespace
        root.addNamespaceDeclaration(n2);
        root.setAttribute("schemaLocation", "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd", n2)
                .setAttribute("version", "2.5");
        document.addContent(root);
        return root;
    }



}
