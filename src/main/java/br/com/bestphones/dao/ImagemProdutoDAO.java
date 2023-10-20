package br.com.bestphones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.bestphones.model.ImagemProduto;
import br.com.bestphones.utils.ConexaoDB;

public class ImagemProdutoDAO {

  public void salvarImagensProduto(int produto_id, String[] imagens) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      for (int i = 0; i < imagens.length; i++) {
        stmt = con.prepareStatement("insert into imagens_produto(produto_id, url_imagem) values(?, ?);");
        stmt.setInt(1, produto_id);
        stmt.setString(2, imagens[i]);
        stmt.executeUpdate();
      }

    } catch (SQLException ex) {
      Logger.getLogger(ImagemProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public List<ImagemProduto> getImagensProduto(int produto_id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<ImagemProduto> listaImagens = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM imagens_produto where produto_id = ?");
      stmt.setInt(1, produto_id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        ImagemProduto i = new ImagemProduto();
        i.setId(rs.getInt("id"));
        i.setProduto_id(produto_id);
        i.setUrl_imagem(rs.getString("url_imagem"));
        listaImagens.add(i);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listaImagens;
  }

  public void alterarImagensProduto(int produto_id) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public void deletarImagensProduto(int produto_id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = con.prepareStatement("DELETE FROM imagens_produto where produto_id = ?;");
      stmt.setInt(1, produto_id);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
  }

  public List<ImagemProduto> getFirstImagensProduto() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<ImagemProduto> listaImagens = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT imagens_produto.produto_id, MAX(imagens_produto.id) AS max_id, MAX(imagens_produto.url_imagem) AS max_url_imagem FROM imagens_produto INNER JOIN produtos ON (imagens_produto.produto_id = produtos.id) WHERE produtos.registro_deletado IS NULL AND produtos.disponivel_venda > 0 GROUP BY imagens_produto.produto_id;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        ImagemProduto i = new ImagemProduto();
        i.setId(rs.getInt("max_id"));
        i.setProduto_id(rs.getInt("produto_id"));
        i.setUrl_imagem(rs.getString("max_url_imagem"));
        listaImagens.add(i);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listaImagens;
  }
}
