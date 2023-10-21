package br.com.bestphones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bestphones.dao.ClienteDAO;
import br.com.bestphones.model.Cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ClienteController {

  private final ClienteDAO clienteDao;

  @Autowired
  public ClienteController(ClienteDAO clienteDao) {
    this.clienteDao = clienteDao;
  }

  @GetMapping("/Cliente/Novo")
  public ModelAndView exibirCadastro() {
    Cliente c = new Cliente();
    ModelAndView mv = new ModelAndView("cliente-novo");
    mv.addObject("cliente", c);
    return mv;
  }

  @PostMapping("/Cliente/Novo")
  public ModelAndView cadastrarCliente(@ModelAttribute(value = "cliente") Cliente c) {
    ModelAndView mv = new ModelAndView("login");
    clienteDao.salvarCliente(c);
    return mv;
  }

  @GetMapping("/DadosPessoais")
  public ModelAndView exibirAlterarProduto(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView("cliente-alterar");
    HttpSession sessao = request.getSession();

    Cliente c = (Cliente) sessao.getAttribute("cliente");
    c = clienteDao.getCliente(c.getId());

    mv.addObject("cliente", c);
    return mv;
  }

  @PostMapping("/DadosPessoais")
  public ModelAndView alterarCliente(@ModelAttribute(value = "cliente") Cliente c, HttpServletRequest request) {
    HttpSession sessao = request.getSession();
    Cliente cliente = (Cliente) sessao.getAttribute("cliente");
    c.setId(cliente.getId());

    ModelAndView mv = new ModelAndView("home");
    clienteDao.alterarCliente(c);

    return mv;
  }

}
