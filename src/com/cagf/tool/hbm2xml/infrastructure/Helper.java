package com.cagf.tool.hbm2xml.infrastructure;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.XMLFormatter;

public class Helper {

    public static final Helper helper = new Helper();
    private Namespace n1;
    private Namespace n2;

    private Helper() {

    }

    public void generateSpringInfrastructure(String output, String filename
    ,String dbPropertyName, String hibernatePropertyName, List<String> hibernateMappings) throws IOException {


        Document document = new Document();

        //设置根元素
        Element root = setRootElement(document);
        root = setDataSource(root, dbPropertyName);
        root = setSessionFactory(root, hibernatePropertyName, hibernateMappings);

        //输出
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        XMLOutputter out = new XMLOutputter(format);
        FileWriter fileWriter = new FileWriter(output + "/" + filename);
        out.output(document, fileWriter);
        fileWriter.close();
    }

    private Element setSessionFactory(Element root, String hibernatePropertyName, List<String> mappings) throws IOException {

        System.out.println("hibernatePropertyName:" + hibernatePropertyName);

        Element sessionFactory = new Element("bean", n1).setAttribute("id", "sessionFactory")
                .setAttribute("class", "org.springframework.orm.hibernate3.LocalSessionFactoryBean");
        root.addContent(sessionFactory);

        //dataSource
        Element dataSourceRef = new Element("property", n1).setAttribute("name", "dataSource")
                .setAttribute("ref", "dataSource");
        Element hibernateProperties = new Element("property", n1).setAttribute("name", "hibernateProperties");
        Element props = new Element("props", n1);

        sessionFactory.addContent(dataSourceRef);
        sessionFactory.addContent(hibernateProperties);
        hibernateProperties.addContent(props);

        //hibernateProperties
        InputStream is = new FileInputStream(new File(hibernatePropertyName));
        Properties properties = new Properties();
        properties.load(is);
        is.close();



        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key =(String) enumeration.nextElement();
            String value = properties.getProperty(key);
            Element prop = new Element("prop", n1).setAttribute("key", key).setText(value);
            props.addContent(prop);
        }

        //处理hibernateMapping
        Element mappingResources = new Element("property", n1);
        mappingResources.setAttribute("name","mappingResources" );
        Element list = new Element("list", n1);
        mappingResources.addContent(list);
        sessionFactory.addContent(mappingResources);

        System.out.println("mappings size: " + mappings.size());
        for (String mapping : mappings) {
            Element value = new Element("value", n1).setText(getHbmFileName(mapping));
            list.addContent(value);
        }



        return root;
    }

    private String getHbmFileName(String mapping) {
        int index = mapping.lastIndexOf("\\");
        return mapping.substring(index + 1);
    }

    private Element setDataSource(Element root, String dbPropertyName) throws IOException {
        Element dataSource = new Element("bean",n1);
        root.addContent(dataSource);
        dataSource.setAttribute("id", "dataSource").setAttribute("class","org.apache.commons.dbcp.BasicDataSource")
                .setAttribute("destroy-method", "close");

        InputStream is = new FileInputStream(new File(dbPropertyName));
        Properties prop = new Properties();
        prop.load(is);
        is.close();

        Enumeration<?> enumeration = prop.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = prop.getProperty(key);
            Element element = new Element("property", n1).setAttribute("name", key).setAttribute("value", value);
            dataSource.addContent(element);
        }
        return root;
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
}
