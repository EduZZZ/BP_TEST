package br.com.levegames.model;

public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private boolean registro_deletado;

  public Cliente() {
  }

  public Cliente(int id, String nome, String cpf, String telefone, String email, String senha, boolean registro_deletado) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.email = email;
    this.senha = senha;
    this.registro_deletado = registro_deletado;
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

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public boolean isRegistro_deletado() {
    return registro_deletado;
  }

  public void setRegistro_deletado(boolean registro_deletado) {
    this.registro_deletado = registro_deletado;
  }

  @Override
  public String toString() {
    return "Cliente{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + ", registro_deletado=" + registro_deletado + '}';
  }

}