<%@ page import="com.qf.pojo.Admin" %><%--
  Created by IntelliJ IDEA.
  User: 李博
  Date: 2020/10/20
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人中心</title>
    <head>
        <meta charset="utf-8">

        <!--表示使用IE最新的渲染模式进行解析-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!--
            兼容一些移动设备，会根据屏幕的大小缩放
            width=device-width  表示宽度是设备的宽度（很多手机的宽度都是980px）
            initial-scale=1  初始化缩放级别   1-5
            minimum-scale=1  maximum-scale=5
            user-scalable=no  禁止缩放
        -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>添加或修改视频</title>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

        <!-- 如果IE版本小于9，加载以下js,解决低版本兼容问题 -->
        <!--[if lt IE 9]>
        <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->


        <!--
            引入网络的jquery  ,如果想换成自己的，导入即可
            网站优化：建议将你网站的css\js等代码，放置在互联网公共平台上维护，比如：七牛
        -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <style type="text/css">
            th {
                text-align: center;
            }
        </style>
    </head>
<body>
<nav class="navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <a class="navbar-brand" href="${pageContext.request.contextPath}/video/list">视频管理系统</a>
        </div>

        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-9">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/video/list">视频管理</a></li>
                <li><a href="${pageContext.request.contextPath}/speaker/showSpeakerList">主讲人管理</a></li>
                <li><a href="${pageContext.request.contextPath}/course/showCourseList">课程管理</a></li>


            </ul>
            <p class="navbar-text navbar-right">
                <a href="/admin/toEdit"><span>${admin.username}</span></a>
                <i class="glyphicon glyphicon-log-in" aria-hidden="true"></i>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/admin/exit" class="navbar-link">退出</a>
            </p>
        </div>
        <!-- /.navbar-collapse -->


    </div>
    <!-- /.container-fluid -->
</nav>

<%
    Admin admin = (Admin) request.getSession(false).getAttribute("admin");
    String password = admin.getPassword();
    String replace = password.replace(password, "*******");
    request.setAttribute("replace",replace);
%>

<div class="container" style="margin-top: 20px;">
    <form action="/admin/edit" method="post">
    <table class="table table-bordered table-hover"
           style="text-align: center;table-layout:fixed;">
        <thead>
        <tr class="active" align="center">
            <th>ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>角色</th>
            <th rowspan="3">
                <input type="submit" value="提交">
            </th>
        </tr>
        <tr align="center">
            <td>${admin.id}</td>
            <td>${admin.username}</td>
            <td>${replace}</td>
            <td>${admin.roles}</td>
        </tr>
        <tr>
            <td>
                ${admin.id}
            </td>
            <td>
                <input type="text" name="username" placeholder="在此处修改" value="${admin.username}" >
            </td>
            <td>
                <input type="password" name="password" placeholder="在此处修改" value="${admin.password}" >
            </td>
            <td>
                ${admin.roles}
            </td>
        </tr>
        </thead>
    </table>
        <input type="hidden" name="id" value="${admin.id}">
        <input type="hidden" name="roles" value="${admin.roles}">
    </form>
</div>
</body>
</html>
