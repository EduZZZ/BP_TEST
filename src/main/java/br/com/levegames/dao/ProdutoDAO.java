package br.com.levegames.dao;

import br.com.levegames.model.Produto;
import br.com.levegames.utils.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

  public List<Produto> getProdutos() {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Produto> produtos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM PRODUTOS where registro_deletado is null;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao_curta(rs.getString("descricao_curta"));
        p.setDescricao_detalhada(rs.getString("descricao_detalhada"));
        p.setPreco(rs.getFloat("preco"));
        p.setQtde(rs.getInt("qtde"));
        p.setDisponivel_venda(rs.getBoolean("disponivel_venda"));
        p.setConsole_id(rs.getInt("console_id"));
        produtos.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return produtos;
  }

  public void removeProduto(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update produtos set registro_deletado = true where id = ?");

      stmt.setInt(1, id);

      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public void salvarProduto(Produto p) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into produtos (nome,descricao_curta,descricao_detalhada,preco,qtde,disponivel_venda,console_id, registro_deletado) values ( ?, ?, ?, ?, ?, ?, ?, false);");

      stmt.setString(1, p.getNome());
      stmt.setString(2, p.getDescricao_curta());
      stmt.setString(3, p.getDescricao_detalhada());
      stmt.setFloat(4, p.getPreco());
      stmt.setInt(5, p.getQtde());
      stmt.setBoolean(6, p.isDisponivel_venda());
      stmt.setInt(7, p.getConsole_id());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public int getUltimoProduto() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int produto_id = 0;

    try {
      stmt = con.prepareStatement("SELECT MAX(id) as id FROM PRODUTOS;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        produto_id = rs.getInt("id");

      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return produto_id;
  }

  public Produto getProdutos(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Produto p = new Produto();

    try {
      stmt = con.prepareStatement("SELECT * FROM PRODUTOS WHERE id = " + id);
      rs = stmt.executeQuery();

      rs.next();

      p.setId(rs.getInt("id"));
      p.setNome(rs.getString("nome"));
      p.setDescricao_curta(rs.getString("descricao_curta"));
      p.setDescricao_detalhada(rs.getString("descricao_detalhada"));
      p.setPreco(rs.getFloat("preco"));
      p.setQtde(rs.getInt("qtde"));
      p.setDisponivel_venda(rs.getBoolean("disponivel_venda"));
      p.setConsole_id(rs.getInt("console_id"));

    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return p;
  }

  public void alterarProduto(Produto p) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update produtos set nome = ?, descricao_curta = ?, descricao_detalhada = ?, preco = ?, qtde = ?, disponivel_venda = ?, console_id = ? where id = ?;");

      stmt.setString(1, p.getNome());
      stmt.setString(2, p.getDescricao_curta());
      stmt.setString(3, p.getDescricao_detalhada());
      stmt.setFloat(4, p.getPreco());
      stmt.setInt(5, p.getQtde());
      stmt.setBoolean(6, p.isDisponivel_venda());
      stmt.setInt(7, p.getConsole_id());
      stmt.setInt(8, p.getId());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

}
