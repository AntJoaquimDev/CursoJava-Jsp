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
        String acao = request.getParameter("acao");
        String user = request.getParameter("user");
        try {
            if (acao.equalsIgnoreCase("delete")) {
                daoUsuario.delete(user);
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro

            } else if (acao.equalsIgnoreCase("editar")) {

                UsuarioBean usuarioBean = daoUsuario.consultar(user); // consulta usuario para deletar, passando o objeto
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("user", usuarioBean);
                view.forward(request, response); // -->

            } else if (acao.equalsIgnoreCase("listarTodos")) {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String acao = request.getParameter("acao");
        if (acao != null && acao.equalsIgnoreCase("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");

            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
            usuarioBean.setNome(nome);
            usuarioBean.setLogin(login);
            usuarioBean.setSenha(senha);

            try {
                if(id == null || id.isEmpty() && !daoUsuario.validarLogin(login)){
                    request.setAttribute("msg","[ERRO!] Login já está Cadastrado no Sistema");
                }
                if (id == null || id.isEmpty() && daoUsuario.validarLogin(login)) {
                    daoUsuario.salvar(usuarioBean);
                } else {
                    daoUsuario.atualizar(usuarioBean);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("usuario salvo no banco");

            //para mandar resultadao da pesquisa para mesma tela de cadastro
            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());

                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
