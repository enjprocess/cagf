<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>add</title>
</head>
<body>
    <s:form action="add@CLASSNAME@.action" theme="simple">
        @FORMOBJS@
        <s:submit />
    </s:form>
</body>
</html>
