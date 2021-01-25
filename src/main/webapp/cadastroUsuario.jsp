<%--
  Created by IntelliJ IDEA.
  User: antonio joaquim
  Date: 24/01/2021
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cadastro de Usuário</title>
</head>
<body >
<h1>Formulário Cadastro de Usuario</h1>
<form action="salvarUsuario" method="post">
    <table>
        <tr>
            <td>Codigo:</td>
            <td><input type="text" id="id" name="id" value="${user.id}"></td>
        </tr>
        <tr>
            <td>Logi:</td>
            <td><input type="text" id="login" name="login" value="${user.login}"></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" id="senha" name="senha" value="${user.senha}"></td>
        </tr>
    </table>
    <input type="submit" value="Salvar ">
</form>

<table>
    <td>Codigo </td><td>Login </td> <td>Senha </td>
   <c:forEach items="${usuarios}" var="user">
       <tr style="background-color: #d29999">

           <td style="width: 50px"><c:out value="${user.id}"></c:out></td>

           <td style="width: 150px"><c:out value="${user.login}"></c:out></td>

           <td><c:out value="${user.senha}"></c:out></td>
           <td> <a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a> </td>
           <td> <a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a> </td>
       </tr>
   </c:forEach>
</table>

</body>
</html>
