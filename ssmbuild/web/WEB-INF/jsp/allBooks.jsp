<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <%--  BootStrap 美化界面  --%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表--显示所有书籍</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/allBooks">返回书籍目录</a>
            </div>
            <div class="col-md-8 column">
                <form action="${pageContext.request.contextPath}/book/getBookByName" method="post"
                      class="form-inline" style="float: right">
                            <input type="text" name="bookName" class="form-control" placeholder="请输入要查询的书籍名称">
                            <input type="submit" value="查询" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 column">
                <div>

                    <%--  搜索为空时的三种解决方案  --%>
                    <c:if test="${true eq error}">

                        <%--   提示窗口    --%>
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h4 style="color: red; text-align: center">
                                <strong>注意! </strong>查询内容为空！请<a href="${pageContext.request.contextPath}/book/allBooks" >返回书籍目录</a>
                            </h4>
                        </div>

                        <%--   alert 弹窗  --%>
                        <%out.print("<script>alert('查找内容为空');</script>");%>

                        <%--  重定向首页   --%>
                        <%response.sendRedirect(request.getContextPath()+"/book/allBooks");%>

                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/book/toUpdateBook?id=${book.bookID}">修改</a>
                                &nbsp; | &nbsp;
                                <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>
