package br.com.levegames.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.levegames.dao.UsuarioDAO;
import br.com.levegames.model.Usuario;

@Controller
public class BackofficeLoginController {

  @GetMapping("/Backoffice/Login")
  public ModelAndView mostrarTela() {
    Usuario u = new Usuario();
    ModelAndView mv = new ModelAndView("backoffice-login");
    mv.addObject("usuario", u);
    return mv;
  }

  @PostMapping("/Backoffice/Login")
  public ModelAndView login(
          @ModelAttribute(value = "usuario") Usuario u,
          HttpServletRequest request) {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    ModelAndView mv;

    u = usuarioDao.getUsuario(u.getEmail(), u.getSenha());
    if (u != null ) {
      HttpSession sessao = request.getSession();
      sessao.setAttribute("user", u);
      mv = new ModelAndView("redirect:/Backoffice/Home");
      mv.addObject("u", u);
    } else {
      mv = new ModelAndView("backoffice-login");
      mv.addObject("usuario", new Usuario());
    }
    return mv;
  }
}
