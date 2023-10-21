package br.com.bestphones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.bestphones.model.Venda;
import br.com.bestphones.utils.ConexaoDB;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class VendaDAO {

  private static final Logger LOGGER = Logger.getLogger(VendaDAO.class.getName());

  @Transactional
  public void salvarVenda(Venda v) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into vendas (cliente_id,endereco_id,meio_pagamento_id,status_id,total, obs) values ( ?, ?, ?, ?, ?, ?);");

      stmt.setInt(1, v.getCliente_id());
      stmt.setInt(2, v.getEndereco_id());
      stmt.setInt(3, v.getMeio_pagamento_id());
      stmt.setInt(4, 1);
      stmt.setDouble(5, v.getTotal());
      stmt.setString(6, v.getObs());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "Erro ao salvar a venda.", ex);
      throw new RuntimeException(ex); // Convertendo em RuntimeException para que a transação possa ser revertida.
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
      LOGGER.log(Level.SEVERE, "Erro ao obter a última venda.", ex);
      throw new RuntimeException(ex); // Convertendo em RuntimeException para que possa ser tratado externamente se necessário.
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return id;
  }
}
