<jsp:useBean id="calcula" class="bens.UsuarioBean" type="bens.UsuarioBean" scope="page"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>recebendo nome</title>
</head>
<body>
<a href="index.jsp">
    <img src="img/fechar.ico" alt="Sair" width="40px" height="40px" title="Sair do Sistema-->"/>
</a>
<td></td>
<a href="acessoLiberado.jsp">
    <img src="img/logout-48.png" alt="Editar" width="35px" height="35px" title="Voltar Para inicio-->"/>
</a>
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
