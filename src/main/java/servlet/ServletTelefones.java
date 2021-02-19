package servlet;

import bens.TelefonesBean;
import bens.UsuarioBean;
import dao.DaoTelefones;
import dao.DaoUsuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "/salvarTelefones", value = "/salvarTelefones")
public class ServletTelefones extends HttpServlet {
    private static final long serialVersionUID = 1l;
    DaoUsuario daoUsuario = new DaoUsuario();
    DaoTelefones daoTelefones = new DaoTelefones();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            String acao = request.getParameter("acao");
            String user = request.getParameter("user");
            if (user != null) {

                if (acao.endsWith("addFone")) {
                    UsuarioBean usuario = daoUsuario.consultar(user);

                    request.getSession().setAttribute("userEscolhido", usuario);
                    request.setAttribute("userEscolhido", usuario);

                    RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
                    request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
                    request.setAttribute("msg", "Salvo com Sucesso");
                    view.forward(request, response);

                } else if (acao.endsWith("deleteFone")) { // remover telefone
                    String foneId = request.getParameter("foneId");

                    daoTelefones.delete(foneId);

                    UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("userEscolhido");
                    //recarregar os telefoes na tela
                    RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
                    request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
                    request.setAttribute("msg", "Removido com Sucesso");
                    view.forward(request, response);
                }
            } else {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                request.setAttribute("msg", "");
                view.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("userEscolhido");
            //para pegar numero e tipo
            String numero = request.getParameter("numero");
            String tipo = request.getParameter("tipo");

            TelefonesBean telefonesBean = new TelefonesBean();
            telefonesBean.setNumero(numero);
            telefonesBean.setTipo(tipo);
            telefonesBean.setUsuario(usuario.getId());

            daoTelefones.salvar(telefonesBean);

            request.getSession().setAttribute("userEscolhido", usuario);
            request.setAttribute("userEscolhido", usuario);

            RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
            request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
            request.setAttribute("msg", "Salvo com Sucesso");

            view.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

