
package br.com.bestphones.model;

public class ProdutoCarrinho {
  private int id;
  private String url_imagem;
  private String nome;
  private String celulares;
  private double preco;
  private int qtde;

  public ProdutoCarrinho() {
  }

  public ProdutoCarrinho(int id, String url_imagem, String nome, String celulares, double preco, int qtde) {
    this.id = id;
    this.url_imagem = url_imagem;
    this.nome = nome;
    this.celulares = celulares;
    this.preco = preco;
    this.qtde = qtde;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl_imagem() {
    return url_imagem;
  }

  public void setUrl_imagem(String url_imagem) {
    this.url_imagem = url_imagem;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCelulares() {
    return celulares;
  }

  public void setCelulares(String celulares) {
    this.celulares = celulares;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public int getQtde() {
    return qtde;
  }

  public void setQtde(int qtde) {
    this.qtde = qtde;
  }
  
}
