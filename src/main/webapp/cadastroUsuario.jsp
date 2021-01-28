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
<center><h1>Cadastro de Usuário</h1></center>
<center><div class="login-page">
    <div class="form">

        <h3 style="color: #ea100f">${msg} </h3>
        <h3 style="color: #29ee09">${msg2} </h3>

        <form action="salvarUsuario" method="post" id="formeUser">
            <ul class="form-style-1">

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
                            <td>Telefone:</td>
                            <td><input type="text" id="telefone" name="telefone" value="${user.telefone}" class="field-long">
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


            </ul>
        </form>
    </div>
</div></center>
<table class="w3-table-all">

    <thead>
    <caption><h2>Usuários cadastrados no Sistema</h2></caption>
    <tr class="w3-green">
        <th>Codigo</th>
        <th>Nome</th>
        <th>Telefone</th>
        <th>Login</th>
        <th>Senha</th>

        <th>Excluir Usuário</th>
        <th>Editar Usuário</th>
    </tr>
    </thead>

    <c:forEach items="${usuarios}" var="user">

        <tr>
            <td><c:out value="${user.id}"></c:out></td>

            <td><img src="img/user.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/>  <c:out value="${user.nome}"></c:out>  </td>

            <td><img src="fone.png" alt="Editar" width="20px" height="20px"
                     title="Telefone"/>  <c:out value="${user.telefone}"></c:out>  </td>

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
