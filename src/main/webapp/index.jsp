<jsp:useBean id="calcula" class="bens.UsuarioBean" type="bens.UsuarioBean" scope="page"/>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE >
<link rel="stylesheet" href="css/estiloTelaLogin.css"/>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
            <ul class="form-style-1">

                <img src="img/user.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/>
            Login<input type="text" id="login" name="login" class="login-page"  >

                <img src="img/cadeado.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/>
            Senha<input type="password" id="senha" name="senha" class="login-page">
            <button type="submit" value="Logar">LOGAR</button>
                </ul>
        </form>
    </div>
</div>
</body>
</html>
