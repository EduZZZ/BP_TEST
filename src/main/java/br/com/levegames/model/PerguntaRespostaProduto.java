package br.com.levegames.model;

public class PerguntaRespostaProduto {
    
    private int id;
    private int produto_id;
    private String pergunta;
    private String resposta;

    public PerguntaRespostaProduto() {}

    public PerguntaRespostaProduto(int id, int produto_id, String pergunta, String resposta) {
	this.id = id;
	this.produto_id = produto_id;
	this.pergunta = pergunta;
	this.resposta = resposta;
    }

    public PerguntaRespostaProduto(int produto_id, String pergunta) {
	this.produto_id = produto_id;
	this.pergunta = pergunta;
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

    public String getPergunta() {
	return pergunta;
    }

    public void setPergunta(String pergunta) {
	this.pergunta = pergunta;
    }

    public String getResposta() {
	return resposta;
    }

    public void setResposta(String resposta) {
	this.resposta = resposta;
    }

    @Override
    public String toString() {
	return "PerguntaRespostaProduto{" + "id=" + id + ", produto_id=" + produto_id + ", pergunta=" + pergunta + ", resposta=" + resposta + '}';
    }
    
}
