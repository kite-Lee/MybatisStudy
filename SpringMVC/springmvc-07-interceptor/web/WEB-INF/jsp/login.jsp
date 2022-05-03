<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--  在 WEB-INF 下的所有页面或资源只能通过 controller 或者 servlet 访问>  --%>
<h1>登录页面</h1>

<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名:<input type="text" name="username"/>
    密 码:<input type="text" name="password"/>
    <input type="submit" value="提交">

</form>
</body>
</html>
