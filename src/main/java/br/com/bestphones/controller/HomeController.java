package br.com.bestphones.controller;

import br.com.bestphones.dao.CelularesDAO;
import br.com.bestphones.dao.ImagemProdutoDAO;
import br.com.bestphones.dao.ProdutoDAO;
import br.com.bestphones.model.Celulares;
import br.com.bestphones.model.ImagemProduto;
import br.com.bestphones.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

  @Autowired
  private ProdutoDAO produtoDao;

  @Autowired
  private ImagemProdutoDAO imagemProdutoDao;

  @Autowired
  private CelularesDAO celularesDao;

  @GetMapping("/Home")
  public ModelAndView exibirHome() {
    ModelAndView mv = new ModelAndView("home");

    List<Produto> produtos = produtoDao.getProdutos();
    List<ImagemProduto> imagens = imagemProdutoDao.getFirstImagensProduto();
    List<Celulares> celulares = celularesDao.getcelularesOrdenado();

    mv.addObject("produtos", produtos);
    mv.addObject("imagens", imagens);
    mv.addObject("celulares", celulares);

    return mv;
  }

}
