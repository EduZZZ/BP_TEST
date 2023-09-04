package br.com.levegames.controller;

import br.com.levegames.dao.ConsoleDAO;
import br.com.levegames.dao.ImagemProdutoDAO;
import br.com.levegames.dao.ProdutoDAO;
import br.com.levegames.model.Console;
import br.com.levegames.model.ImagemProduto;
import br.com.levegames.model.Produto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @GetMapping("/Home")
  public ModelAndView exibirHome() {
    ModelAndView mv = new ModelAndView("home");
    
    ProdutoDAO produtoDao = new ProdutoDAO();
    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    ConsoleDAO consoleDao = new ConsoleDAO();
    List<Produto> produtos = produtoDao.getProdutos();
    List<ImagemProduto> imagens = imagemProdutoDao.getFirstImagensProduto();
    List<Console> consoles = consoleDao.getConsolesOrdenado();
        
    mv.addObject("produtos", produtos);
    mv.addObject("imagens", imagens);
    mv.addObject("consoles", consoles);

    return mv;
  }

}
