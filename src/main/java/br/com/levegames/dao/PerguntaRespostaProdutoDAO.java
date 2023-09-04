package br.com.levegames.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.levegames.model.PerguntaRespostaProduto;
import br.com.levegames.utils.ConexaoDB;

public class PerguntaRespostaProdutoDAO {

  public void salvarPerguntasRespostasProduto(int produto_id, String[] perguntas, String[] respostas) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      for (int i=0 ; i<perguntas.length ; i++) {
       stmt = con.prepareStatement("insert into perguntas_respostas_produto(produto_id, pergunta, resposta) values(" + produto_id + ", '" + perguntas[i] + "', '" + respostas[i] + "');");
       stmt.executeUpdate();
      }

    } catch (SQLException ex) {
      Logger.getLogger(PerguntaRespostaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public List<PerguntaRespostaProduto> getPerguntasRespostasProduto(int produto_id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<PerguntaRespostaProduto> listaPerguntasRespostas = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM perguntas_respostas_produto where produto_id = " + produto_id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        PerguntaRespostaProduto pr = new PerguntaRespostaProduto();
        pr.setId(rs.getInt("id"));
        pr.setProduto_id(produto_id);
        pr.setPergunta(rs.getString("pergunta"));
        pr.setResposta(rs.getString("resposta"));
        listaPerguntasRespostas.add(pr);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listaPerguntasRespostas;
  }

  public void deletarPerguntasRespostasProduto(int produto_id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = con.prepareStatement("DELETE FROM perguntas_respostas_produto where produto_id = ?;");
      stmt.setInt(1, produto_id);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
  }

}
