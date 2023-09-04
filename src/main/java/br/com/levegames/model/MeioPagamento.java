
package br.com.levegames.model;

public class MeioPagamento {
  private String meio_pagamento;
  private String nome_impresso;
  private String numero_cartao;
  private String mes_expiracao;
  private String ano_expiracao;
  private String cvv;
  private String qtd_parcelas;
  private String obs;

  public MeioPagamento() {
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }
  
  

  public String getMeio_pagamento() {
    return meio_pagamento;
  }

  public void setMeio_pagamento(String meio_pagamento) {
    this.meio_pagamento = meio_pagamento;
  }

  public String getNome_impresso() {
    return nome_impresso;
  }

  public void setNome_impresso(String nome_impresso) {
    this.nome_impresso = nome_impresso;
  }

  public String getNumero_cartao() {
    return numero_cartao;
  }

  public void setNumero_cartao(String numero_cartao) {
    this.numero_cartao = numero_cartao;
  }

  public String getMes_expiracao() {
    return mes_expiracao;
  }

  public void setMes_expiracao(String mes_expiracao) {
    this.mes_expiracao = mes_expiracao;
  }

  public String getAno_expiracao() {
    return ano_expiracao;
  }

  public void setAno_expiracao(String ano_expiracao) {
    this.ano_expiracao = ano_expiracao;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getQtd_parcelas() {
    return qtd_parcelas;
  }

  public void setQtd_parcelas(String qtd_parcelas) {
    this.qtd_parcelas = qtd_parcelas;
  }

}
