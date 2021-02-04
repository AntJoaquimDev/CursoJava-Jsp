package servlet;

import bens.UsuarioBean;
import dao.DaoUsuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/salvarUsuario", value = "/salvarUsuario")
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
                request.setAttribute("msg2", "Cadastro Deletado!.");
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
            String telefone = request.getParameter("telefone");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");


            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
            usuarioBean.setNome(nome);
            usuarioBean.setLogin(login);
            usuarioBean.setSenha(senha);
            usuarioBean.setTelefone(telefone);
            usuarioBean.setCep(cep);
            usuarioBean.setRua(rua);
            usuarioBean.setBairro(bairro);
            usuarioBean.setCidade(cidade);
            usuarioBean.setUf(uf);
            try {

                /*Inicio upload
                if(ServletFileUpload.isMultipartContent(request)){

                    List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    for (FileItem fileItem : fileItems) {
                        if(fileItem.getFieldName().equals("foto")){
                            String foto = new Base64().encodeBase64String(fileItem.get());
                            System.out.println(foto);
                        }
                    }
                }


                Fim upload*/

                String msg = null;
                boolean podeInserir = true;

                if (login == null || login.isEmpty()) {
                    msg = "Login deve ser informado";
                    podeInserir = false;

                } else if (senha == null || senha.isEmpty()) {
                    msg = "Senha deve ser informado";
                    podeInserir = false;

                } else if (nome == null || nome.isEmpty()) {
                    msg = "Nome deve ser informado";
                    podeInserir = false;

                } else if (telefone == null || telefone.isEmpty()) {
                    msg = "Telefone deve ser informado";
                    podeInserir = false;

                }

                else if (id == null || id.isEmpty()
                        && !daoUsuario.validarLogin(login)) {// QUANDO DOR
                    // USUÁRIO NOVO
                    msg = "Usuário já existe com o mesmo login!";
                    podeInserir = false;

                } else if (id == null || id.isEmpty()
                        && !daoUsuario.validarSenha(senha)) {// QUANDO FOR
                    // USUÁRIO NOVO
                    msg = "\n A senha já existe para outro usuário!";
                    podeInserir = false;
                }

                if (msg != null) {
                    request.setAttribute("msg", msg);
                } else if (id == null || id.isEmpty()
                        && daoUsuario.validarLogin(login) && podeInserir) {

                    daoUsuario.salvar(usuarioBean);

                } else if (id != null && !id.isEmpty() && podeInserir) {
                    daoUsuario.atualizar(usuarioBean);
                }

                if (!podeInserir) {
                    request.setAttribute("user", usuarioBean);
                }

                RequestDispatcher view = request
                        .getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                request.setAttribute("msg","Salvo com Sucesso");
                view.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}

