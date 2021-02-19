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

    <!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <title>Cadastro de Telefones</title>
</head>
<body>
<a href="index.jsp">
    <img src="img/logout-48.png" alt="Editar" width="30px" height="30px" title="Sair do Sistema-->"/>
</a>
<td></td>
<a href="salvarUsuario?acao=listarTodos">
    <img src="img/sair.png" alt="Editar" width="45px" height="40px" title="Voltar Para Tela Usuario-->"/>
</a>


<center><h1>Cadastro de Telefones</h1></center>
<center>
    <div class="login-page">
        <div class="form">

            <h3 style="color: #ea100f">${msg} </h3>
            <h3 style="color: #29ee09">${msg2} </h3>

            <form action="salvarTelefones" method="post" id="formfone" onsubmit="return validarCampos()?true :false;">
                <ul class="form-style-1">

                    <table>
                        <tr>
                            <td>User:</td>
                            <td><input type="text" readonly="readonly" id="id" name="id"
                                       class="field-long"  value="${userEscolhido.id}"></td>

                            <td><input type="text" readonly="readonly" id="nome" name="nome"
                                       class="field-long" value="${userEscolhido.nome}"></td>
                        </tr>
                        <tr>
                            <td>Número:</td>
                            <td><input type="textt" id="numero" name="numero" class="field-long"
                                 placeholder="Digite o Telefone " maxlength="15" ></td>
                            <td><select id="tipo" name="tipo" style="width: 173px;">
                                <option>Casa</option>
                                <option>Trabalho</option>
                                <option>Celular</option>
                                <option>Outros</option>
                            </select></td>
                        </tr>
                        <td></td>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar" style="background-color: #29ee09">

                            <td>
                                <a href="salvarUsuario?acao=listarTodos">
                                <img src="img/logout-48.png" alt="Editar" width="45px" height="30px" title="Voltar Para Tela Usuario-->"/>
                                </a></td>
                        </tr>
                    </table>
                </ul>
            </form>
        </div>
    </div>
</center>


<table class="w3-table-all">

    <thead>
    <caption><h2>Lista de Telefones</h2></caption>
    <tr class="w3-green">
        <th>Codigo</th>
        <th>Numero</th>
        <th>Tipo</th>
        <th>Excluir</th>
    </tr>
    </thead>

    <c:forEach items="${telefones}" var="fone">
        <tr>
            <td>
                <c:out value="${fone.id}"></c:out>
            </td>
            <td>
                <c:out value="${fone.numero}"></c:out>
            </td>
            <td><c:out value="${fone.tipo}"></c:out></td>

            <td><a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}" onclick="return confirm('Confirma a Exclusão?')">
                <img src="img/delete.png" alt="Excluir" width="20px" height="20px" title="Excluir"/></a></td>
        </tr>
    </c:forEach>

</table>
<script type="text/javascript">
    function validarCampos() {
        if (document.getElementById("numero").value == '') {
            alert('Informe o Número do Telefone :');
            return false;
        }

        return true;
    }

</script>
</body>
</html>
