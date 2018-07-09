<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <s:form action="update@CLASSNAME@.action" theme="simple">
        <input type="hidden" name="id" value="<s:property value='@classname@.id' />">
        @FORMOBJS@


        <s:submit />
    </s:form>
</body>
</html>
