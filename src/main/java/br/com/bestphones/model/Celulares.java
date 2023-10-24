package br.com.bestphones.model;

public class Celulares {
    
    private int id;
    private String nome;

    public Celulares() {}

    public Celulares(int id, String nome) {
	this.id = id;
	this.nome = nome;
    }

    public Celulares(String nome) {
	this.nome = nome;
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

    @Override
    public String toString() {
	return "Celulares{" + "id=" + id + ", nome=" + nome + '}';
    }

}
