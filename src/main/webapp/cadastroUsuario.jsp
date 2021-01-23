<%--
  Created by IntelliJ IDEA.
  User: ajoaquim
  Date: 23/01/2021
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cadastro de Usuário</title>
</head>
<body style="background: #d9a67a">
<h1>Cadastri de Usuario</h1>
<form action="salvarUsuario" method="post">
    <table>
        <tr>
            <td>Logi:</td>
            <td><input type="text" id="login" name="login"></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" id="senha" name="senha"></td>
        </tr>
    </table>
    <input type="submit" value="Salvar ">
</form>

<table>
   <c:forEach items="${usuarios}" var="user">
       <tr style="background-color: #d29999">
           <td style="width: 150px"><c:out value="${user.login}"></c:out></td>
           <td><c:out value="${user.senha}"></c:out></td>
       </tr>
   </c:forEach>
</table>

</body>
</html>
