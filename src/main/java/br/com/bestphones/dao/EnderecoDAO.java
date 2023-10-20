package br.com.bestphones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.bestphones.model.Endereco;

import br.com.bestphones.utils.ConexaoDB;

public class EnderecoDAO {

  public void salvarEnderecoCliente(int cliente_id, Endereco e) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      String sql = "INSERT INTO enderecos (cliente_id, cep, logradouro, numero, complemento, bairro, cidade, estado, is_faturamento) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, cliente_id);
      stmt.setString(2, e.getCep());
      stmt.setString(3, e.getLogradouro());
      stmt.setString(4, e.getNumero());
      stmt.setString(5, e.getComplemento());
      stmt.setString(6, e.getBairro());
      stmt.setString(7, e.getCidade());
      stmt.setString(8, e.getEstado());
      stmt.setBoolean(9, e.isIs_faturamento());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      // Lide com a exceção apropriadamente
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }



  public void deletarPerguntasRespostasProduto(int produto_id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = con.prepareStatement("DELETE FROM perguntas_respostas_produto where produto_id = ?;");
      stmt.setInt(1, produto_id);
      stmt.executeUpdate();
    } catch (SQLException ex) {

    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
  }

  public Endereco getEnderecoFaturamento(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco e = new Endereco();

    try {
      stmt = con.prepareStatement("select * from enderecos where cliente_id = " + id + " and is_faturamento = true;");
      rs = stmt.executeQuery();

      rs.next();

      e.setId(rs.getInt("id"));
      e.setCliente_id(id);
      e.setCep(rs.getString("cep"));
      e.setLogradouro(rs.getString("logradouro"));
      e.setNumero(rs.getString("numero"));
      e.setComplemento(rs.getString("complemento"));
      e.setBairro(rs.getString("bairro"));
      
      e.setCidade(rs.getString("cidade"));
      e.setEstado(rs.getString("estado"));
      e.setIs_faturamento(true);
    } catch (SQLException ex) {

    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return e;
  }

  public Endereco getEnderecoEntrega(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco e = new Endereco();

    try {
      stmt = con.prepareStatement("select * from enderecos where cliente_id = " + id + " and is_faturamento = false;");
      rs = stmt.executeQuery();

      rs.next();

      e.setId(rs.getInt("id"));
      e.setCliente_id(id);
      e.setCep(rs.getString("cep"));
      e.setLogradouro(rs.getString("logradouro"));
      e.setNumero(rs.getString("numero"));
      e.setComplemento(rs.getString("complemento"));
      e.setBairro(rs.getString("bairro"));
      e.setCidade(rs.getString("cidade"));
      e.setEstado(rs.getString("estado"));
      e.setIs_faturamento(false);
    } catch (SQLException ex) {

    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return e;
  }
  
  public Endereco getEnderecoEntregaPagamento(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco e = new Endereco();

    try {
      stmt = con.prepareStatement("select * from enderecos where id = " + id + " and is_faturamento is null;");
      rs = stmt.executeQuery();

      rs.next();

      e.setId(rs.getInt("id"));
      e.setCliente_id(id);
      e.setCep(rs.getString("cep"));
      e.setLogradouro(rs.getString("logradouro"));
      e.setNumero(rs.getString("numero"));
      e.setComplemento(rs.getString("complemento"));
      e.setBairro(rs.getString("bairro"));
      e.setCidade(rs.getString("cidade"));
      e.setEstado(rs.getString("estado"));
      e.setIs_faturamento(false);
    } catch (SQLException ex) {

    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return e;
  }

  public List<Endereco> getEnderecos(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Endereco> listaEnderecos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM enderecos WHERE cliente_id = ? AND is_faturamento IS NULL;");
      stmt.setInt(1, id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        Endereco e = new Endereco();
        e.setId(rs.getInt("id"));
        e.setCliente_id(id);
        e.setCep(rs.getString("cep"));
        e.setLogradouro(rs.getString("logradouro"));
        e.setNumero(rs.getString("numero"));
        e.setComplemento(rs.getString("complemento"));
        e.setBairro(rs.getString("bairro"));
        e.setCidade(rs.getString("cidade"));
        e.setEstado(rs.getString("estado"));
        e.setIs_faturamento(false);
        listaEnderecos.add(e);
      }
    } catch (SQLException ex) {
      // Lide com a exceção apropriadamente
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listaEnderecos;
  }

  public void removeEnderecos(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = con.prepareStatement("DELETE FROM enderecos where cliente_id = ?;");
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
  }

}
