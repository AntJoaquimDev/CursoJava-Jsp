<%--
  Created by IntelliJ IDEA.
  User: ajoaq
  Date: 30/01/2021
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="css/estiloProduto.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Cadastro de Produto</title>
</head>
<body>
<a href="acessoLiberado.jsp">
    <img src="img/sair.png" alt="Editar" width="50px" height="50px" title="Voltar Para inicio-->"/>
</a>
<td></td>
<a href="index.jsp">
    <img src="img/logout-48.png" alt="Editar" width="50px" height="50px" title="Sair do Sistema-->"/>
</a>
<center><h1>Cadastro de Produtos</h1></center>
<center>
    <div class="login-page">
        <div class="form">
            <h3 style="color: #ea100f">${msg} </h3>
            <h3 style="color: #29ee09">${msg2} </h3>
            <form action="salvarProduto" method="post" id="formProduto" onsubmit="validarCampos()">
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
                            <td><input type="text" id="qtd" name="qtd" value="${produto.qtd}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Valor unt R$:</td>
                            <td><input type="text" id="valor" name="valor" value="${produto.valor}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar ">
                                <input type="submit" value="Cancelar " onclick="document.getElementById('formProduto')
         .action = 'salvarProduto?acao=reset'"></td>
                        </tr>
                    </table>
                </ul>
            </form>
        </div>
    </div>
</center>
<table class="w3-table-all">
    <thead>
    <caption><h2>Usuários cadastrados no Sistema</h2></caption>
    <tr class="w3-green">
        <th>Codigo</th>
        <th>Produto</th>
        <th>Quant</th>
        <th>Valor R$:</th>


        <th>Excluir Produto</th>
        <th>Editar Produto</th>

    </tr>
    </thead>

    <c:forEach items="${produtos}" var="produto">

        <tr>
            <td><c:out value="${produto.id}"></c:out></td>

            <td><c:out value="${produto.nome}"></c:out></td>

            <td><c:out value="${produto.qtd}"></c:out></td>

            <td><c:out value="${produto.valor}"></c:out></td>


            <td><a href="salvarProduto?acao=delete&produto=${produto.id}">
                <img src="img/delete.png" alt="Excluir" width="20px" height="20px" title="Excluir"/></a></td>

            <td><a href="salvarProduto?acao=editar&produto=${produto.id}">
                <img src="img/editar.png" alt="Editar" width="20px" height="20px" title="Editar"/></a></td>
        </tr>

    </c:forEach>
</table>
<script type="text/javascript">
    function validarCampos() {
        if (document.getElementById("nome").value == '') {
            alert("Nome do Produto é obrigatório!")
        } else if (document.getElementById("qtd").value == '') {
            alert("Qauntidade do Produto é obrigatório!")
        } else if (document.getElementById("valor").value == '') {
            alert("Valor R$ é do Produto é obrigatório!")
        }
    }
</script>
</body>
</html>