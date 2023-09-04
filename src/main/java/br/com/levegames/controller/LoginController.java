package br.com.levegames.controller;

import br.com.levegames.dao.ClienteDAO;
import br.com.levegames.dao.UsuarioDAO;
import br.com.levegames.model.Cliente;
import br.com.levegames.model.Usuario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @GetMapping("/Login")
  public ModelAndView exibirHome() {
    Cliente c = new Cliente();
    ModelAndView mv = new ModelAndView("login");
    mv.addObject("cliente", c);
    return mv;
  }
  
  @PostMapping("/Login")
  public ModelAndView login(
          @ModelAttribute(value = "cliente") Cliente c,
          HttpServletRequest request) {
    ClienteDAO clienteDao = new ClienteDAO();
    ModelAndView mv;

    c = clienteDao.getCliente(c.getEmail(), c.getSenha());
    if (c != null ) {
      HttpSession sessao = request.getSession();
      sessao.setAttribute("cliente", c);
      mv = new ModelAndView("redirect:/Home");
      mv.addObject("c", c);
    } else {
      mv = new ModelAndView("login");
      mv.addObject("cliente", new Cliente());
    }
    return mv;
  }

}
