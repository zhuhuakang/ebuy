<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统用户信息查询</title>
</head>
<body>
<%--数据表格--%>
<table id="dg"></table>

<%--添加系统账号窗口--%>
<div id="box3">

</div>

<%--编辑前台横向菜单窗口--%>
<div id="box2">
    <center style="padding-top: 30px">
        <form id="ff2" method="post" action="javascript:void(0)">
            <table cellpadding="5">
                <tr>
                    <td>菜单名:</td>
                    <td><input id="aa1" type="text" name="title"></input></td>
                </tr>
                <tr>
                    <td>跳转链接:</td>
                    <td><input id="aa2" type="text" name="url"></input></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <a href="#" id="cc1">编辑</a>
                        <a href="#" id="cc2">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</div>

<script type="text/javascript"></script>
<script>
    function editAuthority() {
        $("#box3").window("open");
    }

    $(function () {//处理文档流
        $('#dg').datagrid({
            //展示菜单栏表格
            url: '<%=basePath %>/getSystemUser.do',
            pageList: [5, 10, 20, 30, 40],
            columns: [
                [
                    {checkbox: 'true'},
                    {field: 'id', title: '主键', width: 100, align: 'center', sortable: true},
                    {field: 'username', title: '账号', width: 100, align: 'center'},
                    {
                        field: 'isRoot', title: '角色', width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '1') {
                                return '超级管理员';
                            }
                            return '普通管理员';
                        }
                    },
                    {field: 'updateTime', title: '更新时间', width: 200, align: 'center'},
                    {
                        field: 'operate', title: '操作', width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            if (row.isRoot == '1') {
                                return '无操作';
                            }
                            return '<a class="c1" onclick="editAuthority()"></a>';
                        }

                    }
                ]
            ],
            onLoadSuccess: function () {
                jQuery(".c1").linkbutton({
                    iconCls: 'icon-edit',
                    text: '设置权限',
                    plain: true
                });
            },
            fit: true,
            striped: true,//斑马线
            remoteSort: false,//关闭远程排序
            sortName: 'id',//定义列排序

            //工具栏
            toolbar: [
                {
                    iconCls: 'icon-add',
                    text: '添加账号',
                    handler: function () {//
                        $("#box1").window('open')
                    }
                }, "-",
                {
                    iconCls: 'icon-edit',
                    text: '编辑账号',
                    handler: function () {
                        //首先判断是否选中某一条记录
                        var ckAttr = $("#dg").datagrid("getSelections");
                        var len = ckAttr.length;
                        if (len == 0) {
                            $.messager.alert('警告', '没有选中要编辑的行');
                        } else if (len == 1) {
                            $("#cc1").linkbutton({
                                iconCls: 'icon-ok'
                            });
                            $("#cc2").linkbutton({
                                iconCls: 'icon-cancel'
                            });
                            $("#ff2 input[name=title]").validatebox({
                                required: true,
                                validType: 'length[1,10]'
                            });
                            $("#ff2 input[name=url]").validatebox({
                                required: true,
                                validType: 'url'
                            });
                            //进行数据回显
                            var row = $("#dg").datagrid("getSelected");
                            $("#ff2 input[name=title]").val(row.title);
                            $("#ff2 input[name=url]").val(row.url);
                            $("#box2").window('open');
                        } else {
                            $.messager.alert('警告', '编辑时不能选中多行');
                        }
                    }
                }, "-",
                {
                    iconCls: 'icon-remove',
                    text: '批量删除',
                    handler: function () {
                        //首先判断是选中数据记录
                        var ckAttr = $("#dg").datagrid("getSelections");
                        var len = ckAttr.length;
                        if (len == 0) {
                            $.messager.alert('警告', '没有选中要删除的记录');
                        } else {
                            $.messager.confirm('警告', '您确认删除记录吗？', function (r) {
                                if (r) {//确认删除
                                    var idStr = "";

                                    for (var i = 0; i < ckAttr.length; i++) {
                                        idStr += ckAttr[i].id + ",";
                                    }
                                    $.ajax({
                                        url: "<%=basePath %>/deleteWebMenu.do",
                                        type: "post",
                                        dataType: "json",
                                        data: {
                                            "idStr": idStr
                                        },
                                        success: function (rs) {
                                            if (rs) {//删除成功
                                                //2、刷新表格
                                                $("#dg").datagrid('reload');
                                                //3、提示成功
                                                $.messager.show({
                                                    title: "提示",
                                                    msg: "菜单删除成功"
                                                });
                                            } else {//删除失败
                                                $.messager.alert('提示', '删除失败，请重试');
                                            }
                                        },
                                        error: function (err) {
                                            $.messager.alert('提示', '删除失败，请重试');
                                        }
                                    });
                                }
                            });
                        }
                    }
                }
            ]
        });

        /*添加账号窗口*/
        $("#box1").window({
            width: 600,
            height: 400,
            href: '<%=basePath %>/pages/addSystemUserForm.jsp',
            title: "添加账号",
            iconCls: 'icon-add',
            draggable: true, /*能拖动*/
            resizable: false, /*不能改变尺寸*/
            minimizable: false,
            collapsible: false,
            maximizable: false,
            modal: true,
            closed: true  /*窗口初始化时就默认关闭*/
        });


        /*编辑前台横向菜单窗口*/
        $("#box2").window({
            width: 300,
            height: 200,
            title: "添加横向菜单",
            iconCls: 'icon-add',
            draggable: true, /*能拖动*/
            resizable: false, /*不能改变尺寸*/
            minimizable: false,
            collapsible: false,
            maximizable: false,
            modal: true,
            closed: true  /*窗口初始化时就默认关闭*/
        });


        /*编辑触发ajax*/
        $("#cc1").click(function () {
            var row = $("#dg").datagrid("getSelected");
            var id = row.id;
            $.ajax({
                url: "<%=basePath %>/updateWebMenu.do",
                type: "post",
                dataType: "json",
                data: {
                    "title": $("#ff2 input[name=title]").val(),
                    "url": $("#ff2 input[name=url]").val(),
                    "id": id
                },
                success: function (rs) {
                    if (rs) {//编辑成功
                        //1、关闭添加前台菜单窗口
                        $("#box2").window('close');
                        //2、刷新表格
                        $("#dg").datagrid('reload');
                        //3、提示添加成功
                        $.messager.show({
                            title: "提示",
                            msg: "菜单编辑成功"
                        });
                    } else {//添加失败
                        $.messager.alert('提示', '编辑失败，请重试');
                    }
                },
                error: function (err) {
                    $.messager.alert('提示', '编辑失败，请重试');
                }
            });
        });

        /*设置权限*/
        $("#box3").window({
            width: 500,
            height: 300,
            href: '<%=basePath %>/pages/editAuthority.jsp',
            title: "设置权限",
            iconCls: 'icon-edit',
            draggable: true, /*能拖动*/
            resizable: false, /*不能改变尺寸*/
            minimizable: false,
            collapsible: false,
            maximizable: false,
            modal: true,
            closed: true  /*窗口初始化时就默认关闭*/
        });

    })
</script>
</body>
</html>
