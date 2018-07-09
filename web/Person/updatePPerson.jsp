<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <s:form action="updatePerson.action" theme="simple">
        <input type="hidden" name="id" value="<s:property value='person.id' />">
        username :<s:textfield name='username' value="%{person.username}" /><br/>address :<s:textfield name='address' value="%{person.address}" /><br/>age :<s:textfield name='age' value="%{person.age}" /><br/>


        <s:submit />
    </s:form>
</body>
</html>
