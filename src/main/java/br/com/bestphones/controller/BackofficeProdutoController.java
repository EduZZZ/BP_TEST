package br.com.bestphones.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bestphones.dao.ConsoleDAO;
import br.com.bestphones.dao.ImagemProdutoDAO;
import br.com.bestphones.dao.PerguntaRespostaProdutoDAO;
import br.com.bestphones.dao.ProdutoDAO;
import br.com.bestphones.model.Console;
import br.com.bestphones.model.ImagemProduto;
import br.com.bestphones.model.PerguntaRespostaProduto;
import br.com.bestphones.model.Produto;

@Controller
public class BackofficeProdutoController {

  @GetMapping("/Backoffice/Produtos")
  public ModelAndView mostrarTela() {

    ModelAndView mv = new ModelAndView("backoffice-produtos");
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtos = produtoDao.getProdutos();
    mv.addObject("games", produtos);
    return mv;
  }

  @GetMapping("/Backoffice/Produtos/Novo")
  public ModelAndView exibirCadastro() {

    Produto p = new Produto();

    ConsoleDAO celularesDao = new ConsoleDAO();
    List<Console> celulares = celularesDao.getcelulares();

    ModelAndView mv = new ModelAndView("backoffice-produtos-novo");

    mv.addObject("celulares", celulares);
    mv.addObject("produto", p);

    return mv;
  }

  @GetMapping("/Backoffice/Produtos/{id}")
  public ModelAndView exibirAlterarProduto(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("backoffice-produtos-alterar");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ConsoleDAO celularesDao = new ConsoleDAO();
    List<Console> celulares = celularesDao.getcelulares();

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    PerguntaRespostaProdutoDAO perguntasRespostasProdutoDAO = new PerguntaRespostaProdutoDAO();
    List<PerguntaRespostaProduto> listaPerguntasRespostas = perguntasRespostasProdutoDAO.getPerguntasRespostasProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);
    mv.addObject("listaPerguntasRespostas", listaPerguntasRespostas);
    mv.addObject("celulares", celulares);

    return mv;
  }
  
  @GetMapping("/Backoffice/Produtos/Visualizar/{id}")
  public ModelAndView verProduto(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("detalhes");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ConsoleDAO celularesDao = new ConsoleDAO();
    Console celulares = celularesDao.getConsolePorId(p.getConsole_id());

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    PerguntaRespostaProdutoDAO perguntasRespostasProdutoDAO = new PerguntaRespostaProdutoDAO();
    List<PerguntaRespostaProduto> listaPerguntasRespostas = perguntasRespostasProdutoDAO.getPerguntasRespostasProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);
    mv.addObject("listaPerguntasRespostas", listaPerguntasRespostas);
    mv.addObject("celulares", celulares);

    return mv;
  }

  @PutMapping("/Backoffice/Produtos/{id}")
  public ModelAndView alterarProduto(
          @PathVariable("id") int id,
          @ModelAttribute(value = "produto") Produto p,
          @RequestParam(value = "imagem", required = false) String[] imagens,
          @RequestParam(value = "pergunta", required = false) String[] perguntas,
          @RequestParam(value = "resposta", required = false) String[] respostas) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.alterarProduto(p);

    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    imagemProdutoDao.deletarImagensProduto(p.getId());

    PerguntaRespostaProdutoDAO perguntasRespostasProdutoDao = new PerguntaRespostaProdutoDAO();
    perguntasRespostasProdutoDao.deletarPerguntasRespostasProduto(p.getId());

    if (imagens != null) imagemProdutoDao.salvarImagensProduto(p.getId(), imagens);
    if (perguntas !=  null && respostas != null) perguntasRespostasProdutoDao.salvarPerguntasRespostasProduto(p.getId(), perguntas, respostas);

    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Produtos");

    return mv;
  }

  @PostMapping("/Backoffice/Produtos/Novo")
  public ModelAndView adicionarProduto(
          @ModelAttribute(value = "produto") Produto p,
          @RequestParam(value = "imagem", required = false) String[] imagens,
          @RequestParam(value = "pergunta", required = false) String[] perguntas,
          @RequestParam(value = "resposta", required = false) String[] respostas) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.salvarProduto(p);

    int produto_id = produtoDao.getUltimoProduto();

    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    PerguntaRespostaProdutoDAO perguntasRespostasProdutoDao = new PerguntaRespostaProdutoDAO();
    
    if (imagens != null) imagemProdutoDao.salvarImagensProduto(produto_id, imagens);
    if (perguntas !=  null && respostas != null) perguntasRespostasProdutoDao.salvarPerguntasRespostasProduto(produto_id, perguntas, respostas);

    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Produtos");

    return mv;
  }

  @DeleteMapping("/Backoffice/Produtos/{id}")
  public ModelAndView removeProduto(@PathVariable("id") int id) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.removeProduto(id);

    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Produtos");

    return mv;

  }

}
