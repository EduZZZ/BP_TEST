package br.com.bestphones.model;

public class Produto {
    
    private int id;
    private String nome;
    private String descricao_curta;
    private String descricao_detalhada;
    private float preco;
    private int qtde;
    private boolean disponivel_venda;
    private int celulares_id;

    public Produto() {}

    public Produto(int id, String nome, String descricao_curta, String descricao_detalhada, float preco, int qtd, boolean ativo, int celulares_id) {
	this.id = id;
	this.nome = nome;
	this.descricao_curta = descricao_curta;
	this.descricao_detalhada = descricao_detalhada;
	this.preco = preco;
	this.qtde = qtd;
	this.disponivel_venda = ativo;
	this.celulares_id = celulares_id;
    }

    public Produto(String nome, String descricao_curta, String descricao_detalhada, float preco, int qtd, boolean ativo, int celulares_id) {
	this.nome = nome;
	this.descricao_curta = descricao_curta;
	this.descricao_detalhada = descricao_detalhada;
	this.preco = preco;
	this.qtde = qtd;
	this.disponivel_venda = ativo;
	this.celulares_id = celulares_id;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getDescricao_curta() {
	return descricao_curta;
    }

    public void setDescricao_curta(String descricao_curta) {
	this.descricao_curta = descricao_curta;
    }

    public String getDescricao_detalhada() {
	return descricao_detalhada;
    }

    public void setDescricao_detalhada(String descricao_detalhada) {
	this.descricao_detalhada = descricao_detalhada;
    }

    public float getPreco() {
	return preco;
    }

    public void setPreco(float preco) {
	this.preco = preco;
    }

    public int getQtde() {
	return qtde;
    }

    public void setQtde(int qtd) {
	this.qtde = qtd;
    }

    public boolean isDisponivel_venda() {
	return disponivel_venda;
    }

    public void setDisponivel_venda(boolean disponivel_venda) {
	this.disponivel_venda = disponivel_venda;
    }

    public int getCelulares_id() {
	return celulares_id;
    }

    public void setCelulares_id(int celulares_id) {
	this.celulares_id = celulares_id;
    }

    @Override
    public String toString() {
	return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao_curta=" + descricao_curta + ", descricao_detalhada=" + descricao_detalhada + ", preco=" + preco + ", qtd=" + qtde + ", ativo=" + disponivel_venda + ", celulares_id=" + celulares_id + '}';
    }
    
}
