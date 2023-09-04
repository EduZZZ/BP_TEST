package br.com.levegames.model;

public class VendaProduto {
    
    private int id;
    private int produto_id;
    private int venda_id;
    private Double valor;
    private int qtd;
    

  public VendaProduto() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProduto_id() {
    return produto_id;
  }

  public void setProduto_id(int produto_id) {
    this.produto_id = produto_id;
  }

  public int getVenda_id() {
    return venda_id;
  }

  public void setVenda_id(int venda_id) {
    this.venda_id = venda_id;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public int getQtd() {
    return qtd;
  }

  public void setQtd(int qtd) {
    this.qtd = qtd;
  }

  

}