<%--
  Created by IntelliJ IDEA.
  User: antonio joaquim
  Date: 24/01/2021
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="css/estiloCadastro.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Cadastro de Usuário</title>
</head>
<body>
<h1>Formulário Cadastro de Usuario</h1>
<div class="login-page">
    <div class="form">
        <form action="salvarUsuario" method="post" id="formeUser">
            <ul class="form-style-1">
                <li>
                    <table>
                        <tr>
                            <td>Codigo:</td>
                            <td><input type="text" id="id" name="id" value="${user.id}" class="field-long"></td>
                        </tr>
                        <tr>
                            <td>Nome:</td>
                            <td><input type="text" id="nome" name="nome" value="${user.nome}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Login:</td>
                            <td><input type="text" id="login" name="login" value="${user.login}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Senha:</td>
                            <td><input type="password" id="senha" name="senha" value="${user.senha}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar ">
                                <input type="submit" value="Cancelar " onclick="document.getElementById('formeUser')
         .action = 'salvarUsuario?reset'"></td>
                        </tr>
                    </table>

                </li>
            </ul>
        </form>
    </div>
</div>
<table class="w3-table-all">

    <thead>
    <caption><h2>Usuários cadastrados no Sistema</h2></caption>
    <tr class="w3-green">
        <th>Codigo</th>
        <th>Nome</th>
        <th>Login</th>
        <th>Senha</th>
        <th>Excluir Usuário</th>
        <th>Editar Usuário</th>
    </tr>
    </thead>

    <c:forEach items="${usuarios}" var="user">

        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.nome}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.senha}"></c:out></td>


            <td><a href="salvarUsuario?acao=delete&user=${user.id}">
                <img src="img/delete.png" alt="Excluir" width="20px" height="20px" title="Excluir"/></a></td>
            <td><a href="salvarUsuario?acao=editar&user=${user.id}">
                <img src="img/editar.png" alt="Editar" width="20px" height="20px" title="Editar"/></a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
