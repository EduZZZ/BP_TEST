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

    LOGGER.info("Cliente encontrado na sessão: {}", c.toString());

    try {
      Endereco e = (Endereco) sessao.getAttribute("endereco");
      if (e != null) {
        LOGGER.info("Endereço recuperado da sessão: {}", e.toString());
      } else {
        LOGGER.warn("Endereço não encontrado na sessão.");
      }

      List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");
      if (carrinho != null) {
        LOGGER.info("Itens do carrinho recuperados da sessão: {}", carrinho.size());
      } else {
        LOGGER.warn("Carrinho de compras não encontrado na sessão.");
      }

      Double total = (Double) sessao.getAttribute("total");
      if (total != null) {
        LOGGER.info("Total do carrinho recuperado da sessão: {}", total);
      } else {
        LOGGER.warn("Total não encontrado na sessão.");
      }

      MeioPagamento pagamento = (MeioPagamento) sessao.getAttribute("pagamento");
      if (pagamento != null) {
        LOGGER.info("Meio de pagamento recuperado da sessão: {}", pagamento.toString());
      } else {
        LOGGER.warn("Meio de pagamento não encontrado na sessão.");
      }

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

    Cliente c = (Cliente) sessao.getAttribute("cliente");
    MeioPagamento pagamento = (MeioPagamento) sessao.getAttribute("pagamento");
    Endereco e = (Endereco) sessao.getAttribute("endereco");
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");
    Double total = (Double) sessao.getAttribute("total");

    if (c == null) {
      LOGGER.warn("Cliente não autenticado. Redirecionando para a página de login.");
      mv.setViewName("redirect:/Login");
      return mv;
    }

    if (pagamento == null) {
      LOGGER.warn("Meio de pagamento não selecionado. Redirecionando para a página de seleção de pagamento.");
      mv.setViewName("redirect:/SelecaoPagamento");
      return mv;
    }

    if (e == null) {
      LOGGER.warn("Endereço de entrega não definido. Redirecionando para a página de endereços.");
      mv.setViewName("redirect:/Enderecos");
      return mv;
    }

    if (carrinho == null || carrinho.isEmpty()) {
      LOGGER.warn("Carrinho de compras vazio. Redirecionando para a página de produtos.");
      mv.setViewName("redirect:/Produtos");
      return mv;
    }

    if (total == null) {
      LOGGER.error("Não foi possível recuperar o total do carrinho. Redirecionando para a página do carrinho para recalculo.");
      mv.setViewName("redirect:/Carrinho");
      return mv;
    }

    // Processamento da venda:
    VendaDAO vendaDao = new VendaDAO();
    VendaProdutoDAO vendaProdutoDao = new VendaProdutoDAO();

    Venda v = new Venda();
    v.setCliente_id(c.getId());
    v.setEndereco_id(e.getId());
    if (pagamento.getMeio_pagamento().equals("boleto")) {
      v.setMeio_pagamento_id(1);
    } else {
      v.setMeio_pagamento_id(2);
    }
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

    LOGGER.info("Compra finalizada com sucesso.");

    return mv;
  }
}