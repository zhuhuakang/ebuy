<%--
  Created by IntelliJ IDEA.
  User: zhuhuakang
  Date: 2019/2/25
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
</head>
<body>
<center>
    <form action="<%=basePath %>/fastDFSUpload.do" type="post" enctype="multipart/form-data">
        <table width="500px" border="1px">
            <tr>
                <td>文件：</td>
                <td><input type="file" name="ff"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
