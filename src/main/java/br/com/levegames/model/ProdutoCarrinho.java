
package br.com.levegames.model;

public class ProdutoCarrinho {
  private int id;
  private String url_imagem;
  private String nome;
  private String console;
  private double preco;
  private int qtde;

  public ProdutoCarrinho() {
  }

  public ProdutoCarrinho(int id, String url_imagem, String nome, String console, double preco, int qtde) {
    this.id = id;
    this.url_imagem = url_imagem;
    this.nome = nome;
    this.console = console;
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

  public String getConsole() {
    return console;
  }

  public void setConsole(String console) {
    this.console = console;
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
