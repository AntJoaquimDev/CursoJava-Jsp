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

            if (acao != null && acao.equalsIgnoreCase("delete")) {
                daoProduto.delete(produto);
                request.setAttribute("msg2", "Cadastro Deletado!.");
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro

            } else if (acao != null && acao.equalsIgnoreCase("editar")) {

                ProdutoBean produtoBean = daoProduto.consultar(produto); // consulta usuario para deletar, passando o objeto

                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produto", produtoBean);
                view.forward(request, response); // -->

            } else if (acao != null && acao.equalsIgnoreCase("listarTodos")) {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response); // --> para fazer o redirecionamento na tela ficar na mesma tela cadastro
            } else {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto.listar());
                view.forward(request, response); // --> p
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String acao = request.getParameter("acao");
        try {
            if (acao != null && acao.equalsIgnoreCase("reset")) {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", daoProduto);
                view.forward(request, response); //para redirecionar na tela de cadastro

            } else {
                String msg = null;
                boolean podeInserir = true;
                String id = request.getParameter("id");
                String nome = request.getParameter("nome");
                String qtd = request.getParameter("qtd");
                String valor = request.getParameter("valor");

                ProdutoBean produtoBean = new ProdutoBean();
                produtoBean.setId(!id.isEmpty() ? Long.parseLong(id) : null);
                produtoBean.setNome(nome);

                produtoBean.setQtd(!qtd.isEmpty() ? Double.parseDouble(qtd.replaceAll("\\,", ".")) : 0);

                if (valor != null && !valor.isEmpty()) {
                    String valorParse = valor.replaceAll("\\.", "");
                    valorParse = valorParse.replaceAll("\\,", ".");
                    produtoBean.setValor(Double.parseDouble(valorParse));
                }


                if (nome == null || nome.isEmpty()) {
                    msg = "[ERRO!] Nome deve ser Informado";
                    podeInserir = false;
                } else if (qtd == null || qtd.isEmpty()) {
                    msg = "[ERRO!] Quantidade deve ser Informado";
                    podeInserir = false;
                } else if (valor == null || valor.isEmpty()) {
                    msg = "[ERRO!] Valor deve ser Informado";
                    podeInserir = false;
                }

                if (id == null || id.isEmpty() && !daoProduto.validarNomeProduto(nome)) {
                    // request.setAttribute("msg","[ERRO!] Login ou SEnha. já está Cadastrado no Sistema");
                    msg = "A Prouto já existe no Cadastro";

                }
                if (msg != null) {
                    request.setAttribute("msg", msg);
                }
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

            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }


}