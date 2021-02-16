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

        String sql = "insert into usuario(nome,login,senha,telefone,cep,rua,bairro,cidade,uf,fotobase64,contenttype,docBase64,contenttypedoc,fotoBase64Miniatura) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement insert = null;
        try {
            insert = connection.prepareStatement(sql);
            insert.setString(1, usuario.getNome());
            insert.setString(2, usuario.getLogin());
            insert.setString(3, usuario.getSenha());
            insert.setString(4, usuario.getTelefone());
            insert.setString(5, usuario.getCep());
            insert.setString(6, usuario.getRua());
            insert.setString(7, usuario.getBairro());
            insert.setString(8, usuario.getCidade());
            insert.setString(9, usuario.getUf());
            insert.setString(10, usuario.getFotoBase64());
            insert.setString(11, usuario.getContentType());
            insert.setString(12, usuario.getDocBase64());
            insert.setString(13, usuario.getContentTypeDoc());
            insert.setString(14, usuario.getFotoBase64Miniatura());

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

        String sql = "select * from usuario where login <> 'admin'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setId(resultSet.getLong("id"));
            usuarioBean.setNome(resultSet.getString("nome"));
            usuarioBean.setLogin(resultSet.getString("login"));
            usuarioBean.setSenha(resultSet.getString("senha"));
            usuarioBean.setTelefone(resultSet.getString("telefone"));
            usuarioBean.setCep(resultSet.getString("cep"));
            usuarioBean.setRua(resultSet.getString("rua"));
            usuarioBean.setBairro(resultSet.getString("bairro"));
            usuarioBean.setCidade(resultSet.getString("cidade"));
            usuarioBean.setUf(resultSet.getString("uf"));
            // usuarioBean.setFotoBase64(resultSet.getString("fotobase64"));
            usuarioBean.setContentType(resultSet.getString("contenttype"));
            usuarioBean.setDocBase64(resultSet.getString("docbase64"));
            usuarioBean.setContentTypeDoc(resultSet.getString("contenttypedoc"));
            usuarioBean.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));
            listar.add(usuarioBean);
        }
        return listar;
    }

    public void delete(String id) {

        try {
            String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";

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

    public UsuarioBean consultar(String id) throws SQLException {
        String sql = "select * from usuario where id = '" + id + "'and login <> 'admin'";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setId(resultSet.getLong("id"));
            usuarioBean.setNome(resultSet.getString("nome"));
            usuarioBean.setLogin(resultSet.getString("login"));
            usuarioBean.setSenha(resultSet.getString("senha"));
            usuarioBean.setTelefone(resultSet.getString("telefone"));
            usuarioBean.setCep(resultSet.getString("cep"));
            usuarioBean.setRua(resultSet.getString("rua"));
            usuarioBean.setBairro(resultSet.getString("bairro"));
            usuarioBean.setCidade(resultSet.getString("cidade"));
            usuarioBean.setUf(resultSet.getString("uf"));
            usuarioBean.setFotoBase64(resultSet.getString("fotobase64"));
            usuarioBean.setContentType(resultSet.getString("contenttype"));
            usuarioBean.setDocBase64(resultSet.getString("docbase64"));
            usuarioBean.setContentTypeDoc(resultSet.getString("contenttypedoc"));
            usuarioBean.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));

            return usuarioBean;
        }

        return null;
    }

    public void atualizar(UsuarioBean usuarioBean) {

        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" update usuario set nome = ?,login = ?, senha = ? ,telefone= ? ");
            sql.append(", cep = ?, rua = ?,bairro = ?,cidade = ?,uf = ? ");

            if (usuarioBean.isAtualizarImagem()) {
                sql.append(" ,fotobase64 = ?,contenttype = ? ");
            }
            if (usuarioBean.isAtualizarDocPdf()) {
                sql.append(" ,docBase64 = ?,contenttypedoc =? ");
            }
            if (usuarioBean.isAtualizarImagem()) {
                sql.append(" ,fotoBase64Miniatura = ? ");
            }

            sql.append("  where id = " + usuarioBean.getId());

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, usuarioBean.getNome());
            statement.setString(2, usuarioBean.getLogin());
            statement.setString(3, usuarioBean.getSenha());
            statement.setString(4, usuarioBean.getTelefone());
            statement.setString(5, usuarioBean.getCep());
            statement.setString(6, usuarioBean.getRua());
            statement.setString(7, usuarioBean.getBairro());
            statement.setString(8, usuarioBean.getCidade());
            statement.setString(9, usuarioBean.getUf());

            if (usuarioBean.isAtualizarImagem()) {
                statement.setString(10, usuarioBean.getFotoBase64());
                statement.setString(11, usuarioBean.getContentType());
            }
            if (usuarioBean.isAtualizarDocPdf()) {
                if (usuarioBean.isAtualizarDocPdf() && !usuarioBean.isAtualizarImagem()) {
                    statement.setString(10, usuarioBean.getDocBase64());
                    statement.setString(11, usuarioBean.getContentTypeDoc());
                } else {
                    statement.setString(12, usuarioBean.getDocBase64());
                    statement.setString(13, usuarioBean.getContentTypeDoc());
                }
            } else {
                if (usuarioBean.isAtualizarImagem()) {
                    statement.setString(12, usuarioBean.getFotoBase64Miniatura());
                }
            }
            if ( usuarioBean.isAtualizarImagem() && usuarioBean.isAtualizarDocPdf() ) {
                statement.setString(14, usuarioBean.getFotoBase64Miniatura());
            }
            statement.executeUpdate();
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

    public boolean validarLogin(String login) throws SQLException {
        String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("qtd") <= 0;
        }
        return false;
    }

    public boolean validarSenha(String senha) throws SQLException {
        String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("qtd") <= 0;
        }
        return false;
    }


   /*
    public void ValidarInserir(String nome,String login,String senha){
        if(nome == null || nome.isEmpty() || login == null || login.isEmpty() || senha == null || senha.isEmpty()){
String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("qtd") <= 0;
        }
        return false;
    }
        }

*/

//parfa validar atualizar com mesmo loguin e mesmo id

    public boolean validarLogin(String login, long id) throws SQLException {
        String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("qtd") <= 0;
        }
        return false;
    }


}


