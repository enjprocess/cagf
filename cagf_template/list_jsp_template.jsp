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
                <td colspan="3" align="right"><a href="add@CLASSNAME@.jsp">add</a></td>
            </tr>
            <tr align="center">
                @TITLE@
                <th>operation</th>
            </tr>
        </thead>

        <tbody >
        <s:iterator value="#request.@classname@List" id="u">
            <tr align="center">
               @CONTENT@
                <td><a href="updateP@CLASSNAME@.action?id=<s:property value='#u.id'/>&start=<s:property value='start'/>&range=<s:property value='range'/> ">update</a> |
                    <a href="del@CLASSNAME@.action?id=<s:property value='#u.id'/>&start=<s:property value='start'/>&range=<s:property value='range'/> ">delete</a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</body>
</html>
