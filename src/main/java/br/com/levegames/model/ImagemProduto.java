package br.com.levegames.model;

public class ImagemProduto {
    
    private int id;
    private int produto_id;
    private String url_imagem;

    public ImagemProduto() {}

    public ImagemProduto(int id, int produto_id, String url_imagem) {
	this.id = id;
	this.produto_id = produto_id;
	this.url_imagem = url_imagem;
    }

    public ImagemProduto(int produto_id, String url_imagem) {
	this.produto_id = produto_id;
	this.url_imagem = url_imagem;
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

    public String getUrl_imagem() {
	return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
	this.url_imagem = url_imagem;
    }

    @Override
    public String toString() {
	return "ImagemProduto{" + "id=" + id + ", produto_id=" + produto_id + ", url_imagem=" + url_imagem + '}';
    }

}
