<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>前台横向菜单</title>
</head>
<body>
<%--数据表格--%>
<table id="bannerDg"></table>

<%--添加前台横向菜单窗口--%>
<div id="bannerBox1">
    <center style="padding-top: 30px">
        <form id="ff" method="post" action="javascript:void(0)">
            <table cellpadding="5">
                <tr>
                    <td>菜单名:</td>
                    <td><input id="a1" type="text" name="title"></input></td>
                </tr>
                <tr>
                    <td>跳转链接:</td>
                    <td><input id="a2" type="text" name="url"></input></td>
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
</div>

<%--编辑前台横向菜单窗口--%>
<div id="bannerBox2">
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
    $(function () {//处理文档流
        $('#bannerDg').datagrid({

            //展示菜单栏表格
            url: '<%=basePath %>/getHxMenu.do',
            rownumbers: true,//序号
            pagination: true,//分页
            pageList: [5, 10, 20, 30, 40],
            columns: [
                [
                    {checkbox: 'true'},
                    {field: 'id', title: '主键', width: 100, align: 'center', sortable: true},
                    {field: 'title', title: '菜单名', width: 100, align: 'center'},
                    {field: 'url', title: '链接地址', width: 200, align: 'center'},
                    {field: 'updateTime', title: '更新时间', width: 200, align: 'center'},
                    {title: '操作', width: 100, align: 'center'}
                ]
            ],
            fit: true,
            striped: true,//斑马线
            remoteSort: false,//关闭远程排序
            sortName: 'id',//定义列排序

            //工具栏
            toolbar: [
                {
                    iconCls: 'icon-add',
                    text: '添加',
                    handler: function () {//
                        $("#c1").linkbutton({
                            iconCls: 'icon-ok'
                        });
                        $("#c2").linkbutton({
                            iconCls: 'icon-cancel'
                        });
                        $("#a1").validatebox({
                            required: true,
                            validType: 'length[1,10]'
                        });
                        $("#a2").validatebox({
                            required: true,
                            validType: 'url'
                        });
                        $("#bannerBox1").window('open')
                    }
                }, "-",
                {
                    iconCls: 'icon-edit',
                    text: '编辑',
                    handler: function () {
                        //首先判断是否选中某一条记录
                        var ckAttr = $("#bannerDg").datagrid("getSelections");
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
                            var row = $("#bannerDg").datagrid("getSelected");
                            $("#ff2 input[name=title]").val(row.title);
                            $("#ff2 input[name=url]").val(row.url);
                            $("#bannerBox2").window('open');
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
                        var ckAttr = $("#bannerDg").datagrid("getSelections");
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
                                                $("#bannerDg").datagrid('reload');
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

        /*添加前台横向菜单窗口*/
        $("#bannerBox1").window({
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


        /*重置添加内容*/
        $("#c2").click(function () {
            $("#ff input[type=text]").val("");
            $("#a1").focus();
        });

        /*编辑前台横向菜单窗口*/
        $("#bannerBox2").window({
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

        /*添加触发ajax*/
        $("#c1").click(function () {
            $.ajax({
                url: "<%=basePath %>/addWebMenu.do",
                type: "post",
                dataType: "json",
                data: {
                    "title": $("input[name=title]").val(),
                    "url": $("input[name=url]").val()
                },
                success: function (rs) {
                    if (rs) {//添加成功
                        //1、关闭添加前台菜单窗口
                        $("#bannerBox1").window('close');
                        //2、刷新表格
                        $("#bannerDg").datagrid('reload')
                        //3、提示添加成功
                        $.messager.show({
                            title: "提示",
                            msg: "菜单添加成功"
                        });
                    } else {//添加失败
                        $.messager.alert('提示', '添加失败，请重试');
                    }
                },
                error: function (err) {
                    alert("提交错误！")
                }
            });
        });

        /*编辑触发ajax*/
        $("#cc1").click(function () {
            var row = $("#bannerDg").datagrid("getSelected");
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
                        $("#bannerBox2").window('close');
                        //2、刷新表格
                        $("#bannerDg").datagrid('reload');
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

    })
</script>
</body>
</html>
