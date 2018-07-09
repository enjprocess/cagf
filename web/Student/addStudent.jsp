<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>add</title>
</head>
<body>
    <s:form action="addStudent.action" theme="simple">
        username :<s:textfield name='username' /><br/>address :<s:textfield name='address' /><br/>age :<s:textfield name='age' /><br/>
        <s:submit />
    </s:form>
</body>
</html>
