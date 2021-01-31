package dao;

import bens.ProdutoBean;
import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoProduto {
    private Connection connection;

    public DaoProduto() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(ProdutoBean produto) {

        try {
            String sql = "insert into produto (nome,qtd,valor) values(?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, produto.getNome());
            insert.setDouble(2, produto.getQtd());
            insert.setDouble(3, produto.getValor());
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

    public List<ProdutoBean> listar() throws SQLException {
        List<ProdutoBean> listar = new ArrayList<ProdutoBean>();

        String sql = "select *from produto";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {// enquanto tiver dados ele llistar

            ProdutoBean produtoBean = new ProdutoBean();
            produtoBean.setId(resultSet.getLong("id"));
            produtoBean.setNome(resultSet.getString("nome"));
            produtoBean.setQtd(resultSet.getDouble("qtd"));
            produtoBean.setValor(resultSet.getDouble("valor"));
            listar.add(produtoBean);

        }
        return listar;
    }
    public void delete(String id) {
        try {
            String sql = "delete from produto where id = '" + id + "'";
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

    public ProdutoBean consultar(String id) throws SQLException {
        String sql = "select * from produto where id = '" + id + "'";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            ProdutoBean produtoBean = new ProdutoBean();
            produtoBean.setId(resultSet.getLong("id"));
            produtoBean.setNome(resultSet.getString("nome"));
            produtoBean.setQtd(resultSet.getDouble("qtd"));
            produtoBean.setValor(resultSet.getDouble("valor"));

            return  produtoBean;
        }

        return null;
    }

    public void atualizar(ProdutoBean produtoBean) {

        try {
            String sql = "update produto set nome = ?, qtd = ? ,valor= ? where id = " + produtoBean.getId();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,produtoBean.getNome());
            preparedStatement.setDouble(2,produtoBean.getQtd());
            preparedStatement.setDouble(3,produtoBean.getValor());
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

    public boolean validarNomeProduto(String nome) throws SQLException {
        String sql = "select count(1) as qtds from produto where nome = '" + nome + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("qtds") <= 0;
        }
        return false;
    }

}

