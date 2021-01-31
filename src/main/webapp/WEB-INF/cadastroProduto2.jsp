<%--
  Created by IntelliJ IDEA.
  User: ajoaq
  Date: 30/01/2021
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="css/estiloProduto.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Cadastro de Produto</title>
</head>
<body>
<center><h1>Cadastro de Produtos</h1></center>
<center>
    <div class="login-page">
        <div class="form">
            <h3 style="color: #ea100f">${msg} </h3>
            <h3 style="color: #29ee09">${msg2} </h3>
            <form action="salvarProduto" method="post" id="formeProduto">
                <ul class="form-style-1">
                    <table>
                        <tr>
                            <td>Codigo Produto</td>
                            <td><input type="text" id="id" name="id" value="${produto.id}" class="field-long"></td>
                        </tr>
                        <tr>
                            <td>Nome Produto</td>
                            <td><input type="text" id="nome" name="nome" value="${produto.nome}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Qtd Produto</td>
                            <td><input type="text" id="qtd" name="qyd" value="${produto.quantidade}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Valor Produto</td>
                            <td><input type="text" id="valor" name="valor" value="${produto.valor}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar ">
                                <input type="submit" value="Cancelar " onclick="document.getElementById('formeUser')
         .action = 'salvarProduto?reset'"></td>
                        </tr>
                    </table>
                </ul>
            </form>
        </div>
    </div>
</center>
<table class="w3-table-all">
    <thead>
    <caption><h2>Produtos Cadastrados no Sistema</h2></caption>
    <tr class="w3-green">
        <th>Codigo</th>
        <th>Produto</th>
        <th>Quant</th>
        <th>Valor</th>


        <th>Excluir Produto</th>
        <th>Editar Produto</th>

    </tr>
    </thead>

    <c:forEach items="${produtos}" var="produto">

           <tr>
            <td><c:out value="${produto.id}"></c:out></td>

            <td><img src="img/user.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/> <c:out value="${produto.nome}"></c:out></td>

            <td><img src="img/fone.png" alt="Editar" width="20px" height="20px"
                     title="Telefone"/> <c:out value="${produto.quantidade}"></c:out></td>

            <td><c:out value="${produto.valor}"></c:out></td>


            <td><a href="salvarProduto?acao=delete&produto=${produto.id}">
                <img src="img/delete.png" alt="Excluir" width="20px" height="20px" title="Excluir"/></a></td>
            <td><a href="salvarProdutio?acao=editar&produto=${produto.id}">
                <img src="img/editar.png" alt="Editar" width="20px" height="20px" title="Editar"/></a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>