/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.levegames.model;

/**
 *id int primary key not null auto_increment,
	cliente_id int not null,
    endereco_id int not null,
    data timestamp default current_timestamp,
    meio_pagamento_id int not null,
    status_id int not null,
    codigo_rastreio varchar(30),
	total decimal(11, 2),
    obs text,
 * @author alvar
 */
public class Venda {
  
  private int id;
  private int cliente_id;
  private int endereco_id;
  private int meio_pagamento_id;
  private int status_id;
  private String codigo_rastreio;
  private double total;
  private String obs;

  public Venda() {
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

  public int getEndereco_id() {
    return endereco_id;
  }

  public void setEndereco_id(int endereco_id) {
    this.endereco_id = endereco_id;
  }

  public int getMeio_pagamento_id() {
    return meio_pagamento_id;
  }

  public void setMeio_pagamento_id(int meio_pagamento_id) {
    this.meio_pagamento_id = meio_pagamento_id;
  }

  public int getStatus_id() {
    return status_id;
  }

  public void setStatus_id(int status_id) {
    this.status_id = status_id;
  }

  public String getCodigo_rastreio() {
    return codigo_rastreio;
  }

  public void setCodigo_rastreio(String codigo_rastreio) {
    this.codigo_rastreio = codigo_rastreio;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }
  
}
