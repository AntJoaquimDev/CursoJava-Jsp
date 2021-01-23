package dao;

import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoLogin {
    private Connection connection;

    public DaoLogin() {
        connection = SingleConnection.getConnection();
    }

    public boolean validarLogin(String login, String senha) throws Exception {
        String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return true; //tem user no banco
        } else {
            return false; // nao tem user no banco
        }

    }
}
