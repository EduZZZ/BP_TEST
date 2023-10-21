package br.com.bestphones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bestphones.dao.EnderecoDAO;
import br.com.bestphones.dao.VendaDAO;
import br.com.bestphones.dao.VendaProdutoDAO;
import br.com.bestphones.model.Cliente;
import br.com.bestphones.model.Endereco;
import br.com.bestphones.model.MeioPagamento;
import br.com.bestphones.model.ProdutoCarrinho;
import br.com.bestphones.model.Venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheckoutController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);

  @Autowired
  private EnderecoDAO enderecosDao;

  @Autowired
  private VendaDAO vendaDao;

  @Autowired
  private VendaProdutoDAO vendaProdutoDao;

  @GetMapping("/Checkout")
  public ModelAndView mostrarTela(HttpServletRequest request) {
    LOGGER.info("Iniciando processo de checkout...");

    ModelAndView mv = new ModelAndView();
    HttpSession sessao = request.getSession();
    Cliente c = (Cliente) sessao.getAttribute("cliente");

    if (c == null) {
      LOGGER.warn("Nenhum cliente encontrado na sessão. Redirecionando para o Login...");
      mv.setViewName("redirect:/Login");
      return mv;
    }

    try {
      Endereco e = (Endereco) sessao.getAttribute("endereco");
      List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");
      double total = (Double) sessao.getAttribute("total");
      MeioPagamento pagamento = (MeioPagamento) sessao.getAttribute("pagamento");

      mv.setViewName("checkout");
      mv.addObject("cliente", c);
      mv.addObject("endereco", e);
      mv.addObject("carrinho", carrinho);
      mv.addObject("pagamento", pagamento);
      mv.addObject("total", total);

      LOGGER.info("Mostrando tela de checkout para o cliente {}", c.getId());
    } catch (Exception ex) {
      LOGGER.error("Erro ao mostrar tela de checkout.", ex);
    }

    return mv;
  }

  @PostMapping("/Checkout")
  public ModelAndView finalizarCompra(HttpServletRequest request) {
    LOGGER.info("Iniciando finalização de compra...");

    ModelAndView mv = new ModelAndView();
    HttpSession sessao = request.getSession();

    try {
      Cliente c = (Cliente) sessao.getAttribute("cliente");
      MeioPagamento pagamento = (MeioPagamento) sessao.getAttribute("pagamento");
      Endereco e = (Endereco) sessao.getAttribute("endereco");
      List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");
      Double total = (Double) sessao.getAttribute("total");

      Venda v = new Venda();
      v.setCliente_id(c.getId());
      v.setEndereco_id(e.getId());
      v.setMeio_pagamento_id(pagamento.getMeio_pagamento().equals("boleto") ? 1 : 2);
      v.setStatus_id(1);
      v.setTotal(total);
      v.setObs(pagamento.getQtd_parcelas());

      vendaDao.salvarVenda(v);
      int venda_id = vendaDao.getUltimaVenda();
      vendaProdutoDao.salvarVendaProdutos(venda_id, carrinho);
      v.setId(venda_id);
      mv.setViewName("venda-finalizada");
      mv.addObject("venda", v);
      mv.addObject("pagamento", pagamento);
      mv.addObject("total", total);

      LOGGER.info("Compra finalizada para o cliente {}", c.getId());
    } catch (Exception ex) {
      LOGGER.error("Erro ao finalizar compra.", ex);
    }

    return mv;
  }
}