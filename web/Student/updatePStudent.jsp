<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <s:form action="updateStudent.action" theme="simple">
        <input type="hidden" name="id" value="<s:property value='student.id' />">
        username :<s:textfield name='username' value="%{student.username}" /><br/>address :<s:textfield name='address' value="%{student.address}" /><br/>age :<s:textfield name='age' value="%{student.age}" /><br/>


        <s:submit />
    </s:form>
</body>
</html>
