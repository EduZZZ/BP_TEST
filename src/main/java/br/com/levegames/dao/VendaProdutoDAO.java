/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.levegames.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.levegames.model.ProdutoCarrinho;
import br.com.levegames.utils.ConexaoDB;

/**
 *
 * @author victoria.sousa
 */
public class VendaProdutoDAO {

  public void salvarVendaProdutos(int venda_id, List<ProdutoCarrinho> carrinho) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      for (ProdutoCarrinho p : carrinho) {
        stmt = con.prepareStatement("insert into vendas_produtos (produto_id,venda_id,valor,qtd) values (?, ?, ?, ?);");
        stmt.setInt(1, p.getId());
        stmt.setInt(2, venda_id);
        stmt.setDouble(3, p.getPreco());
        stmt.setInt(4, p.getQtde());
        stmt.executeUpdate();
      }
    } catch (SQLException ex) {
      Logger.getLogger(VendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public int getUltimaVenda() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int id = 0;

    try {
      stmt = con.prepareStatement("SELECT MAX(id) as id FROM vendas;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        id = rs.getInt("id");

      }
    } catch (SQLException ex) {
      Logger.getLogger(VendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return id;
  }

}
