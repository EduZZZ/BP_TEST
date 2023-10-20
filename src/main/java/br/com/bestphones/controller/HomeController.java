package br.com.bestphones.controller;


import br.com.bestphones.model.Produto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @GetMapping("/Home")
  public ModelAndView exibirHome() {
    ModelAndView mv = new ModelAndView("home");
    



    return mv;
  }

}
