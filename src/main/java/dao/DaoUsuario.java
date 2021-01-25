package dao;

import bens.UsuarioBean;
import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {
    private Connection connection;

    public DaoUsuario() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(UsuarioBean usuario) {

        String sql = "insert into usuario(login,senha) values(?,?)";

        PreparedStatement insert = null;
        try {
            insert = connection.prepareStatement(sql);
            insert.setString(1, usuario.getLogin());
            insert.setString(2, usuario.getSenha());
            insert.execute();
            connection.commit();

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

    }

    public List<UsuarioBean> listar() throws SQLException {
        List<UsuarioBean> listar = new ArrayList<UsuarioBean>();

        String sql = "select * from usuario";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setId(resultSet.getLong("id"));
            usuarioBean.setLogin(resultSet.getString("login"));
            usuarioBean.setSenha(resultSet.getString("senha"));
            listar.add(usuarioBean);
        }
        return listar;
    }

    public void delete(String login) {

        try {
            String sql = "delete from usuario where login = '" + login + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public UsuarioBean consultar(String login) throws SQLException {
        String sql = "select * from usuario where login = '"+ login + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                UsuarioBean usuarioBean = new UsuarioBean();
                usuarioBean.setId(resultSet.getLong("id"));
                usuarioBean.setLogin(resultSet.getString("login"));
                usuarioBean.setSenha(resultSet.getString("senha"));
                return usuarioBean;
            }

        return null;
    }

    public void atualizar(UsuarioBean usuarioBean) {

        try {
            String  sql ="update usuario set login = ?, senha = ? where id = "+ usuarioBean.getId();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,usuarioBean.getLogin());
            preparedStatement.setString(2, usuarioBean.getSenha());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
