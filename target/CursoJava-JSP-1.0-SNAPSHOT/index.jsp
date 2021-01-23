<jsp:useBean id="calcula" class="bens.BeanCursoJsp" type="bens.BeanCursoJsp" scope="page"/>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE >

<html>

<head>
    <title>JSP - Basico de JSP</title>
</head>
<body style="background: aqua">
<H1> Exercios de Treinos Jsp</H1>

<form action="Servlet-Login" method="post">

    Login:
    <input type="text" id="login" name="login">
    <br/>
    Senha:
    <input type="text" id="senha" name="senha">
    <br>
    <input type="submit" value="Logar">

</form>

</body>
</html>
