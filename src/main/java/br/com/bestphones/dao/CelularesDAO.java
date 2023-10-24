package br.com.bestphones.dao;

import br.com.bestphones.model.Celulares;
import br.com.bestphones.utils.ConexaoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CelularesDAO {

  public List<Celulares> getcelulares() {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Celulares> celulares = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM CELULARES;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Celulares c = new Celulares();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        celulares.add(c);
      }
    } catch (SQLException ex) {
      Logger.getLogger(CelularesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return celulares;
  }

  public Celulares getCelularesPorId(int celulares_id) {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    Celulares celulares = new Celulares();

    try {
      stmt = con.prepareStatement("SELECT * FROM CELULARES where id = " + celulares_id);
      rs = stmt.executeQuery();
      rs.next();
      celulares.setId(rs.getInt("id"));
      celulares.setNome(rs.getString("nome"));

    } catch (SQLException ex) {
      Logger.getLogger(CelularesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return celulares;
  }

  public List<Celulares> getcelularesOrdenado() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Celulares> listacelulares = new ArrayList<>();
      
    try {
      stmt = con.prepareStatement("select celulares.* from celulares inner join produtos on (celulares.id = produtos.celulares_id) where produtos.registro_deletado is null and produtos.disponivel_venda > 0 order by produtos.id;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Celulares c = new Celulares();
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
