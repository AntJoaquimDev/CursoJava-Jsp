package dao;


import bens.TelefonesBean;
import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoTelefones {
    private Connection connection;

    public DaoTelefones() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(TelefonesBean telefonesBean) {

        try {
            String sql = "insert into telefone (numero,tipo,usuario) values(?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, telefonesBean.getNumero());
            insert.setString(2, telefonesBean.getTipo());
            insert.setLong(3, (telefonesBean.getUsuario()));
            insert.execute();
            System.out.println("produto salvo");
            connection.commit();
        } catch (SQLException e1) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            e1.printStackTrace();
        }
    }

    public List<TelefonesBean> listar(long user) throws SQLException {
        List<TelefonesBean> listar = new ArrayList<TelefonesBean>();

        String sql = "select *from telefone where usuario = "+ user;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {// enquanto tiver dados ele llistar

            TelefonesBean telefonesBean = new TelefonesBean();
            telefonesBean.setId(resultSet.getLong("id"));
            telefonesBean.setNumero(resultSet.getString("numero"));
            telefonesBean.setTipo(resultSet.getString("tipo"));
            telefonesBean.setUsuario(resultSet.getLong("usuario"));

            listar.add(telefonesBean);

        }
        return listar;
    }

    public void delete(String id) {
        try {
            String sql = "delete from telefone where id = '" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}

