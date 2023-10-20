package br.com.bestphones.dao;

import br.com.bestphones.model.Console;
import br.com.bestphones.utils.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleDAO {

  public List<Console> getcelulares() {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Console> celulares = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM CELULARES;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Console c = new Console();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        celulares.add(c);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ConsoleDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return celulares;
  }

  public Console getConsolePorId(int celulares_id) {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    Console celulares = new Console();

    try {
      stmt = con.prepareStatement("SELECT * FROM CELULARES where id = " + celulares_id);
      rs = stmt.executeQuery();
      rs.next();
      celulares.setId(rs.getInt("id"));
      celulares.setNome(rs.getString("nome"));

    } catch (SQLException ex) {
      Logger.getLogger(ConsoleDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return celulares;
  }

  public List<Console> getcelularesOrdenado() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Console> listacelulares = new ArrayList<>();
      
    try {
      stmt = con.prepareStatement("select celulares.* from celulares inner join produtos on (celulares.id = produtos.celulares_id) where produtos.registro_deletado is null and produtos.disponivel_venda > 0 order by produtos.id;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Console c = new Console();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        listacelulares.add(c);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listacelulares;
  }

}
