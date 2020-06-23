package br.com.casadocodigo.loja.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder ) {
		binder.addValidators( new UsuarioValidation()  );
		
	}
	
	@RequestMapping("/listar")
	public ModelAndView obterUsuarios() throws URISyntaxException{
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		List<Usuario> list = this.usuarioDao.listarUsuarios();
		modelAndView.addObject("usuarios", list);
		return modelAndView;
	}
	
	
	@RequestMapping("/detalhe")
	public ModelAndView detalhe( @RequestParam(name = "email") String email){
		System.out.println(email);
	    ModelAndView modelAndView = new ModelAndView("/usuarios/detalhe");
	    Usuario usuario = usuarioDao.loadUserByUsername(email);
	    
	    return form(usuario);
	}
	@RequestMapping("/form")
	public ModelAndView form(Usuario user) {
		ModelAndView modelAndView = new ModelAndView("/usuarios/detalhe");
		 List<Role> lsRoles = roleDAO.listar();
		 List<String> listRoles = new ArrayList<>();
		 lsRoles.forEach( r->{
			 listRoles.add(r.getNome());
		 });
		 modelAndView.addObject("listRoles", listRoles.toArray());
		 modelAndView.addObject("usuario", user);
		return modelAndView;
	}
	
	@RequestMapping("/gravar")
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result , RedirectAttributes redirect ) {
		 ModelAndView modelAndView = null;
		if( result.hasErrors() ) {
			  return form( usuario);
		}
		
		this.usuarioDao.gravar(usuario);
		
		 modelAndView = new ModelAndView("usuarios/sucesso");
		
		
		return modelAndView;
	}
	
	
	
	
}
