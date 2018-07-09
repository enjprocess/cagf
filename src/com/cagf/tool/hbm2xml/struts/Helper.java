package com.cagf.tool.hbm2xml.struts;

import org.jdom.DocType;
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

    public void generateSpringInfrastructure(String output, String filename, List<String> hibernateMappings) throws IOException {

        Document document = new Document();

        //设置DTD
        setRootElement(document);

        Element root = new Element("struts");
        document.addContent(root);
        for (String hibernateMapping : hibernateMappings) {
            String classFullName = getClassFullName(getFileData(hibernateMapping));
            String className = getClassName(classFullName);

            registerModule(hibernateMapping,root, className);
        }

        //输出
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        XMLOutputter out = new XMLOutputter(format);
        FileWriter fileWriter = new FileWriter(output + "/" + filename);
        out.output(document, fileWriter);
        fileWriter.close();
    }

    private void registerModule(String hibernateMapping, Element root, String className) {
        //设置add部分
        Element packageEle = new Element("package");

        //设置元素
        //设置add
        //package
        packageEle.setAttribute("name", className)
                .setAttribute("extends","struts-default")
                .setAttribute("namespace", "/" + className);

        setStrutsAddPart(root,packageEle, className);

        setStrutsListPart(root, packageEle, className);

        setStrutsDelPart(root, packageEle, className);

        setStrutsUpdatePPart(root, packageEle, className);

       setStrutsUpdatePart(root, packageEle, className);
    }

    private void setStrutsUpdatePart(Element root, Element packageEle, String className) {
        Element actionEle = new Element("action");
        Element resultEle = new Element("result");
        Element paramEle = new Element("param");

        //action
        actionEle.setAttribute("name", "update" + className)
                .setAttribute("class", "update" + className);
        //result
        resultEle.setAttribute("name", "success")
                .setAttribute("type", "redirectAction");
        //param
        paramEle.setAttribute("name", "actionName").setText("list" + className);


        //安装元素
        resultEle.addContent(paramEle);
        actionEle.addContent(resultEle);
        packageEle.addContent(actionEle);
    }

    private void setStrutsUpdatePPart(Element root, Element packageEle, String className) {
        Element actionEle = new Element("action");
        Element resultEle = new Element("result");


        //action
        actionEle.setAttribute("name", "updateP" + className)
                .setAttribute("class", "updateP" + className);
        //result
        resultEle.setAttribute("name", "success")
                .setText("updateP" + className + ".jsp");
        //param
        //安装元素
        actionEle.addContent(resultEle);
        packageEle.addContent(actionEle);
    }

    private void setStrutsDelPart(Element root, Element packageEle, String className) {
        Element actionEle = new Element("action");
        Element resultEle = new Element("result");
        Element paramEle = new Element("param");
        Element startEle = new Element("param");
        Element rangeEle = new Element("param");


        //action
        actionEle.setAttribute("name", "del" + className)
                .setAttribute("class", "del" + className);
        //result
        resultEle.setAttribute("name", "success")
                .setAttribute("type", "redirectAction");
        //param
        paramEle.setAttribute("name", "actionName").setText("list" + className);

        startEle.setAttribute("name", "start").setText("${start}");
        rangeEle.setAttribute("name", "range").setText("${range}");

        //安装元素
        resultEle.addContent(paramEle);
        resultEle.addContent(startEle);
        resultEle.addContent(rangeEle);
        actionEle.addContent(resultEle);
        packageEle.addContent(actionEle);
    }

    private void setStrutsListPart(Element root,Element packageEle, String className) {
        Element actionEle = new Element("action");
        Element resultEle = new Element("result");


        //action
        actionEle.setAttribute("name", "list" + className)
                .setAttribute("class", "list" + className);
        //result
        resultEle.setAttribute("name", "success")
                .setText("list" + className + ".jsp");

        //安装元素
        actionEle.addContent(resultEle);
        packageEle.addContent(actionEle);
    }

    private void setStrutsAddPart(Element root,Element packageEle, String className) {

        Element actionEle = new Element("action");
        Element resultEle = new Element("result");
        Element paramEle = new Element("param");


        //action
        actionEle.setAttribute("name", "add" + className)
                .setAttribute("class", "add" + className);
        //result
        resultEle.setAttribute("name", "success")
                    .setAttribute("type", "redirectAction");
        //param
        paramEle.setAttribute("name", "actionName").setText("list" + className);


        //安装元素
        resultEle.addContent(paramEle);
        actionEle.addContent(resultEle);
        packageEle.addContent(actionEle);
        root.addContent(packageEle);
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
     * 设置DTD
     *
     * @param document
     * @return
     */
    private void setRootElement(Document document) {

        DocType docType = new DocType("struts");
        docType.setPublicID("-//Apache Software Foundation//DTD Struts Configuration 2.0//EN");
        docType.setSystemID("http://struts.apache.org/dtds/struts-2.0.dtd");
        document.addContent(docType);
    }

    private String getFileData(String templateFile) throws IOException {
        File template = new File(templateFile);
        FileReader fileReader = new FileReader(template);
        char[] content = new char[(int) template.length()];
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
