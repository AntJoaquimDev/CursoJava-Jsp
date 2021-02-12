package servlet;

import bens.UsuarioBean;
import dao.DaoUsuario;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;

@WebServlet(name = "/salvarUsuario", value = "/salvarUsuario")
@MultipartConfig
public class ServletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1l;
    DaoUsuario daoUsuario = new DaoUsuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String acao = request.getParameter("acao");
        String user = request.getParameter("user");

        try {
            if (acao != null && acao.equalsIgnoreCase("delete")) {
                daoUsuario.delete(user);
                request.setAttribute("msg2", "Cadastro Deletado!.");
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro


            } else if (acao != null && acao.equalsIgnoreCase("editar")) {

                UsuarioBean usuarioBean = daoUsuario.consultar(user); // consulta usuario para deletar, passando o objeto
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("user", usuarioBean);
                view.forward(request, response); // -->

            } else if (acao != null && acao.equalsIgnoreCase("listarTodos")) {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela

            } else if (acao != null && acao.equalsIgnoreCase("download")) { //converte a base64 a img do banco bytes.p/ baixar o arquivo para o pc"fazer o download"
                UsuarioBean usuarioBean = daoUsuario.consultar(user);
                if (usuarioBean != null) {

                    String contentType = "";
                    byte[] fileBytes = null;
                    String tipo = request.getParameter("tipo");

                    if (tipo.equalsIgnoreCase("imagem")) {
                        contentType = usuarioBean.getContentType();
                        fileBytes = new Base64().decodeBase64(usuarioBean.getFotoBase64());
                    } else if (tipo.equalsIgnoreCase("curriculo")) {
                        contentType = usuarioBean.getContentTypeDoc();
                        fileBytes = new Base64().decodeBase64(usuarioBean.getDocBase64());
                    }

                    response.setHeader("Content-Disposition", "attachment;filename=arquivo."
                            + contentType.split("\\/")[1]);

                    /*coloca os bytes em um objeto de entrada para processar*/

                    InputStream is = new ByteArrayInputStream(fileBytes);

                    //inicia a resposta para o navegar
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    OutputStream os = response.getOutputStream();
                    while ((read = is.read(bytes)) != -1) {
                        os.write(bytes, 0, read);
                    }
                    os.flush();
                    os.close();
                }
            }else {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
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
            // String fotobase64 = request.getParameter("fotobase64");
            //  String contenttype = request.getParameter("contenttype");


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
                /*Inicio upload  File de imagens e pdf */
                if (ServletFileUpload.isMultipartContent(request)) {
                    Part imagemFoto = request.getPart("foto");

                    if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {

                        String fotoBase64 = new Base64()
                                .encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));
                        usuarioBean.setFotoBase64(fotoBase64);
                        usuarioBean.setContentType(imagemFoto.getContentType());

                    } else {//pegar foto temporaria
                        usuarioBean.setFotoBase64(request.getParameter("fotoTemp64"));
                        usuarioBean.setContentType(request.getParameter("contentTypeTemp"));
                    }
                    //Processo PDF*/
                    Part documentoPdf = request.getPart("curriculo");
                    if (documentoPdf != null && documentoPdf.getInputStream().available() > 0) {
                        String docBase64 = new Base64()
                                .encodeBase64String(converteStremParabyte(documentoPdf.getInputStream()));
                        usuarioBean.setDocBase64(docBase64);
                        usuarioBean.setContentTypeDoc(documentoPdf.getContentType());

                    } else {//pegar doc temporario
                        usuarioBean.setDocBase64(request.getParameter("docTemp64"));
                        usuarioBean.setContentTypeDoc(request.getParameter("contentTypeDocTemp"));
                    }
                }
                /* Fim upload*/

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

                } else if (id == null || id.isEmpty()
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
                request.setAttribute("msg", "Salvo com Sucesso");
                view.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*Converte a entrada de fluxo de dados da imagem para byte[]*/
    private byte[] converteStremParabyte(InputStream imagem) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = imagem.read();
        while (reads != -1) {
            baos.write(reads);
            reads = imagem.read();
        }

        return baos.toByteArray();

    }
}

