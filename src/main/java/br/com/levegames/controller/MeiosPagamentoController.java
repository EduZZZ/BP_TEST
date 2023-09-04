package br.com.levegames.controller;

import br.com.levegames.dao.ClienteDAO;
import br.com.levegames.model.ProdutoCarrinho;
import br.com.levegames.dao.ConsoleDAO;
import br.com.levegames.dao.EnderecoDAO;
import br.com.levegames.dao.ImagemProdutoDAO;
import br.com.levegames.dao.PerguntaRespostaProdutoDAO;
import br.com.levegames.dao.ProdutoDAO;
import br.com.levegames.model.Cliente;
import br.com.levegames.model.Console;
import br.com.levegames.model.Endereco;
import br.com.levegames.model.ImagemProduto;
import br.com.levegames.model.MeioPagamento;
import br.com.levegames.model.PerguntaRespostaProduto;
import br.com.levegames.model.Produto;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeiosPagamentoController {

  @GetMapping("/Meios-de-pagamento")
  public ModelAndView mostrarTela(HttpServletRequest request) {
    
    ModelAndView mv = null;
    EnderecoDAO enderecosDao = new EnderecoDAO();
    
    HttpSession sessao = request.getSession();
    if (sessao.getAttribute("cliente") == null) {

      mv = new ModelAndView("redirect:/Login");

    } else {

      Cliente c = (Cliente) sessao.getAttribute("cliente");
      mv = new ModelAndView("meios-pagamento");
      mv.addObject("total",  sessao.getAttribute("total"));
    }
    
    return mv;

  }
  
  @PostMapping("/Meios-de-pagamento/Cartao")
  public ModelAndView pagamentoCartao(
          @RequestParam(value = "username", required = true) String nome_impresso,
          @RequestParam(value = "email", required = true) String numero_cartao,
          @RequestParam(value = "expiry_month", required = true) String mes_expiracao,
          @RequestParam(value = "expiry_year", required = true) String ano_expiracao,
          @RequestParam(value = "password_confirm", required = true) String cvv,
          @RequestParam(value = "parcelas", required = true) String qtd_parcelas, HttpServletRequest request) {
    
    
    MeioPagamento pagamento = new MeioPagamento();
    pagamento.setMeio_pagamento("cartao");
    pagamento.setNome_impresso(nome_impresso);
    pagamento.setNumero_cartao(numero_cartao);
    pagamento.setMes_expiracao(mes_expiracao);
    pagamento.setAno_expiracao(ano_expiracao);
    pagamento.setCvv(cvv);
    pagamento.setQtd_parcelas(qtd_parcelas);
    ModelAndView mv = new ModelAndView("redirect:/Checkout");
    
    HttpSession sessao = request.getSession();
    sessao.setAttribute("pagamento", pagamento);

    return mv;
  }
  
  @PostMapping("/Meios-de-pagamento/Boleto")
  public ModelAndView pagamentoBoleto(HttpServletRequest request) {
    
    MeioPagamento pagamento = new MeioPagamento();
    pagamento.setMeio_pagamento("boleto");
    ModelAndView mv = new ModelAndView("redirect:/Checkout");
    
    HttpSession sessao = request.getSession();
    sessao.setAttribute("pagamento", pagamento);

    return mv;
  }

}
