package br.com.levegames.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.levegames.dao.ImagemProdutoDAO;
import br.com.levegames.dao.ProdutoDAO;
import br.com.levegames.model.ImagemProduto;
import br.com.levegames.model.Produto;
import br.com.levegames.model.ProdutoCarrinho;

@Controller
public class CarrinhoController {

  @GetMapping("/Carrinho")
  public ModelAndView mostrarTela(HttpServletRequest request) {

    ModelAndView mv = new ModelAndView("carrinho");

    HttpSession sessao = request.getSession();
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");

    if (carrinho == null) {
      carrinho = new ArrayList<ProdutoCarrinho>();
    }

    double total = 0;
    for (ProdutoCarrinho produtoCarrinho : carrinho) {
        total = total + produtoCarrinho.getPreco() * produtoCarrinho.getQtde();
      }
    
    if (sessao.getAttribute("cep") != null) {
      total = total + 20;
    }
    
    sessao.setAttribute("total", total);
    
    mv.addObject("total", total);
    
    sessao.setAttribute("carrinho-compras", carrinho);
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    

    return mv;
  }

  @PostMapping("/Carrinho")
  public ModelAndView adicionarProduto(
          @RequestParam(value = "produtoId") int produtoId, HttpServletRequest request) {

    ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho();

    ProdutoDAO produtoDao = new ProdutoDAO();
    ImagemProdutoDAO imagemDao = new ImagemProdutoDAO();

    Produto p = produtoDao.getProdutos(produtoId);
    List<ImagemProduto> img = imagemDao.getImagensProduto(produtoId);

    produtoCarrinho.setId(produtoId);
    produtoCarrinho.setNome(p.getNome());
    produtoCarrinho.setUrl_imagem(img.get(0).getUrl_imagem());
    produtoCarrinho.setQtde(1);
    produtoCarrinho.setPreco(p.getPreco());

    HttpSession sessao = request.getSession();
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");
    if (carrinho == null) {
      carrinho = new ArrayList<ProdutoCarrinho>();
    }
    carrinho.add(produtoCarrinho);
    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    
    
    

    mv.addObject("cep", sessao.getAttribute("cep"));
    
    mv.addObject("carrinho", carrinho);
    return mv;
  }


  @PostMapping("/Carrinho/Cep")
  public ModelAndView addCep(@RequestParam(value = "cep") String cep, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");

    if (cep.length() < 8) {
      sessao.setAttribute("cep", null);
    } else {
      sessao.setAttribute("cep", cep);
    }


    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("cep", cep);
    mv.addObject("carrinho", carrinho);
    return mv;

  }
  
  @PostMapping("/Carrinho/{id}/removeQtde")
  public ModelAndView removeQtdeProduto(@PathVariable("id") int id, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");

    for (ProdutoCarrinho produtoCarrinho : carrinho) {
      if (produtoCarrinho.getId() == id) {
        if (produtoCarrinho.getQtde() != 1) produtoCarrinho.setQtde(produtoCarrinho.getQtde() - 1);
      }
    }

    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    return mv;

  }
  
  @PostMapping("/Carrinho/{id}/addQtde")
  public ModelAndView addQtdeProduto(@PathVariable("id") int id, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");

    for (ProdutoCarrinho produtoCarrinho : carrinho) {
      if (produtoCarrinho.getId() == id) {
        produtoCarrinho.setQtde(produtoCarrinho.getQtde() + 1);
      }
    }

    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    return mv;

  }

  @DeleteMapping("/Carrinho/{id}")
  public ModelAndView removeProduto(@PathVariable("id") int id, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<ProdutoCarrinho> carrinho = (List<ProdutoCarrinho>) sessao.getAttribute("carrinho-compras");

    if (carrinho.size() == 1) {
      carrinho.clear();
    } else {
      for (ProdutoCarrinho produtoCarrinho : carrinho) {
        if (produtoCarrinho.getId() == id) {
          carrinho.remove(produtoCarrinho);
          break;
        }
      }
    }

    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    return mv;

  }

}
