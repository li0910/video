<%--
  Created by IntelliJ IDEA.
  User: 李博
  Date: 2020/10/20
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程管理</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

    <!-- 如果IE版本小于9，加载以下js,解决低版本兼容问题 -->
    <!--[if lt IE 9]-->
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/confirm.js"></script>

    <script type="text/javascript">
        function showAddPage() {
            location.href = "${pageContext.request.contextPath}/course/addCourse";
        }

        function delSpeakerById(Obj, id, title) {

            Confirm.show('温馨提示：', '确定要删除' + title + '么？', {
                'Delete': {
                    'primary': true,
                    'callback': function () {
                        var param = {"id": id};
                        $.post("/course/courseDel", param, function (data) {
                            if (data == 'success') {
                                Confirm.show('温馨提示：', '删除成功');
                                $(Obj).parent().parent().remove();
                            } else {
                                Confirm.show('温馨提示：', '操作失败');
                            }
                        });
                    }
                }
            });
        }
    </script>
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

                <%--                管理员名称--%>
                <a href="/admin/toEdit"><span>${admin.username}</span></a>&nbsp;&nbsp;
                <i class="glyphicon glyphicon-log-in" aria-hidden="true"></i>&nbsp;&nbsp;
                <%--                退出登录--%>
                <a href="${pageContext.request.contextPath}/admin/exit" class="navbar-link">退出</a>
            </p>
        </div>
        <!-- /.navbar-collapse -->


    </div>
    <!-- /.container-fluid -->
</nav>
<div class="jumbotron" style="padding-top: 15px;padding-bottom: 15px;">
    <div class="container">
        <h2>课程管理</h2>
    </div>
</div>
<div class="container">


    <button onclick="showAddPage()" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
        添加
    </button>

</div>

<div class="container" style="margin-top: 20px;">

    <table class="table table-bordered table-hover"
           style="text-align: center;table-layout:fixed;">
        <thead>
        <tr class="active">
            <th>序号</th>
            <th style="width:20%;">名称</th>
            <th style="width:60%;">课程简介</th>
            <th>所属学科</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="course" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${course.courseTitle}</td>

                <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${course.courseDesc}</td>
                <td>${course.subject.subjectName}</td>
                <td><a href="${pageContext.request.contextPath}/course/edit?id=${course.id}"><span
                        class="glyphicon glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
                <td><a href="#" onclick="return delSpeakerById(this,'${course.id}','${course.courseTitle}')"><span
                        class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
            </tr>

        </c:forEach>


        </tbody>
    </table>

</div>

<%--        <p:page url="${pageContext.request.contextPath}/speaker/showSpeakerList"></p:page>--%>
<nav aria-label="Page navigation">
    <ul class="pager">
        <li><a href="/video/list?pageNum=${page.navigateFirstPage}">首页</a></li>
        <c:if test="${page.hasPreviousPage}">
            <li><a href="/video/list?pageNum=${page.prePage}">上一页</a></li>
            <li><a href="/video/list?pageNum=${page.prePage}">${page.prePage}</a></li>
        </c:if>
        <li><a href="/video/list?pageNum=${page.pageNum}"><font color="red">${page.pageNum}</font></a></li>
        <c:if test="${page.hasNextPage}">
            <li><a href="/video/list?pageNum=${page.nextPage}">${page.nextPage}</a></li>
            <li><a href="/video/list?pageNum=${page.nextPage}">下一页</a></li>
        </c:if>
        <li><a href="/video/list?pageNum=${page.navigateLastPage}">末页</a></li>
        <%--        <p:page url="${pageContext.request.contextPath}/video/list"></p:page>--%>
    </ul>
</nav>
</body>
</html>
