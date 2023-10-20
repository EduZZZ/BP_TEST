package br.com.bestphones.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bestphones.dao.EnderecoDAO;
import br.com.bestphones.model.Cliente;
import br.com.bestphones.model.Endereco;

import java.util.List;

@Controller
public class EnderecoPagamentoController {

  @GetMapping("/Endereco-de-entrega")
  public ModelAndView mostrarTela(HttpServletRequest request) {

    ModelAndView mv = null;
    EnderecoDAO enderecosDao = new EnderecoDAO();

    HttpSession sessao = request.getSession();
    if (sessao.getAttribute("cliente") == null) {
      // Log para depuração
      System.out.println("Nenhum cliente está logado. Redirecionando para /Login.");
      mv = new ModelAndView("redirect:/Login");
    } else {
      Cliente c = (Cliente) sessao.getAttribute("cliente");
      // Log para depuração
      System.out.println("Cliente " + c.getNome() + " está logado.");

      mv = new ModelAndView("endereco-entrega");
      // Log para depuração
      System.out.println("Recuperando lista de endereços para o cliente " + c.getNome() + ".");
      List<Endereco> enderecos = enderecosDao.getEnderecos(c.getId());
      mv.addObject("enderecos", enderecos);
    }

    return mv;
  }


  @PostMapping("/Endereco-de-entrega/{id}")
  public ModelAndView selecionarEndereco(@PathVariable("id") int id, HttpServletRequest request) {  
    ModelAndView mv = new ModelAndView("redirect:/Meios-de-pagamento");
    EnderecoDAO enderecosDao = new EnderecoDAO();
    HttpSession sessao = request.getSession();
    sessao.setAttribute("endereco", enderecosDao.getEnderecoEntregaPagamento(id));
    return mv;
  }
  
  @PostMapping("/Novo-endereco")
  public ModelAndView cadastrarEndereco(
          @RequestParam(value = "cep", required = false) String cep,
          @RequestParam(value = "logradouro", required = false) String logradouro,
          @RequestParam(value = "numero", required = false) String numero,
          @RequestParam(value = "complemento", required = false) String complemento,
          @RequestParam(value = "bairro", required = false) String bairro,
          @RequestParam(value = "cidade", required = false) String cidade,
          @RequestParam(value = "estado", required = false) String estado,
          HttpServletRequest request) {

    ModelAndView mv = new ModelAndView("redirect:/Endereco-de-entrega");
    EnderecoDAO enderecoDao = new EnderecoDAO();

    HttpSession sessao = request.getSession();
    Cliente c = (Cliente) sessao.getAttribute("cliente");
    
    Endereco e = new Endereco();
    e.setCliente_id(c.getId());
    e.setCep(cep);
    e.setLogradouro(logradouro);
    e.setNumero(numero);
    e.setComplemento(complemento);
    e.setBairro(bairro);
    e.setCidade(cidade);
    e.setEstado(estado);
    e.setIs_faturamento(false);

    enderecoDao.salvarEnderecoCliente(c.getId(), e);

    return mv;
  }
  
}
