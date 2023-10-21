package br.com.bestphones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.bestphones.dao.EnderecoDAO;
import br.com.bestphones.model.Cliente;
import br.com.bestphones.model.Endereco;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EnderecoPagamentoController {

  private static final Logger logger = LoggerFactory.getLogger(EnderecoPagamentoController.class);

  // Injetando o DAO (supondo que EnderecoDAO esteja configurado como um bean do Spring)
  @Autowired
  private EnderecoDAO enderecosDao;

  @GetMapping("/Endereco-de-entrega")
  public ModelAndView mostrarTela(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView();
    HttpSession sessao = request.getSession();
    Cliente c = (Cliente) sessao.getAttribute("cliente");

    if (c == null) {
      logger.info("Nenhum cliente está logado. Redirecionando para /Login.");
      mv.setViewName("redirect:/Login");
    } else {
      List<Endereco> enderecos = enderecosDao.getEnderecos(c.getId());

      logger.info("Cliente {} está logado.", c.getNome());
      logger.info("Recuperados {} endereços para o cliente {}.", enderecos.size(), c.getNome());

      mv.setViewName("endereco-entrega");
      mv.addObject("enderecos", enderecos);
    }

    return mv;
  }

  @PostMapping("/Endereco-de-entrega/{id}")
  public String selecionarEndereco(@PathVariable("id") int id, HttpServletRequest request) {
    HttpSession sessao = request.getSession();
    sessao.setAttribute("endereco", enderecosDao.getEnderecoEntregaPagamento(id));
    return "redirect:/Meios-de-pagamento";
  }

  @PostMapping("/Novo-endereco")
  public ModelAndView cadastrarEndereco(
          @Valid @ModelAttribute("endereco") Endereco endereco,
          BindingResult result,
          HttpServletRequest request) {

    ModelAndView mv = new ModelAndView("redirect:/Endereco-de-entrega");

    if (result.hasErrors()) {
      // Aqui você pode adicionar lógica para retornar para o formulário e exibir os erros.
      mv.setViewName("formulario-endereco");
      return mv;
    }

    HttpSession sessao = request.getSession();
    Cliente c = (Cliente) sessao.getAttribute("cliente");

    if (c == null) {
      logger.error("Tentativa de adicionar um endereço sem estar logado.");
      mv.setViewName("redirect:/Login");
      return mv;
    }

    endereco.setCliente_id(c.getId());
    endereco.setIs_faturamento(false);

    enderecosDao.salvarEnderecoCliente(c.getId(), endereco);

    return mv;
  }


}
