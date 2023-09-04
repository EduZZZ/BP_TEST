package br.com.levegames.dao;

import br.com.levegames.model.Console;
import br.com.levegames.utils.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleDAO {

  public List<Console> getConsoles() {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Console> consoles = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM CONSOLES;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Console c = new Console();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        consoles.add(c);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ConsoleDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return consoles;
  }

  public Console getConsolePorId(int console_id) {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    Console console = new Console();

    try {
      stmt = con.prepareStatement("SELECT * FROM CONSOLES where id = " + console_id);
      rs = stmt.executeQuery();
      rs.next();
      console.setId(rs.getInt("id"));
      console.setNome(rs.getString("nome"));

    } catch (SQLException ex) {
      Logger.getLogger(ConsoleDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return console;
  }

  public List<Console> getConsolesOrdenado() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Console> listaConsoles = new ArrayList<>();
      
    try {
      stmt = con.prepareStatement("select consoles.* from consoles inner join produtos on (consoles.id = produtos.console_id) where produtos.registro_deletado is null and produtos.disponivel_venda > 0 order by produtos.id;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Console c = new Console();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        listaConsoles.add(c);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listaConsoles;
  }

}
