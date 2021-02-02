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

    <title>Cadastro de Usuário</title>
</head>
<body>
<a href="index.jsp">
    <img src="img/logout-48.png" alt="Editar" width="50px" height="50px" title="Sair do Sistema-->"/>
</a>
<td></td>
<a href="acessoLiberado.jsp">
    <img src="img/sair.png" alt="Editar" width="60px" height="40px" title="Voltar Para inicio-->"/>
</a>


<center><h1>Cadastro de Usuário</h1></center>
<center>
    <div class="login-page">
        <div class="form">

            <h3 style="color: #ea100f">${msg} </h3>
            <h3 style="color: #29ee09">${msg2} </h3>

            <form action="salvarUsuario" method="post" id="formeUser" onsubmit="validarCampos()">
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
                            <td><input type="text" id="telefone" name="telefone" value="${user.telefone}"
                                       class="field-long">
                            </td>
                        </tr>
                        <tr>
                            <td>Cep:</td>
                            <td><input type="text" id="cep" name="cep" size="10" value="${user.cep} "
                                       onblur="consultaCep()"></td>
                        </tr>
                        <tr>
                            <td>Rua:</td>
                            <td><input type="text" id="rua" name="rua" value="${user.rua}"></td>
                        </tr>
                        <tr>
                            <td>Bairro:</td>
                            <td><input type="text" id="bairro" name="bairro" value="${user.bairro}"></td>
                        </tr>
                        <tr>
                            <td>Cidade:</td>
                            <td><input type="text" id="cidade" name="cidade" value="${user.cidade}"></td>
                        </tr>

                        <tr>
                            <td>Estado:</td>
                            <td><input type="text" id="uf" name="uf" size="5" value="${user.uf}"></td>
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
    </div>
</center>


<table class="w3-table-all">

    <thead>
    <caption><h2>Usuários cadastrados no Sistema</h2></caption>
    <tr class="w3-green">
        <th>Codigo</th>
        <th>Nome</th>
        <th>Telefone</th>
        <th>Cep</th>
        <th>Rua</th>
        <th>Bairro</th>
        <th>Cidade</th>
        <th>Estado</th>
        <th>Login</th>
        <th>Senha</th>

        <th>Excluir</th>
        <th>Editar</th>
    </tr>
    </thead>

    <c:forEach items="${usuarios}" var="user">

        <tr>
            <td><c:out value="${user.id}"></c:out></td>

            <td><img src="img/user.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/> <c:out value="${user.nome}"></c:out></td>

            <td><img src="img/fone.png" alt="Editar" width="20px" height="20px"
                     title="Telefone"/> <c:out value="${user.telefone}"></c:out></td>

            <td><c:out value="${user.cep}"></c:out></td>
            <td><c:out value="${user.rua}"></c:out></td>
            <td><c:out value="${user.bairro}"></c:out></td>
            <td><c:out value="${user.cidade}"></c:out></td>
            <td><c:out value="${user.uf}"></c:out></td>

            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.senha}"></c:out></td>


            <td><a href="salvarUsuario?acao=delete&user=${user.id}">
                <img src="img/delete.png" alt="Excluir" width="20px" height="20px" title="Excluir"/></a></td>
            <td><a href="salvarUsuario?acao=editar&user=${user.id}">
                <img src="img/editar.png" alt="Editar" width="20px" height="20px" title="Editar"/></a></td>
        </tr>
    </c:forEach>

</table>
<script type="text/javascript">
    function validarCampos() {
        if (document.getElementById("nome").value == '') {
            alert('Informe o Nome:');
            return false;
        } else if (document.getElementById("telefone").value == '') {
            alert('Informe o Telefone:');
            return false;
        } else if (document.getElementById("login").value == '') {
            alert('Informe o login:');
            return false;
        } else if (document.getElementById("Senha").value == '') {
            alert('Informe o Senha:');
            return false;
        }
        return true;
    }

    function consultaCep() {
        var cep = $("#cep").val();
        //alert(cep);
        //Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

            if (!("erro" in dados)) {
                //enviardados ao form

                $("#rua").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#cidade").val(dados.localidade);
                $("#uf").val(dados.uf);

            } //end if.
            else {
                //CEP pesquisado não foi encontrado.
                limpa_formulário_cep()
                alert("CEP não encontrado.");
            }
        });
    } //end if.
    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        $("#cep").val("");
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#uf").val("");

    }

</script>
</body>
</html>
