package br.com.levegames.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

  @GetMapping("/Logout")
  public ModelAndView exibirHome(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView("redirect:/Home");
    HttpSession sessao = request.getSession();
    sessao.removeAttribute("cliente");
    return mv;
  }
  
  @GetMapping("/Logoff")
  public ModelAndView deslogarCliente(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView("redirect:/Home");
    HttpSession sessao = request.getSession();
    sessao.removeAttribute("cliente");
    return mv;
  }
}