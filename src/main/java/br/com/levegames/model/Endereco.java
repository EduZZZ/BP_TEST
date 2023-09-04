package br.com.levegames.model;

public class Endereco {
    
    private int id;
    private int cliente_id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean is_faturamento;
    
    public Endereco() {}

  public Endereco(int id, int cliente_id, String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, boolean is_faturamento) {
    this.id = id;
    this.cliente_id = cliente_id;
    this.cep = cep;
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.is_faturamento = is_faturamento;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCliente_id() {
    return cliente_id;
  }

  public void setCliente_id(int cliente_id) {
    this.cliente_id = cliente_id;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public boolean isIs_faturamento() {
    return is_faturamento;
  }

  public void setIs_faturamento(boolean is_faturamento) {
    this.is_faturamento = is_faturamento;
  }

  @Override
  public String toString() {
    return "Endereco{" + "id=" + id + ", cliente_id=" + cliente_id + ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", is_faturamento=" + is_faturamento + '}';
  }
  
}
