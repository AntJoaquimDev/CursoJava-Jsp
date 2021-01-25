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
        <form action="salvarUsuario" method="post">
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
                            <td><input type="submit" value="Salvar "></td>
                        </tr>
                    </table>

                </li>
            </ul>
        </form>
    </div>
</div>
<table class="w3-table-all">

    <thead>
    <caption>Usuários cadastrados no Sistema</caption>
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
            <td ><c:out value="${user.id}"></c:out></td>
            <td ><c:out value="${user.nome}"></c:out></td>
            <td ><c:out value="${user.login}"></c:out></td>
            <td ><c:out value="${user.senha}"></c:out></td>


            <td><a href="salvarUsuario?acao=delete&user=${user.id}">Excluir</a></td>
            <td><a href="salvarUsuario?acao=editar&user=${user.id}">Editar</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
