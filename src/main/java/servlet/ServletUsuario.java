package servlet;

import bens.UsuarioBean;
import dao.DaoUsuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "salvarUsuario", value = "/salvarUsuario")
public class ServletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1l;
    DaoUsuario daoUsuario = new DaoUsuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String login = request.getParameter("login");

        String senha = request.getParameter("senha");

        UsuarioBean usuarioBean = new UsuarioBean();

        usuarioBean.setLogin(login);
        usuarioBean.setSenha(senha);

        daoUsuario.salvar(usuarioBean);
        System.out.println("usuario salvo no banco");
        //para mandar resultadao da pesquisa para mesma tela de cadastro

        try {
            RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
            request.setAttribute("usuarios", daoUsuario.listar());
            view.forward(request,response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }
}
