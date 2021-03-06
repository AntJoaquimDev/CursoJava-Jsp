package servlet;

import bens.UsuarioBean;
import dao.DaoLogin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet-Login", value = "/Servlet-Login")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1l;
    DaoLogin daoLogin = new DaoLogin();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
       try {
           UsuarioBean usuarioBean = new UsuarioBean();
           String login =(request.getParameter("login"));
           String senha =(request.getParameter("senha"));

           if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

               if (daoLogin.validarLogin(login, senha)) { // acesso liberado
                   RequestDispatcher dispatcher = request.getRequestDispatcher("acessoLiberado.jsp");
                   dispatcher.forward(request, response);
               } else {//acesso negado
                   RequestDispatcher dispatcher = request.getRequestDispatcher("acessoNegado.jsp");
                   dispatcher.forward(request, response);
               }
           }else{
                   RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                   dispatcher.forward(request, response);
               }

       }catch (Exception e){

           e.printStackTrace();
       }
    }
}
