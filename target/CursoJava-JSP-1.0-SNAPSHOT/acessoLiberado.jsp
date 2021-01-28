<jsp:useBean id="calcula" class="bens.UsuarioBean" type="bens.UsuarioBean" scope="page"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>recebendo nome</title>
</head>
<body >
<jsp:setProperty name="calcula" property="*"/>
<h1>Acesso liberado para seu Usuario</h1>
<h2>Seja benvindo ao Sistema JSP</h2>

<br/>
<tr>
<a href="salvarUsuario?acao=listarTodos">
<img src="img/user.png" alt="Editar" width="120px" height="120px" title="Cadastro de Usuário -->"/>
</a>
</tr>
</body>

</html>
