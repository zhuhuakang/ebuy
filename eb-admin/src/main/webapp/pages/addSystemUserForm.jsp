<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加后台账号信息表页面</title>
</head>
<body>
<center style="padding-top: 30px">
    <form id="ff" method="post" action="javascript:void(0)">
        <table cellpadding="5">
            <tr>
                <td>账号:</td>
                <td><input id="a1" type="text" name="username"></input></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input id="a2" type="text" name="pwd"></input></td>
            </tr>
            <tr>
                <td>授权：</td>
                <td>
                    <ul id="t"></ul>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="#" id="c1">添加</a>
                    <a href="#" id="c2">重置</a>
                </td>
            </tr>

        </table>
    </form>
</center>

<script type="text/javascript">
    $(function () {

        /*添加触发ajax*/
        $("#c1").click(function () {
            var dms = $('#t').tree("getChecked", ['indeterminate', 'checked']);
            var menuIds = "";
            for (var i in dms) {
                menuIds += dms[i].id + ",";
            }
            $.ajax({
                url: "<%=basePath %>/addSystemUser.do",
                type: "post",
                dataType: "json",
                data: {
                    "username": $("input[name=username]").val(),
                    "pwd": $("input[name=pwd]").val(),
                    "menuIds": menuIds
                },
                success: function (rs) {
                    if (rs) {
                        alert(rs)
                    }
                }
            });
        });

        /*重置添加内容*/
        $("#c2").click(function () {
            $("#ff input[type=text]").val("");
            $("#a1").focus();
        });

        //加载权限树
        $('#t').tree({
            url: '<%=basePath %>/getAuthority.do',
            checkbox: true
        });

        $("#a1").validatebox({
            required: true,
            validType: 'length[1,10]'
        });
        $("#a2").validatebox({
            required: true,
            validType: 'length[1,10]'
        });
        $("#c1").linkbutton({
            iconCls: 'icon-ok'
        });
        $("#c2").linkbutton({
            iconCls: 'icon-cancel'
        });
    });
</script>
</body>
</html>
