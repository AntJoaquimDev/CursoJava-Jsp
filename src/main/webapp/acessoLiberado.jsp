<jsp:useBean id="calcula" class="bens.UsuarioBean" type="bens.UsuarioBean" scope="page"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>recebendo nome</title>
</head>
<body>
<center style="padding-top: 10%;">
    <h1>Acesso liberado para seu Usuario</h1>
    <h2>Seja benvindo ao Sistema JSP</h2>
</center>
<br/>
<center>
    <table>
        <tr>
            <td>
                <a href="salvarUsuario?acao=listarTodos">
                    <img src="img/user.png" width="100px" height="100px" title="Cadastro de Usuário -->"/>
                </a>
            </td>
            <td>
                <a href="salvarProduto?acao=listarTodos">
                    <img src="img/carrinho.png" width="100px" height="100px" title="Cadastro de Produto -->"/>
                </a>
            </td>
        </tr>
        <tr>
            <td>Cad. Usúsario</td>
            <td>Cad. Produto</td>
        </tr>

    </table>
</center>
</body>

</html>
