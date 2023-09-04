package br.com.levegames.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.levegames.dao.UsuarioDAO;
import br.com.levegames.model.Usuario;

@Controller
public class BackofficeUsuarioController {

  @GetMapping("/Backoffice/Usuarios")
  public ModelAndView mostrarTela(HttpServletRequest request) {

    HttpSession sessao = request.getSession();

    Usuario u = (Usuario) sessao.getAttribute("user");
    
    
    ModelAndView mv = new ModelAndView("backoffice-usuarios");
    UsuarioDAO usuarioDao = new UsuarioDAO();
    List<Usuario> usuarios = usuarioDao.getUsuarios();
    mv.addObject("usuarios", usuarios);
    return mv;
  }

  @GetMapping("/Backoffice/Usuarios/Novo")
  public ModelAndView exibirCadastro() {
    Usuario u = new Usuario();
    ModelAndView mv = new ModelAndView("backoffice-usuarios-novo");
    mv.addObject("usuario", u);
    return mv;
  }

  @GetMapping("/Backoffice/Usuarios/{id}")
  public ModelAndView exibirAlterarUsuario(@PathVariable("id") int id) {
    ModelAndView mv = new ModelAndView("backoffice-usuarios-alterar");
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Usuario u = usuarioDao.getUsuario(id);
    mv.addObject("usuario", u);
    return mv;
  }

  @PutMapping("/Backoffice/Usuarios/{id}")
  public ModelAndView alterarUsuario(
          @PathVariable("id") int id,
          @ModelAttribute(value = "usuario") Usuario u) {

    UsuarioDAO usuarioDao = new UsuarioDAO();
    usuarioDao.alterarUsuario(u);
    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Usuarios");
    return mv;
  }

  @PostMapping("/Backoffice/Usuarios/Novo")
  public ModelAndView adicionarUsuario(
          @ModelAttribute(value = "usuario") Usuario u,
          @RequestParam(value = "repetir-senha", required = true) String repetirSenha) {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    boolean usuarioExistente = usuarioDao.getIsUsuarioExiste(u.getEmail());
    if (!repetirSenha.equals(u.getSenha()) || u.getNome().length() < 5 || usuarioExistente ) {
      ModelAndView mv = new ModelAndView("backoffice-usuarios-novo");
      mv.addObject("usuario", u);
      return mv;
    }

    usuarioDao.salvarUsuario(u);
    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Usuarios");
    return mv;
  }

  @DeleteMapping("/Backoffice/Usuarios/{id}")
  public ModelAndView removeUsuario(@PathVariable("id") int id) {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    usuarioDao.removeUsuario(id);
    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Usuarios");
    return mv;
  }

}
