package br.com.bestphones.dao;

import org.springframework.stereotype.Repository;
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

@Repository
public class EnderecoDAO {

  public void salvarEnderecoCliente(int cliente_id, Endereco e) {
    String sql = "INSERT INTO enderecos (cliente_id, cep, logradouro, numero, complemento, bairro, cidade, estado, is_faturamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = ConexaoDB.obterConexao();
         PreparedStatement stmt = con.prepareStatement(sql)) {

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
      throw new RuntimeException("Erro ao salvar o endereço no banco de dados", ex);
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
    String sql = "SELECT * FROM enderecos WHERE cliente_id = ? AND is_faturamento = true;";
    try (Connection con = ConexaoDB.obterConexao();
         PreparedStatement stmt = con.prepareStatement(sql)) {

      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return extractEnderecoFromResultSet(rs);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public Endereco getEnderecoEntrega(int id) {
    String sql = "SELECT * FROM enderecos WHERE cliente_id = ? AND is_faturamento = false;";
    try (Connection con = ConexaoDB.obterConexao();
         PreparedStatement stmt = con.prepareStatement(sql)) {

      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return extractEnderecoFromResultSet(rs);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public Endereco getEnderecoEntregaPagamento(int id) {
    String sql = "SELECT * FROM enderecos WHERE id = ?;";
    try (Connection con = ConexaoDB.obterConexao();
         PreparedStatement stmt = con.prepareStatement(sql)) {

      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return extractEnderecoFromResultSet(rs);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }


  public List<Endereco> getEnderecos(int id) {
    List<Endereco> listaEnderecos = new ArrayList<>();
    String sql = "SELECT * FROM enderecos WHERE cliente_id = ? AND is_faturamento = false;";

    try (Connection con = ConexaoDB.obterConexao();
         PreparedStatement stmt = con.prepareStatement(sql)) {

      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          listaEnderecos.add(extractEnderecoFromResultSet(rs));
        }
      }
    } catch (SQLException ex) {
      throw new RuntimeException("Erro ao obter endereços do banco de dados", ex);
    }
    return listaEnderecos;
  }

  private Endereco extractEnderecoFromResultSet(ResultSet rs) throws SQLException {
    Endereco e = new Endereco();
    e.setId(rs.getInt("id"));
    e.setCliente_id(rs.getInt("cliente_id"));
    e.setCep(rs.getString("cep"));
    e.setLogradouro(rs.getString("logradouro"));
    e.setNumero(rs.getString("numero"));
    e.setComplemento(rs.getString("complemento"));
    e.setBairro(rs.getString("bairro"));
    e.setCidade(rs.getString("cidade"));
    e.setEstado(rs.getString("estado"));
    e.setIs_faturamento(rs.getBoolean("is_faturamento"));
    return e;
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
