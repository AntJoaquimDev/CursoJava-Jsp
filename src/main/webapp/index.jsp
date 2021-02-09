<jsp:useBean id="calcula" class="bens.UsuarioBean" type="bens.UsuarioBean" scope="page"/>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE >
<link rel="stylesheet" href="css/estiloTelaLogin.css"/>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
   <link rel="stylesheet" href="">
    <title>JSP - Basico de JSP</title>

</head>
<body>
<H1 class="h1-login"> Tela Login JAVA-JSP</H1>

<div class="login-page">
    <div class="form">
        <form action="Servlet-Login"  method="post" class="login-form" id="formlogin"
              onsubmit="return validarCampos()?true : false" >
            <ul class="form-style-1">

                <img src="img/user.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/>
            Login<input type="text" id="login" name="login" class="login-page"  >

                <img src="img/cadeado.png" alt="Editar" width="20px" height="20px"
                     title="usuário -->"/>
            Senha<input type="password" id="senha" name="senha" class="login-page">
            <button type="submit" value="Logar" >LOGAR</button>

                </ul>
        </form>
    </div>
</div>
<script type="text/javascript">
    function validarCampos() {
        if (document.getElementById("login").value == '') {
            alert('Informe o Login e Senha:');
            return false;
        } else if (document.getElementById("senha").value == '') {
            alert('Informe a Senha:');
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
