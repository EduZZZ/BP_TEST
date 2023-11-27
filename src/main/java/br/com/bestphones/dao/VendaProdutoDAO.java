package br.com.bestphones.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import br.com.bestphones.model.ProdutoCarrinho;
import br.com.bestphones.utils.ConexaoDB;


@Repository
public class VendaProdutoDAO {

    public void salvarVendaProdutos(int venda_id, List<ProdutoCarrinho> carrinho) {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;

<<<<<<< HEAD
    try {
      for (ProdutoCarrinho p : carrinho) {
        stmt = con.prepareStatement("insert into vendas_produtos (produto_id,venda_id,valor,qtd) values (?, ?, ?, ?);");
        stmt.setInt(1, p.getId());
        stmt.setInt(2, venda_id);

        // Formata o preço para duas casas decimais
        BigDecimal preco = BigDecimal.valueOf(p.getPreco());
        preco = preco.setScale(2, RoundingMode.HALF_UP);

        stmt.setBigDecimal(3, preco);
        stmt.setInt(4, p.getQtde());
        stmt.executeUpdate();
      }
    } catch (SQLException ex) {
      Logger.getLogger(VendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
=======
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
>>>>>>> f9f132647128c5cb96614f255e5dfd5f2ba72270
    }

<<<<<<< HEAD

  public int getUltimaVenda() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int id = 0;
=======
    public int getUltimaVenda() {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
>>>>>>> f9f132647128c5cb96614f255e5dfd5f2ba72270

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
