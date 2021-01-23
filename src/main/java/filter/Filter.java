package filter;

import connection.SingleConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {
    private static Connection connection = SingleConnection.getConnection();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws  ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            connection.commit();
        } catch (Exception e) {

            try {
                e.printStackTrace();
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
