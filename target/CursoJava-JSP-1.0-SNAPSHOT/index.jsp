<jsp:useBean id="calcula" class="bens.UsuarioBean" type="bens.UsuarioBean" scope="page"/>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE >
<link rel="stylesheet" href="css/estiloTelaLogin.css"/>
<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
   <link rel="stylesheet" href="">
    <title>JSP - Basico de JSP</title>

</head>
<body>
<H1 class="h1-login"> Tela Login JAVA-JSP</H1>

<div class="login-page">
    <div class="form">
        <form action="Servlet-Login" method="post" class="login-form">
            Login:<input type="text" id="login" name="login">

            Senha:<input type="password" id="senha" name="senha">
            <button type="submit" value="Logar">LOGAR</button>
        </form>
    </div>
</div>
</body>
</html>
