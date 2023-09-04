package br.com.levegames.dao;

import br.com.levegames.model.Usuario;
import br.com.levegames.utils.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

  public List<Usuario> getUsuarios() {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Usuario> usuarios = new ArrayList<>();

    try {
      stmt = con.prepareStatement("select * from usuarios where registro_deletado = false;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setCargo(rs.getString("cargo"));
        u.setRegistro_deletado(rs.getBoolean("registro_deletado"));
        usuarios.add(u);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return usuarios;
  }

  public void removeUsuario(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    Usuario user = getUsuario(id);

    if (user.getCargo() != "Administrador") {
      try {
        stmt = con.prepareStatement("update usuarios set registro_deletado = true where id = ?");

        stmt.setInt(1, id);

        stmt.executeUpdate();
      } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        ConexaoDB.fecharConexao(con, stmt);
      }
    } else {
      //tratar excepiton
    }
  }

  public void salvarUsuario(Usuario u) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into usuarios (nome,email,senha,cargo,registro_deletado) values ( ?, ?, ?, ?, false);");

      stmt.setString(1, u.getNome());
      stmt.setString(2, u.getEmail());
      stmt.setString(3, u.getSenha());
      stmt.setString(4, u.getCargo());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public Usuario getUsuario(String email, String senha) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Usuario u = null;

    try {
      stmt = con.prepareStatement("select * from usuarios where email = '" + email + "' and senha = '" + senha + "';");
      rs = stmt.executeQuery();

      rs.next(); //Vá para a última linha do resultSet:
      int rows = rs.getRow(); //Pegue o número da linha

      if (rows == 1) {
        u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setCargo(rs.getString("cargo"));
        u.setRegistro_deletado(rs.getBoolean("registro_deletado"));

      }

    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return u;
  }
  
  public boolean getIsUsuarioExiste(String email) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    boolean userExiste = false;

    try {
      stmt = con.prepareStatement("select * from usuarios where email = '" + email + "';");
      rs = stmt.executeQuery();

      rs.next(); //Vá para a última linha do resultSet:
      int rows = rs.getRow(); //Pegue o número da linha

      if (rows == 1) {
        userExiste = true;

      }

    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return userExiste;
  }
  

  public void alterarUsuario(Usuario u) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update usuarios set nome = ?, senha = ?, cargo = ? where id = ?;");

      stmt.setString(1, u.getNome());
      stmt.setString(2, u.getSenha());
      stmt.setString(3, u.getCargo());
      stmt.setInt(4, u.getId());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public Usuario getUsuario(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Usuario u = new Usuario();

    try {
      stmt = con.prepareStatement("select * from usuarios where id = " + id);
      rs = stmt.executeQuery();

      rs.next();

      u.setId(rs.getInt("id"));
      u.setNome(rs.getString("nome"));
      u.setEmail(rs.getString("email"));
      u.setSenha(rs.getString("senha"));
      u.setCargo(rs.getString("cargo"));
      u.setRegistro_deletado(rs.getBoolean("registro_deletado"));

    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return u;
  }

  public List<Usuario> getUsuariosDiferenteDoLogado(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Usuario> usuarios = new ArrayList<>();

    try {
      stmt = con.prepareStatement("select * from usuarios where registro_deletado = false and id <> " + id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setCargo(rs.getString("cargo"));
        u.setRegistro_deletado(rs.getBoolean("registro_deletado"));
        usuarios.add(u);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return usuarios;
  }

}
