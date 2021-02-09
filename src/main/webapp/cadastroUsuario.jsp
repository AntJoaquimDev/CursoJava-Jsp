<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="resources/css/cadastro.css"/>
    <!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/estiloCadastro.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

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

            <form action="salvarUsuario" method="post" id="formUser"
                  onsubmit="return validarCampos()?true : false" enctype="multipart/form-data">

                <ul class="form-style-1">

                    <table>
                        <tr>
                            <td>Codigo:</td>
                            <td><input type="text" id="id" name="id" readonly="readonly" value="${user.id}"
                                       class="field-long"></td>
                            <td>Cep:</td>
                            <td><input type="text" id="cep" name="cep" placeholder="Digite CEP válido"
                                       value="${user.cep} "
                                       onblur="consultaCep()"></td>
                        </tr>
                        <tr>
                            <td>Nome:</td>
                            <td><input type="text" id="nome" name="nome" value="${user.nome}">
                            </td>
                            <td>Rua:</td>
                            <td><input type="text" id="rua" name="rua" placeholder="Digite seu Nome "
                                       value="${user.rua}"></td>
                        </tr>
                        <tr>
                            <td>Telefone:</td>
                            <td><input type="text" id="telefone" name="telefone" value="${user.telefone}"
                                       class="field-long">
                            </td>
                            <td>Bairro:</td>
                            <td><input type="text" id="bairro" name="bairro" value="${user.bairro}"></td>
                        </tr>
                        <tr>
                            <td>Login:</td>
                            <td><input type="text" id="login" name="login" value="${user.login}" class="field-long">
                            </td>
                            <td>Cidade:</td>
                            <td><input type="text" id="cidade" name="cidade" value="${user.cidade}"></td>
                        </tr>
                        <tr>
                            <td>Senha:</td>
                            <td><input type="password" id="senha" name="senha" value="${user.senha}" class="field-long">
                            </td>
                            <td>Estado:</td>
                            <td><input type="text" id="uf" name="uf" size="5" value="${user.uf}"></td>
                        </tr>
                        <tr>
                            <td>Foto:</td>
                            <td><input type="file" name="foto">
                                <input type="text" style="display: none" name="fotoTemp64" readonly="readonly"
                                       value="${user.fotoBase64}">
                                <input type="text" style="display: none" name="contentTypeTemp" redonly="readonly"
                                       value="${user.contentType}">
                            </td>
                        </tr>
                        <tr>
                            <td>Curriculo:</td>

                            <td><input type="file" name="curriculo">
                                <input type="text" style="display: none" name="docTemp64" readonly="readonly"
                                       value="${user.docBase64}"></td>
                            <td><input type="text" style="display: none" name="contentTypeDocTemp" readonly="readonly"
                                    value="${user.contentTypeDoc}"></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar ">
                                <input type="submit" value="Cancelar " onclick="document.getElementById('formUser')
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
        <th>*=*=*=*</th>
        <th>Curriculo</th>
        <th>Nome</th>
        <th>Telefone</th>
        <th>Cep</th>
        <th>Rua</th>
        <th>Bairro</th>
        <th>Cidade</th>
        <th>Estado</th>
        <th>Login</th>
        <th>Senha</th>
        <th>Ed.Telefone</th>
        <th>Excluir</th>
        <th>Editar</th>
    </tr>
    </thead>

    <c:forEach items="${usuarios}" var="user">

        <tr>
            <td><c:out value="${user.id}"></c:out></td>

            <!-- inicio veirficação se exixte img ou nao. e colocar img padrao -->
            <c:if test="${user.fotoBase64.isEmpty() ==  false}">
                <td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}">
                    <img src='<c:out value="${user.tempFileUser}"/>' width="50px" height="50px"/></a></td>
            </c:if>
            <c:if test="${user.fotoBase64.isEmpty() == true}">
                <td><img alt="imagem User" src="img/user.png"  width="50px" height="50px"/></td>
            </c:if>
            <!--fim -->

            <!-- inicio veirficação se exixte Doc ou nao. e colocar img Doc padrao -->
            <c:if test="${user.docBase64.isEmpty() ==  false}">
            <td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"
                   title="Curriculo"><img alt="imagem User" src="img/arquivo.png.png"  width="50px" height="50px"/> </a></td>
            </c:if>
            <c:if test="${user.docBase64.isEmpty() == true}">
                <td><img alt="Doc User" src="img/arquivo.png" width="50px" height="50px"/></td>
            </c:if>
            <!--fim -->
            <td><c:out value="${user.nome}"></c:out></td>

            <td><c:out value="${user.telefone}"></c:out></td>

            <td><c:out value="${user.cep}"></c:out></td>
            <td><c:out value="${user.rua}"></c:out></td>
            <td><c:out value="${user.bairro}"></c:out></td>
            <td><c:out value="${user.cidade}"></c:out></td>
            <td><c:out value="${user.uf}"></c:out></td>

            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.senha}"></c:out></td>


            <td><a href="salvarTelefones?acao=addFone&user=${user.id}">
                <img src="img/fone.png" alt="Telefone" width="20px" height="20px" title="Telefone"/></a></td>

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

    $(document).ready(function () {

        function limpa_formulário_cep() {
            // Limpa valores do formulário de cep.
            $("#rua").val("");
            $("#bairro").val("");
            $("#cidade").val("");
            $("#uf").val("");
            $("#ibge").val("");
        }

        //Quando o campo cep perde o foco.
        $("#cep").blur(function () {

            //Nova variável "cep" somente com dígitos.
            var cep = $(this).val().replace(/\D/g, '');

            //Verifica se campo cep possui valor informado.
            if (cep != "") {

                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if (validacep.test(cep)) {

                    //Preenche os campos com "..." enquanto consulta webservice.
                    $("#rua").val("...");
                    $("#bairro").val("...");
                    $("#cidade").val("...");
                    $("#uf").val("...");
                    $("#ibge").val("...");

                    //Consulta o webservice viacep.com.br/
                    $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                        if (!("erro" in dados)) {
                            //Atualiza os campos com os valores da consulta.
                            $("#rua").val(dados.logradouro);
                            $("#bairro").val(dados.bairro);
                            $("#cidade").val(dados.localidade);
                            $("#uf").val(dados.uf);

                        } //end if.
                        else {
                            //CEP pesquisado não foi encontrado.
                            limpa_formulário_cep();
                            alert("CEP não encontrado.");
                        }
                    });
                } //end if.
                else {
                    //cep é inválido.
                    limpa_formulário_cep();
                    alert("Formato de CEP inválido.");
                }
            } //end if.
            else {
                //cep sem valor, limpa formulário.
                limpa_formulário_cep();
            }
        });
    });
</script>
</body>
</html>
