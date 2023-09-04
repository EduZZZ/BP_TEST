
package br.com.levegames.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BackofficeHomeController {
    
    @GetMapping("/Backoffice/Home")
    public ModelAndView mostrarTela(HttpServletRequest request) {
		HttpSession sessao = request.getSession();
        Object x = sessao.getAttribute("user");
	ModelAndView mv = new ModelAndView("backoffice-home");
        mv.addObject("u", x);
	return mv;
    }

}