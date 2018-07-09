<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri ="/struts-tags" %>
<html>
<head>
    <title>list</title>
</head>
<body>



    <table width="600px" align="center" border="1">
        <thead >
            <tr>
                <td colspan="3" align="right"><a href="addPerson.jsp">add</a></td>
            </tr>
            <tr align="center">
                <th>id</th><th>username</th><th>address</th><th>age</th>
                <th>operation</th>
            </tr>
        </thead>

        <tbody >
        <s:iterator value="#request.personList" id="u">
            <tr align="center">
               <td><s:property value='#u.id' /></td><td><s:property value='#u.username' /></td><td><s:property value='#u.address' /></td><td><s:property value='#u.age' /></td>
                <td><a href="updatePPerson.action?id=<s:property value='#u.id'/>&start=<s:property value='start'/>&range=<s:property value='range'/> ">update</a> |
                    <a href="delPerson.action?id=<s:property value='#u.id'/>&start=<s:property value='start'/>&range=<s:property value='range'/> ">delete</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</body>
</html>
