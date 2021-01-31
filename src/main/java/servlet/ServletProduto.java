package servlet;

import bens.ProdutoBean;
import dao.DaoProduto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "/salvarProduto", value = "/salvarProduto")
public class ServletProduto extends HttpServlet {
    private static final long serialVersionUID = 1l;

    private DaoProduto daoProduto = new DaoProduto();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            String acao = request.getParameter("acao");
            String produto = request.getParameter("produto");

            if (acao.equalsIgnoreCase("delete")) {
                daoProduto.delete(produto);
                request.setAttribute("msg2", "Cadastro Deletado!.");
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro

            } else if (acao.equalsIgnoreCase("editar")) {

                ProdutoBean produtoBean = daoProduto.consultar(produto); // consulta usuario para deletar, passando o objeto

                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produto", produtoBean);
                view.forward(request, response); // -->

            } else if (acao.equalsIgnoreCase("listarTodos")) {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String acao = request.getParameter("acao");

        if (acao != null && acao.equalsIgnoreCase("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto);
                view.forward(request, response); //para redirecionar na tela de cadastro

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            /*
            if(id == null || nome.isEmpty() || qtd.isEmpty()|| valor.isEmpty()) {
                    msg = "[ERRO!]Login e Senha devem Ser informados";
                    request.setAttribute("msg", msg);
                }else
             */
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String qtd = request.getParameter("qtd");
            String valor = request.getParameter("valor");


            ProdutoBean produtoBean = new ProdutoBean();

            produtoBean.setId(!id.isEmpty() ? Long.parseLong(id) : null);
            produtoBean.setNome(nome);
            produtoBean.setQtd(Double.parseDouble(qtd));
            produtoBean.setValor(Double.parseDouble(valor));
            try {
                String msg = null;
                boolean podeInserir = true;

                if (id == null || id.isEmpty() && !daoProduto.validarNomeProduto(nome)) {
                    // request.setAttribute("msg","[ERRO!] Login ou SEnha. já está Cadastrado no Sistema");
                    msg = "A Prouto já existe no cadastro";

                }else
                if (msg != null) {
                    request.setAttribute("msg", msg);
                }else
                if (id == null || id.isEmpty() && daoProduto.validarNomeProduto(nome)
                        && podeInserir) {
                    request.setAttribute("msg2", "Produto Salvo com Sucesso!.");
                    daoProduto.salvar(produtoBean);

                } else if (id != null && !id.isEmpty() && podeInserir) {
                    daoProduto.atualizar(produtoBean);
                }
                if (!podeInserir) {
                    request.setAttribute("produto", produtoBean);
                }

                //para mandar resultadao da pesquisa para mesma tela de cadastro
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}