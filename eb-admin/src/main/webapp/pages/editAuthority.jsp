<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>设置权限</title>
</head>
<body>
<center>
    <table id="authVue" width="400px">
        <tr v-for="oneMenu in oneMenuList">
            <td><h3>{{oneMenu.oneText}}&nbsp;</h3></td>
            <td v-for="twoMenu in oneMenu.twoMenuList">
                <input type="checkbox">{{twoMenu.twoText}}&nbsp;
            </td>
        </tr>
    </table>
</center>

<script type="text/javascript">
    const vue1 = new Vue({
        el: "#authVue",
        data: {
            oneMenuList: []
        },
        mounted() {
            this.$http.post("<%=basePath %>/findAuthorityByRelation.do").then(
                function (rs) {
                    this.oneMenuList = rs.body;
                }
            );
        }
    });
</script>
</body>
</html>
