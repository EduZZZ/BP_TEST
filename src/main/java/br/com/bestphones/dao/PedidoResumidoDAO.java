package br.com.bestphones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.bestphones.model.PedidoResumido;
import br.com.bestphones.utils.ConexaoDB;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoResumidoDAO {

<<<<<<< HEAD
  public List<PedidoResumido> getPedidos(int cliente_id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<PedidoResumido> pedidos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT vendas.id, vendas.total, status.status FROM vendas, status where cliente_id = ? and status.id = vendas.status_id");
      stmt.setInt(1, cliente_id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        PedidoResumido p = new PedidoResumido();
        p.setId(rs.getInt("vendas.id"));
        p.setTotal(rs.getDouble("vendas.total"));
        p.setStatus(rs.getString("status.status"));
        pedidos.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PedidoResumidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return pedidos;
  }

  public List<PedidoResumido> todosPedidos() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<PedidoResumido> pedidos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT vendas.id, vendas.total, status.status FROM vendas JOIN status ON status.id = vendas.status_id");
      rs = stmt.executeQuery();

      while (rs.next()) {
        PedidoResumido p = new PedidoResumido();
        p.setId(rs.getInt("vendas.id"));
        p.setTotal(rs.getDouble("vendas.total"));
        p.setStatus(rs.getString("status.status"));
        pedidos.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PedidoResumidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return pedidos;
  }

  public PedidoResumido getPedidoPorId(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = con.prepareStatement("SELECT vendas.id, vendas.total, status.status FROM vendas JOIN status ON status.id = vendas.status_id WHERE vendas.id = ?");
      stmt.setInt(1, id);
      rs = stmt.executeQuery();

      if (rs.next()) {
        PedidoResumido pedido = new PedidoResumido();
        pedido.setId(rs.getInt("vendas.id"));
        pedido.setTotal(rs.getDouble("vendas.total"));
        pedido.setStatus(rs.getString("status.status"));
        return pedido;
      }
    } catch (SQLException ex) {
      Logger.getLogger(PedidoResumidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return null;
  }

  public void atualizarStatusPedido(int pedidoId, int statusId) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      String sql = "UPDATE vendas SET status_id = ? WHERE id = ?";
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, statusId);
      stmt.setInt(2, pedidoId);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(PedidoResumidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }
  public int proximoStatus(int statusAtualId) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int proximoStatusId = -1;

    try {
      String sql = "SELECT id FROM status WHERE id > ? ORDER BY id ASC LIMIT 1";
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, statusAtualId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        proximoStatusId = rs.getInt("id");
      }
    } catch (SQLException ex) {
      Logger.getLogger(PedidoResumidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return proximoStatusId;
  }

  public int getStatusAtual(int pedidoId) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int statusAtualId = -1;

    try {
      String sql = "SELECT status_id FROM vendas WHERE id = ?";
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, pedidoId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        statusAtualId = rs.getInt("status_id");
      }
    } catch (SQLException ex) {
      Logger.getLogger(PedidoResumidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return statusAtualId;
  }
=======
    public List<PedidoResumido> getPedidos(int cliente_id) {

        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<PedidoResumido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT vendas.id, vendas.total, status.status FROM vendas, status where cliente_id = " + cliente_id + " and status.id = vendas.status_id");
            rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoResumido p = new PedidoResumido();
                p.setId(rs.getInt("vendas.id"));
                p.setTotal(rs.getDouble("vendas.total"));
                p.setStatus(rs.getString("status.status"));
                pedidos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt, rs);
        }
        return pedidos;
    }
>>>>>>> f9f132647128c5cb96614f255e5dfd5f2ba72270
}



