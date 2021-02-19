<%--
  Created by IntelliJ IDEA.
  User: ajoaq
  Date: 30/01/2021
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/estiloProduto.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <script type="text/javascript" src="jscript/jquery.min.js"></script>
    <script type="text/javascript" src="jscript/jquery.maskMoney.min.js"></script>

    <title>Cadastro de Produto</title>
</head>
<body>

<a href="index.jsp">
    <img src="img/fechar.ico" alt="Sair" width="40px" height="40px" title="Sair do Sistema-->"/>
</a>
<td></td>
<a href="acessoLiberado.jsp">
    <img src="img/logout-48.png" alt="Editar" width="35px" height="35px" title="Voltar Para inicio-->"/>
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
                            <td><input type="text" id="id" name="id" readonly="readonly" value="${produto.id}"
                                       class="field-long"></td>
                        </tr>
                        <tr>
                            <td>Nome Produto</td>
                            <td><input type="text" id="nome" name="nome" value="${produto.nome}" class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Qtd Produto</td>
                            <td><input type="text" id="qtd" name="qtd" value="${produto.qtd}"
                                       class="field-long" maxlength="8" >

                            </td>
                        </tr>
                        <tr>
                            <td>Valor R$:</td>
                            <td><input type="text" id="valor" name="valor" value="${produto.valorEmTxt}"
                                       data-thousands="." data-decimal="."
                                       class="field-long" maxlength="8">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar" style="width: 112px">
                                <input type="submit" value="Cancelar " onclick="document.getElementById('formProduto')
         .action = 'salvarProduto?reset'" style="width: 112px"></td>
                        </tr>
                    </table>
                </ul>
            </form>
        </div>
    </div>
</center>
<table class="w3-table-all">
    <thead>
    <caption><h2>Lista de Produtos </h2></caption>
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

            <td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${produto.valor}"/> </td>



            <td><a href="salvarProduto?acao=delete&produto=${produto.id}" onclick="return confirm('Confirma a Exclusão?')">
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
<script type="text/javascript">
    $(function () {
        $('#valor').maskMoney();
    })


    $(document).ready(function () {
       $('#qtd').keyup(function (){
            $('#qtd').val(this.value.match(/[0-9]*/));
        });
    });
</script>
</html>