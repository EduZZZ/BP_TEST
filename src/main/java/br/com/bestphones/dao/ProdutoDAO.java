package br.com.bestphones.dao;

import br.com.bestphones.model.Produto;
import br.com.bestphones.utils.ConexaoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Repository
public class ProdutoDAO {

  private static final Logger LOGGER = Logger.getLogger(ProdutoDAO.class.getName());

  public List<Produto> getProdutos() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Produto> produtos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM produtos WHERE registro_deletado =0;");
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
        p.setCelulares_id(rs.getInt("celulares_id"));
        produtos.add(p);
      }
      LOGGER.info("Produtos recuperados: " + produtos.size());
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao recuperar produtos.", ex);
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
      LOGGER.info("Produto com ID " + id + " removido.");
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao remover produto com ID " + id, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public void salvarProduto(Produto p) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into produtos (nome,descricao_curta,descricao_detalhada,preco,qtde,disponivel_venda,celulares_id, registro_deletado) values ( ?, ?, ?, ?, ?, ?, ?, ?);");
      stmt.setString(1, p.getNome());
      stmt.setString(2, p.getDescricao_curta());
      stmt.setString(3, p.getDescricao_detalhada());
      stmt.setFloat(4, p.getPreco());
      stmt.setInt(5, p.getQtde());
      stmt.setBoolean(6, p.isDisponivel_venda());
      stmt.setInt(7, p.getCelulares_id());
      stmt.setInt(8, 0);
      stmt.executeUpdate();
      LOGGER.info("Produto com nome " + p.getNome() + " inserido.");
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao salvar produto.", ex);
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
      stmt = con.prepareStatement("SELECT MAX(id) as id FROM produtos;");
      rs = stmt.executeQuery();

      if (rs.next()) {
        produto_id = rs.getInt("id");
      }
      LOGGER.info("Último produto com ID: " + produto_id);
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao recuperar o último produto.", ex);
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
      stmt = con.prepareStatement("SELECT * FROM produtos WHERE id = ?");
      stmt.setInt(1, id);
      rs = stmt.executeQuery();

      if (rs.next()) {
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao_curta(rs.getString("descricao_curta"));
        p.setDescricao_detalhada(rs.getString("descricao_detalhada"));
        p.setPreco(rs.getFloat("preco"));
        p.setQtde(rs.getInt("qtde"));
        p.setDisponivel_venda(rs.getBoolean("disponivel_venda"));
        p.setCelulares_id(rs.getInt("celulares_id"));
      }
      LOGGER.info("Produto com ID " + id + " recuperado.");
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao recuperar produto com ID " + id, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return p;
  }

  public void alterarProduto(Produto p) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update produtos set nome = ?, descricao_curta = ?, descricao_detalhada = ?, preco = ?, qtde = ?, disponivel_venda = ?, celulares_id = ? where id = ?;");
      stmt.setString(1, p.getNome());
      stmt.setString(2, p.getDescricao_curta());
      stmt.setString(3, p.getDescricao_detalhada());
      stmt.setFloat(4, p.getPreco());
      stmt.setInt(5, p.getQtde());
      stmt.setBoolean(6, p.isDisponivel_venda());
      stmt.setInt(7, p.getCelulares_id());
      stmt.setInt(8, p.getId());
      stmt.executeUpdate();
      LOGGER.info("Produto com ID " + p.getId() + " atualizado.");
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao atualizar produto com ID " + p.getId(), ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public List<Produto> getProdutosDeletados() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Produto> produtos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("select * from produtos where registro_deletado = 1;");
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
        p.setCelulares_id(rs.getInt("celulares_id"));
        produtos.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return produtos;
  }
  public void reativarProduto(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update produtos set registro_deletado = false where id = ?");
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

}
